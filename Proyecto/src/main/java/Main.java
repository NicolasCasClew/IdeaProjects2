import DBs.Employee;
import DBs.Skillset;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
     static EntityManager entityManager = entityManagerFactory.createEntityManager();
     static EntityTransaction transaction = entityManager.getTransaction();
     Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
       // insertarEmpleado(new Employee("123456789","Juan","Manolin", BigInteger.valueOf(0)));
        skillMasAlto();
    //crearSkillsetTest();
    }


    public static void insertarEmpleado(Employee emp){
        transaction.begin();
        entityManager.persist(emp);
        //CONSULTAR SKILLSETS Y BUSCAR EL Nº MAX
        //skillMasAlto();
        transaction.commit();
        System.out.println("Usuario creado con exito");
    }
    public static void crearSkillsetTest(){
        transaction.begin();
            entityManager.persist( new Skillset(0,0,0,0));
        transaction.commit();
    }


    public static void skillMasAlto(){
        ArrayList<Integer> test = new ArrayList<>() ;
            Query query = entityManager.createNativeQuery(
                    "SELECT * FROM SKILLSET", Skillset.class);
            List<Skillset> res = query.getResultList();
            for (Skillset i :  res) {
                System.out.println(i.getSsId());
                test.add(i.getSsId().intValue());
            }


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
