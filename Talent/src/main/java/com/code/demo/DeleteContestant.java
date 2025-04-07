package com.code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.code.entity.Contestant;

public class DeleteContestant {

    private SessionFactory sessionFactory;

    public DeleteContestant(SessionFactory sessionFactory, int contestantId) {
        this.sessionFactory = sessionFactory;

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Find and delete
            Contestant contestant = session.get(Contestant.class, contestantId);

            if (contestant != null) {
                session.remove(contestant);
                System.out.println("Deleted contestant with ID: " + contestantId);
            } else {
                System.out.println("No contestant found with ID: " + contestantId);
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
