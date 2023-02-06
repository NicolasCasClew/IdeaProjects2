import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.util.Scanner;

public class Main {
    static String dir = "src/equipos_db.yap";
   static ObjectContainer db ;
    public static void main(String[] args) {
        initfili();
        Equipos eq1 = new Equipos("Montaña 1","Alevin",3,"Las Palmas","Illo Pepe",69);
        Equipos eq2 = new Equipos("Montaña 2","Alevin",4,"Telde","Illo Juan",420);
        Equipos eq3 = new Equipos("Playa 2","Arlekin",4,"Galdar","Illo Penga",1337);
        Equipos eq4 = new Equipos("Ciudad 1","Manikin",3,"El Donbass","Illo Seve",621);

        db.store(eq1);
        db.store(eq2);
        db.store(eq3);
        db.store(eq4);

        getAll();
        getNombrePunto();
        getSedePresi();



        db.close();
    }
public static void initfili(){
    File f = new File(dir);
    if (f.exists()){
        f.delete();
    }
    db = Db4oEmbedded.openFile(f.getAbsolutePath());
}
    public static void getAll(){
        System.out.println("Get All");
        //Equipos ex = new Equipos();
        query(new Equipos());
        spacer();
    }
   public static void getNombrePunto(){
        System.out.println("Get Nombre y Putnto");
        Equipos nyp = new Equipos(
                null,"Alevin",null,null,null,null);
       ObjectSet<Equipos> res = db.queryByExample(nyp);
       while (res.hasNext()){
           Equipos eqRes = res.next();
           System.out.println("Nombre = "+eqRes.getNombre()+" y puntos = "+ eqRes.getPuntos());
       }
       spacer();
    }
    public static void getSedePresi(){
        System.out.println("Get Sede n Presi");
        Scanner sc = new Scanner(System.in);
        String nom;
        System.out.println("Que equipo quieres ver?");
        nom = sc.nextLine();
        Equipos sePre = new Equipos(nom,null,null,null,null,null);
        ObjectSet<Equipos> res = db.queryByExample(sePre);
        while (res.hasNext()){
            Equipos eqRes = res.next();
            System.out.println("Sede = "+eqRes.getSede()+" y Presidente = "+ eqRes.getPresidente());
        }
        spacer();
    }
    public static void query(Equipos eq){
        ObjectSet<Equipos> res = db.queryByExample(eq);
        while (res.hasNext()){
            System.out.println(res.next());
        }
    }
    static void spacer(){
        System.out.println("_________________________");
        System.out.println();
    }
}