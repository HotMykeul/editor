
import org.ulco.GraphicsObject;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by mdurieux abeaucou on 21/01/16.
 */

public class Main {

    public static void main(String[] args) {
        task2();

    }

    public static void task1() {
        File directory = new File("./src/org/ulco");
        File[] fList = directory.listFiles();

        for (File f : fList) {
            Class cl = null;
            try {
                cl = Class.forName("org.ulco." + f.getName().split("\\.")[0]);
                if (cl.getSuperclass().getName().equals("org.ulco.GraphicsObject")) System.out.println(cl.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void task2() {
        Class cl = GraphicsObject.class;
        for (Method m : cl.getDeclaredMethods()) {
            if (Modifier.isAbstract(m.getModifiers()))
                System.out.println(m.getName());
        }
    }
}
