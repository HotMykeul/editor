package org.ulco;

/**
 * Created by mdurieux on 27/11/15.
 */
public class Triangle extends GraphicsObject {

    private final Point m_center;
    Point a, b, c;

    public Triangle(Point m_center, Point a, Point b, Point c) {
        this.m_center = m_center;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public GraphicsObject copy() {
        return new Triangle(m_center.copy(), a, b, c);
    }

    @Override
    public boolean isClosed(Point pt, double distance) {
        Double xc = (a.getX() + b.getX() + c.getX()) / 3;
        Double yc = (a.getY() + b.getY() + c.getY()) / 3;
        Point centre = new Point(xc, yc);
        double dist = Math.sqrt((centre.getX() - pt.getX()) * (centre.getX() - pt.getX()) + ((centre.getY() - pt.getY()) * (centre.getY() - pt.getY())));
        if (dist <= distance) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void move(Point delta) {
        m_center.move(delta);
    }

    @Override
    public String toJson() {
        return "{ type: triangle, center: " + m_center.toJson() + ", a: " + a.toJson() + ", b: " + b.toJson() + ", c: " + c.toJson() + " }";
    }

    @Override
    public String toString() {
        return "triangle[" +  "," + a.toString() + "," + b.toString() + "," + c.toString() + "]";
    }
}
