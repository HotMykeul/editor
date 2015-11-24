package org.ulco;

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
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }
}
