package org.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.project.entities.Demo;
import org.project.persistance.CustomPersistenceUnitInfo;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


//      EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); -> when using persistence.xml fil


        // for using persistence class instead of persistence.xml file
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

        EntityManager em = emf.createEntityManager(); // represents the context

        try {
            em.getTransaction().begin();

            Demo d = new Demo();

            d.setId(3L);
            d.setName("Hawawshi");

            em.persist(d); // add this to context  -> Not an insert query

            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }
}