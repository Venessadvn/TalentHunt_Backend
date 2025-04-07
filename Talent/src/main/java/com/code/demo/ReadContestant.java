package com.code.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.code.entity.Contestant;

public class ReadContestant {

    private SessionFactory sessionFactory;

    public ReadContestant(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Fetch all contestants
            List<Contestant> contestants = session.createQuery("from Contestant", Contestant.class).getResultList();

            if (contestants.isEmpty()) {
                System.out.println("No Contestant records found.");
            } else {
                for (Contestant c : contestants) {
                    System.out.println(c);
                }
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
