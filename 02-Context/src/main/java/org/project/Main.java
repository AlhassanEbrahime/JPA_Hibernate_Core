package org.project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.project.entities.Employee;
import org.project.persistance.CustomPersistenceUnitInfo;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName ="pu-name";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","update");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);
        EntityManager em = emf.createEntityManager(); // represents the context
        try{
            em.getTransaction().begin();
//            var e1 = em.getReference(Employee.class, 1);
//            e1.setName("name");
//            System.out.println("Before" + e1);
//            em.refresh(e1);
//            System.out.println("After" + e1);
//            em.persist();     -> Adding an entity in the context
//            em.find();        -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();      -> Marking entity from removal
//            em.merge();       -> Merges an entity from outside the context in the context
//            em.refresh();     -> Mirror the context from the database
//            em.detach();      -> Taking the entity out of the context
//            em.getReference() -> only issues a query if something used the instance (Lazy)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}