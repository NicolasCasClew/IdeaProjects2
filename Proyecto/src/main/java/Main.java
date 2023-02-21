import DBs.Employee;
import DBs.Skillset;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
     static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
     static EntityManager entityManager = entityManagerFactory.createEntityManager();
     static EntityTransaction transaction = entityManager.getTransaction();
     Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
        //crearEmpleado("test4","test4","test4");

    }


    public static void crearEmpleado( String NIF, String Nombre, String Apellido){
        Employee emp = new Employee(NIF, Nombre, Apellido,BigInteger.valueOf(crearSkillsetTest()));
        transaction.begin();
        entityManager.persist(emp);
        transaction.commit();
        System.out.println("Usuario "+emp.getName()+ " creado con exito");
    }
    public static Integer crearSkillsetTest(){
        int[] array = highestID();
        Skillset ss = new Skillset(array[0],array[1], 0,0);
        transaction.begin();
            entityManager.persist(ss) ; //TODO skill y nivel, conectar con skill
        transaction.commit();
        return array[1];
    }


    public static int[] highestID(){
        ArrayList<Integer> test = new ArrayList<>() ;
        ArrayList<Integer> test2 = new ArrayList<>() ;

            Query query = entityManager.createNativeQuery(
                    "SELECT * FROM SKILLSET", Skillset.class);
            List<Skillset> res = query.getResultList();
            for (Skillset i :  res) {
                test.add(i.getSsId().intValue());
                test2.add(i.getSkillset().intValue());
            }
        int[] array = new int[2];
        Integer max = iterator(test);
        Integer max2 = iterator(test2);
        array[0] =max+1;
        array[1] = max2+1;
        return (array);

    }
    public static void createSkill(String skill, String desc){

        transaction.begin();

        String query = "insert into SKILL(SKILL_NAME,DESCR) values(?, ?)"; //TODO Añadir control de repeticiones

        entityManager.createNativeQuery(query)
                .setParameter(1,skill)
                .setParameter(2, desc)
                .executeUpdate();



        transaction.commit();
        System.out.println("AAAA CURBAAAAA");
    }

    public static void addSkill(){

    }




    public static Integer iterator(ArrayList<Integer> arr){
        Integer max = arr.get(0);
        for(Integer i: arr){
            if(i>max){
                max=i;
            }
        }
        return max;
    }
}
