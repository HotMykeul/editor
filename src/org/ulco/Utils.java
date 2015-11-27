package org.ulco;

import java.util.Vector;

/**
 * Created by mdurieux on 24/11/15.
 */
public class Utils {

    public static GraphicsObjects select(Point pt, double distance, Document doc) {
        GraphicsObjects list = new GraphicsObjects();

        for (Layer layer : doc.getLayer()) {
            list.addAll(select(pt, distance, layer));
        }
        return list;
    }

    public static GraphicsObjects select(Point pt, double distance, Layer layer) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : layer.getM_list()) {
            if(object instanceof Group){
                Vector<GraphicsObject> gos = select(pt, distance, (Group) object);
                list.addAll(gos);
            }
            else if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

    public static Vector<GraphicsObject> select(Point pt, double distance, Group group) {
        Vector<GraphicsObject> list = new GraphicsObjects();

        for (GraphicsObject object : group.getAllObjects()) {
            if(object instanceof Group) {
                list.addAll(select(pt, distance, (Group) object));
            }
            else if(object.isClosed(pt, distance)){
                list.add(object);
            }
        }
        return list;
    }

    public static int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public static void parseObjects(String objectsStr, Vector<GraphicsObject> m_objectList) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = Utils.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

}
