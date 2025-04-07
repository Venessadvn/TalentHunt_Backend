package com.code.demo;

import org.hibernate.SessionFactory;

import javax.sound.midi.Instrument;
import org.hibernate.cfg.Configuration;

import com.code.entity.Contestant;
import com.code.entity.Evaluation;
import com.code.entity.Judge;
import com.code.entity.Result;
import com.code.entity.Submission;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       // System.out.println( "Hello World!" );
    	 SessionFactory sessionFactory=   new Configuration()
     			.configure("hibernate.cfg.xml")
     			.addAnnotatedClass(Contestant.class)
     			.addAnnotatedClass(Judge.class)
     			.addAnnotatedClass(Submission.class)
     			.addAnnotatedClass(Result.class)
     			.addAnnotatedClass(Evaluation.class)
     			.buildSessionFactory();
    	 
    	 //new CreateContestant(sessionFactory);
    	 //new ReadContestant(sessionFactory);
    	// new UpdateContestant(sessionFactory); 
    	 new DeleteContestant(sessionFactory);

    }
}
