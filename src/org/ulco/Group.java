package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject {

    private Vector<GraphicsObject> m_objectList;

    public Group() {
        super();
        m_objectList = new Vector<GraphicsObject>();
    }

    public Group(String json) {
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");
        Utils.parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2), m_objectList);
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public void add(GraphicsObject object) {
        addObject(object);
    }

    private void addObject(GraphicsObject object) {
        m_objectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            g.addObject(element.copy());
        }

        return g;
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        for(GraphicsObject go : m_objectList){
            if(!(go.isClosed(pt, distance))) return false;
        }
        return true;
    }

    public void move(Point delta) {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            element.move(delta);
        }
    }

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = Utils.searchSeparator(groupsStr);
            String groupStr;
            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parseGroup(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size = 0;
        for(Object o : m_objectList){
            if(o instanceof Group) {
                size += ((Group) o).size();
            }
            else size ++;
        }
        return size;
    }

    public String toJson() {
        String strObject = "{ type: group, objects : { ";
        String strGroup = " }, groups : { ";
        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(element instanceof Group){
                strGroup += element.toJson();
            }
            else{
                strObject += element.toJson();
                if (i < m_objectList.size() - 1) {
                    strObject += ", ";
                }
            }
        }
        return strObject + strGroup + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); i++) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (!(element instanceof Group)) {
                if (!str.equals("group[[")) {
                    str += ", ";
                }
                str += element.toString();
            }
        }
        str += "],[";
        for (int i = 0; i < m_objectList.size(); i++) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element instanceof Group) {
                str += element.toString();
            }
        }
        return str + "]]";
    }

    public Vector<GraphicsObject> getAllObjects(){
        return m_objectList;
    }

}
