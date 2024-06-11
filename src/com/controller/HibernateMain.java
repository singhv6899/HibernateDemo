package com.controller;

import org.hibernate.cfg.Configuration;

import com.transferObject.Player;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateMain {

	public static void main(String[] args) {
		try {
		Configuration  configuration = new Configuration().configure();
		configuration.addAnnotatedClass(com.transferObject.Player.class);
		StandardServiceRegistryBuilder builder = 
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Player p1 = new Player("Vishal", "RCB", 24);
		Player p2 = new Player("Kohli", "RCB", 34);
		Player p3 = new Player("Rohit", "MI", 38);
		Player p4 = session.get(Player.class, 1);
		System.out.println("Player Details of "+p4.getPlayerId()+" is: "+p4);
		p4.setPlayerName("Dhoni");
		p4.setAge(40);
		p4.setTeamName("CSK");
		System.out.println("Player Details of "+p4.getPlayerId()+" is: "+p4);
		Player p5 = session.get(Player.class, 3);
		session.delete(p5);
		session.save(p1);
		session.save(p2);
		session.save(p3);
		session.save(p4);
		session.save(p5);
		transaction.commit();
		session.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}