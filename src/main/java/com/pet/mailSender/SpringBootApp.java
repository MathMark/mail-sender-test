package com.pet.mailSender;

import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.PeopleList;
import com.pet.mailSender.model.Person;
import com.pet.mailSender.model.Template;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

@SpringBootApplication
public class SpringBootApp {
    public static void main(String...args){
        //SpringApplication.run(SpringBootApp.class, args);

        /*Greeter greetJpa = new Greeter();
        greetJpa.setGreeting("Hello");
        greetJpa.setTarget("JPA");

        Greeter greetHibernate = new Greeter();
        greetHibernate.setGreeting("Hello");
        greetHibernate.setTarget("Hibernate");*/

        Person vadim = new Person();
        vadim.setFirstName("Vadim");
        vadim.setLastName("Martsun");
        vadim.setEmail("vadimmartsun@gmail.com");

        Person mark = new Person();
        mark.setFirstName("Mark");
        mark.setLastName("Martsun");
        mark.setEmail("mark.netpeak@gmail.com");

        PeopleList peopleList = new PeopleList();
        peopleList.setTitle("Test");
        vadim.setPeopleList(peopleList);
        mark.setPeopleList(peopleList);

        Template template = new Template();
        template.setSubject("fdf");
        template.setBody("This is body");
        template.setTitle("Test template");

        Campaign campaign = new Campaign();
        campaign.setTitle("Test campaign");
        campaign.setDelay(2000L);
        campaign.setTemplate(template);

        campaign.setPeopleList(peopleList);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.pet.mailSender.model");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vadim);
        em.persist(mark);
        em.persist(campaign);
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campaign> campaigns = em.createQuery("from Campaign", Campaign.class)
                .getResultList();
        campaigns.forEach(c -> System.out.println(c));
        em.getTransaction().commit();
        em.close();

    }
}
