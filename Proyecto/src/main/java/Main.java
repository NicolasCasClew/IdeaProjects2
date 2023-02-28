import DBs.Employee;
import DBs.Skill;
import DBs.Skillset;
import jakarta.persistence.*;

import java.math.BigDecimal;
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
     static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);

        System.out.println("TESTEADORS = ");

       //removeSkill();
        //crearEmpleado("test4","test4","test4");
    highestID();
    }


    public static void crearEmpleado( String NIF, String Nombre, String Apellido){
        Employee emp = new Employee(NIF, Nombre, Apellido,BigInteger.valueOf(crearSkillsetTest()));
        transaction.begin();
        entityManager.persist(emp);
        transaction.commit();
        System.out.println("Usuario "+emp.getName()+ " creado con exito");
    }
    public static Employee getEmployee(String nif){
        Employee emp;
        transaction.begin();
        emp = (Employee) entityManager.createNativeQuery("SELECT * FROM EMPLOYEE WHERE NIF = ?" )
                .setParameter(1, nif)
                .getSingleResult();

        transaction.commit();
        return emp;
    }

    public static Integer crearSkillsetTest(){
        int[] array = highestID();
        Skillset ss = new Skillset(array[0],array[1], 0,0);
        //transaction.begin();
          //  entityManager.persist(ss) ; //TODO skill y nivel, conectar con skill
        //transaction.commit();
        return array[1];
    }


    public static int[] highestID(){

        BigDecimal id1= (BigDecimal) entityManager.createNativeQuery("SELECT MAX(SS_ID) FROM SKILLSET")
                .getSingleResult();
        BigDecimal id2 = (BigDecimal) entityManager.createNativeQuery("SELECT MAX(SKILLSET) FROM SKILLSET")
                .getSingleResult();


        int[] array = new int[2];
        array[0] =id1.intValue()+1;
        array[1] = id2.intValue()+1;

        return (array);
        /**
         *         ArrayList<Integer> test = new ArrayList<>() ;
         *         ArrayList<Integer> test2 = new ArrayList<>() ;
         Query query = entityManager.createNativeQuery(
         "SELECT * FROM SKILLSET", Skillset.class);

         List<Skillset> res = query.getResultList();
         for (Skillset i :  res) {
         test.add(i.getSsId().intValue());
         test2.add(i.getSkillset().intValue());
         }

         Integer max = iterator(test);
         Integer max2 = iterator(test2);
         **/
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

    public static Boolean skillExists(String skill){
        transaction.begin();

        Query query = entityManager.createNativeQuery("SELECT * FROM SKILL WHERE SKILL_NAME = ?", Skill.class)
                .setParameter(1, skill);

        try {
            Skill results = (Skill) query.getSingleResult();
            System.out.println("Existe esa skill");
            transaction.commit();
            return true;
            // entityManager.remove(results);

        }catch (NoResultException e){
            transaction.commit();
            System.out.println("No existe esa skill");
            return false;

        }

    }


    public static void removeSkill(String skill) {

        //pasar la skil a liminar (leer skill_name y obtener su id)
if(skillExists(skill)) {

    transaction.begin();

    Query query = entityManager.createNativeQuery("SELECT * FROM SKILL WHERE SKILL_NAME = ?", Skill.class)
            .setParameter(1, skill);

    Skill results = (Skill) query.getSingleResult();
    entityManager.remove(results);
    System.out.println("Habilidad " + results.getSkillName() + " con id " + results.getSkillId() + " eliminada con exito");


    //borrarla de cualquier skillset que la contenga (iterar por los skillsets y si la contienen eliminarla)


    query =entityManager.createNativeQuery("SELECT * FROM SKILLSET WHERE SKILL = ?").setParameter(1,results.getSkillId());
    List<Skillset> remSS = query.getResultList();
    for(Skillset i : remSS){
        System.out.println(i.getSkill());
        entityManager.remove(i);
    }

        transaction.commit();
        }else{
    System.out.println("Esa skill no existe tontito");
}
    }

    public static void addSkill(int SS, int SK, int LVL){  //SS se obtiene del Employee, SK de Skill, y LVL es manual
        transaction.begin();
            Skillset skillset = new Skillset(highestID()[0],SS,SK,LVL );

        transaction.commit();
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
