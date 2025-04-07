package com.code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.code.entity.Contestant;

public class CreateContestant {

    private SessionFactory sessionFactory;

    public CreateContestant(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Create contestant objects
            Contestant c1 = new Contestant("Riya Sen", "riya@gmail.com", "Dance");
            Contestant c2 = new Contestant("Amit Roy", "amit@gmail.com", "Singing");

            // Save to database
            session.persist(c1);
            session.persist(c2);

            session.getTransaction().commit();
            System.out.println("Contestants created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
