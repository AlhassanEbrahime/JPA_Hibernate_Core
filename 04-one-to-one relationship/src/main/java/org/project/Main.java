package org.project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.project.entities.Passport;
import org.project.entities.Person;
import org.project.entities.User;
import org.project.persistance.CustomPersistenceUnitInfo;

import javax.naming.ldap.PagedResultsControl;
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


        EntityManager em = emf.createEntityManager();
        try{

            em.getTransaction().begin();

            Person person = new Person();
            person.setName("hassan");

            Passport passport = new Passport();
            passport.setNumber("ABC123");

            person.setPassport(passport);
            passport.setPerson(person);

            em.persist(person);
            em.persist(passport);

            TypedQuery<Person> q = em.createQuery("select p from Person  p where p.passport.number= :number", Person.class);
            q.setParameter("number", "ABC123");
            System.out.println(q.getResultList());


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}





























