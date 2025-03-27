package org.project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.project.entities.Product;
import org.project.entities.Student;
import org.project.entities.keys.StudentKey;
import org.project.persistance.CustomPersistenceUnitInfo;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName ="pu-name";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","create");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);
        EntityManager em = emf.createEntityManager(); // represents the context
        try{
//            Product p = new Product();
//            p.setCode("ABC");
//            p.setNumber(10);
//            p.setColor("RED");
//            em.persist(p);

//            StudentKey id = new StudentKey();
//
//            id.setCode("ABC");
//            id.setNumber(10);
//
//            Student s = new Student();
//            s.setId(id);
//            s.setName("hasan");
//            em.persist(s);
            em.getTransaction().begin();


            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}





























