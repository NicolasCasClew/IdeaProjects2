import jakarta.persistence.*;
import oracleDB.Mascotas;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

            insertarMascota(new Mascotas("420", "Roberto", "Beagle", "222333444"));
            //insertarMascota(new Mascotas("133", "Manolo", "Gran Danes", "888444666"));
            //insertarMascota(new Mascotas("621", "Illo Pepe", "Basset Hound", "999000444"));


            leerMascotas();
            nombrePorRaza();
            modificarAleman();
            eliminarMascota();
        }
    }

    private static void eliminarMascota() {
        transaction.begin();
            System.out.println("Que mascota quieres eliminar?");
            String chip = sc.nextLine();
            Mascotas msc = leerMascota(chip);
            entityManager.remove(msc);
            System.out.println("Mascota eliminada");
        transaction.commit();
        spacer();
    }

    private static void modificarAleman() {
        int cont = 0;
        Query query = entityManager.createNativeQuery(
                "Select  * From mascotas  Where raza = ? ", Mascotas.class);
        query.setParameter(1,"Pastor Alemán");


        List<Mascotas> results = query.getResultList();
        for(Mascotas i : results){
            cont ++;
            //System.out.println(i.getNombre());
            modificarRaza(i.getChip());
        }
        System.out.println("Se han modificado "+cont+" mascotas");
        spacer();
    }
    private static void modificarRaza(String chip){
        transaction.begin();
        Mascotas msc = leerMascota(chip);
        msc.setRaza("Pastor Belga");
        entityManager.merge(msc);
        System.out.println("Raza de "+msc.getNombre()+" actualizada correctamente");
        transaction.commit();
    }

    private static void nombrePorRaza() {
        System.out.println("De que raza buscas los nombre?");
        String raza =sc.nextLine();
        Query query = entityManager.createNativeQuery(
                "Select  * From mascotas  Where raza = ? ", Mascotas.class);
        query.setParameter(1, raza);


        List<Mascotas> results = query.getResultList();
        for(Mascotas i : results){
            System.out.println("La mascota "+i.getNombre()+ " es de raza "+raza);
        }
        spacer();
    }
    public static Mascotas leerMascota(String chip){

        return entityManager.find(Mascotas.class,chip);
    }

    private static void leerMascotas() {
        ArrayList<Mascotas> list = (ArrayList<Mascotas>) entityManager.createQuery("from "+Mascotas.class.getName(), Mascotas.class).getResultList();

        for(int i= 0;i<list.size(); i++){
            System.out.println(list.get(i).toString());

        }
        spacer();
    }

    private static void insertarMascota(Mascotas msc) {
        transaction.begin();
        entityManager.persist(msc);
        transaction.commit();
    }

    private static void spacer(){
        System.out.println("____________________________________________________");
        System.out.println();
    }

}
