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

}
