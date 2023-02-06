import entidad.TestdosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oracle.Empleados;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {


            TestdosEntity emp = new TestdosEntity();
            emp.setDni("X3785910K");
            emp.setNombreEmp("EjemploCargo");
            emp.setTelefono("TestiNombre");


            //emp.setCodOfi(BigInteger.valueOf(2));
            System.out.println(emp.getDni()+ "    AAAAAAAAAAAAAAAAAAAAA");
            entityManager.persist(emp);


            transaction.commit();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();



        }


    }
}
