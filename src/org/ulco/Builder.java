package org.ulco;

import java.util.Vector;

/**
 * Created by mdurieux on 27/11/15.
 */
public class Builder {

    public Document createSquares(Point origin, int line, int column, double length) {
        Document doc = new Document();

        Layer layer = doc.createLayer();

        for (int indexX = 0; indexX < column; ++indexX) {
            for (int indexY = 0; indexY < line; ++indexY) {
                layer.add(new Square(new Point(origin.getX() + indexX * length, origin.getY() + indexY * length), length));
            }
        }
        return doc;
    }

    public Document createCircles(Point center, int number, double radius, double delta) {
        Document doc = new Document();

        Layer layer = doc.createLayer();

        for (int index = 0; index < number; ++index) {
            layer.add(new Circle(center, radius + index * delta));
        }
        return doc;
    }
}
