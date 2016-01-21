
import java.io.File;

/**
 * Created by mdurieux abeaucou on 21/01/16.
 */

public class Main {

    public static void main (String[] args){
        File directory = new File("./src/org/ulco");
        File[] fList = directory.listFiles();

        for(File f : fList){
            Class cl = null;
            try {
                cl = Class.forName("org.ulco." + f.getName().split("\\.")[0]);
                if(cl.getSuperclass().getName().equals("org.ulco.GraphicsObject")) System.out.println(cl.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
            System.out.println();

    }

}
