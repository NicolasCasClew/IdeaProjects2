import DBs.Employee;
import DBs.Skill;
import DBs.Skills;
import DBs.Skillset;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
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
        //int[] missingno = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21};

        //System.out.println("EL QUE DEVUELVE = "+ missingNumber());
        while(true){
            System.out.println("Que skill quieres añador?");
            String skill = sc.nextLine();
            System.out.println("Que descripvion tendra?");
            String desc = sc.nextLine();

            createSkill(skill,desc);
        }
    }


    public static int missingNumber(){
        int[] missingno = readSkills();

        for(int i = 0; i>=0;i++){
            try {
                if ((missingno[i] + 1 == missingno[i + 1])) {
                    //es el siguiente numericamente
                    //System.out.println("SIGUE  |  buf= "+(missingno[i]+1)+"  i =  "+missingno[i+1]);
                    //return missingno[i];
                } else {
                    //System.out.println("SALE|  buf= "+(missingno[i]+1)+"  i =  "+missingno[i+1]);
                    return (missingno[i] + 1);
                }
            }catch (ArrayIndexOutOfBoundsException e){
                return ((missingno[missingno.length-1])+1);
            }
        }

        return ((missingno[missingno.length-1])+1);
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

    public static void skillIDiterator(){
        Query query = entityManager.createNativeQuery("SELECT  SKILL_ID FROM SKILL");

        List<BigDecimal> ids = query.getResultList();
        for(BigDecimal i: ids){
            System.out.println("EL VALOR = "+i);
        }
    }
    public static int[] readSkills(){
        List<BigDecimal> list;

        Query query = entityManager.createNativeQuery("SELECT SKILL_ID FROM SKILLS");
        list = query.getResultList();
        Collections.sort(list);
        int[] vec= new int[list.size()];
        for (int i = 0; i<list.size();i++){
            vec[i]=list.get(i).intValue();
        }

    return  vec;
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

        Skills sk = new Skills(BigInteger.valueOf(missingNumber()),skill, desc);
        entityManager.persist(sk);

        transaction.commit();
        System.out.println("Skill insertada con exito");
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

    public static List<Employee> getAllEmployees() {
        transaction.begin();
        List<Employee> results = entityManager.createNativeQuery("SELECT * FROM EMPLOYEE", Employee.class).getResultList();
        for(Employee e : results){
            System.out.println("EL EMPLEADO = "+ e.getName());
        }

        transaction.commit();
        return results;
    }
}
