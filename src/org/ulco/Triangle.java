package org.ulco;

/**
 * Created by mdurieux on 21/01/16.
 */
public class Triangle extends GraphicsObject{
    @Override
    public GraphicsObject copy() {
        return null;
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        return false;
    }

    @Override
    void move(Point delta) {

    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
