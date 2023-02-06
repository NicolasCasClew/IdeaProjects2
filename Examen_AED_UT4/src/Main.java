import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;

public class Main {
    static String dir = "src/mascotas_db.yap";
    static ObjectContainer db ;
    public static void main(String[] args) {
        initfili();
        insertarDatos();
        getAll();


        db.close();
    }

    private static void initfili() {
        File f = new File(dir);
        if (f.exists()){
            f.delete();
        }
        db = Db4oEmbedded.openFile(f.getAbsolutePath());
    }
    private  static void insertarDatos(){
        Mascotas msc1 = new Mascotas("111","Illo Pepe","Beagle", "111222333");
        Mascotas msc2 = new Mascotas("222","Illo Juan","Labrador", "333444555");
        Mascotas msc3 = new Mascotas("333","Illo Manu","Chihuahua", "888333000");
        Mascotas msc4 = new Mascotas("444","Illo Peng","Spaniel", "000222333");
        Mascotas msc5 = new Mascotas("555","Illo Seve","Bulldog", "222000555");

        db.store(msc1);
        db.store(msc2);
        db.store(msc3);
        db.store(msc4);
        db.store(msc5);
    }

    private static void getAll(){
        System.out.println("Todas las mascotas = ");
        query(new Mascotas());
        spacer();
    }

    public static void query(Mascotas msc){
        ObjectSet<Mascotas> res = db.queryByExample(msc);
        while (res.hasNext()){
            System.out.println(res.next());
        }
    }
    private static void spacer(){
        System.out.println("____________________________________________");
        System.out.println("");
    }
}
