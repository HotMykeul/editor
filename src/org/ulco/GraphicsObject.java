package org.ulco;

abstract public class GraphicsObject {
    private int m_ID;
    private String color;

    public GraphicsObject() {
        m_ID = ID.getInstance().getID();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

}
