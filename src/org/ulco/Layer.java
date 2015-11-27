package org.ulco;

import java.util.Vector;

public class Layer {
    private Vector<GraphicsObject> m_list;
    private int m_ID;

    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().getID();
    }

    public Layer(String json) {
        m_list= new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        Utils.parseObjects(str.substring(objectsIndex + 9, endIndex - 1), m_list);
    }

    public Vector<GraphicsObject> getM_list() {
        return m_list;
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        int size = 0;
        for(Object o : m_list){
            if(o instanceof Group) {
                size += ((Group) o).size();
            }
            else size ++;
        }
        return size;
    }

    public int getID() {
        return m_ID;
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";
        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);
            str += element.toJson();
            if (i < m_list.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }


}
