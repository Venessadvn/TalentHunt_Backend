package com.code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.code.entity.Contestant;

public class UpdateContestant {
    public UpdateContestant(SessionFactory sessionFactory, int contestantId, String updatedEmail) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Fetch the contestant by ID
        Contestant contestant = session.get(Contestant.class, contestantId);
        if (contestant != null) {
            // Update the email
            contestant.setEmail(updatedEmail);
            System.out.println("Email updated to: " + updatedEmail);
        } else {
            System.out.println("Contestant not found with ID: " + contestantId);
        }

        session.getTransaction().commit();
        session.close();
    }
}
