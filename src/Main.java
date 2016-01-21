
import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.ulco.GraphicsObject;
import org.ulco.Triangle;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mdurieux abeaucou on 21/01/16.
 */

public class Main {

    public static void main(String[] args) {
        task3();

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

    public static void task3() {
        Class cl = null;
        try {
            cl = Class.forName("org.ulco.Triangle");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class cl2 = cl.getSuperclass();

        Method[] methodsOfSuperClass = cl2.getDeclaredMethods();
        Method[] methodsToVerify = cl.getDeclaredMethods();

        boolean allMethodsImplemented = true;
        for (Method m : methodsOfSuperClass) {
            boolean methodFound = false;
            String name = m.getName();
            Class[] types = m.getParameterTypes();

            for(Method mToVerify : methodsToVerify){
                boolean goodAttributes = true;
                if(mToVerify.getName().equals(name)) {
                    Class[] typesToVerify = mToVerify.getParameterTypes();
                    for(int i = 0 ; i < types.length; i++){
                        if(!types[i].getName().equals(typesToVerify[i].getName())) goodAttributes = false;
                    }
                }
                methodFound = true;
            }
            if(!methodFound) {
                allMethodsImplemented = false;
                break;
            }
        }
        if(allMethodsImplemented) System.out.println("Toutes les méthodes de " + cl2.getName() + " ont été implémentées par " + cl.getName());
        else{
            System.out.println("Erreur, toutes les méthodes ne sont pas implémentées");
        }
    }
}
