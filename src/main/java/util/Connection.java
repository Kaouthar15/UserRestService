package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {
	public static SessionFactory factory;
	
	private Connection() {
		
	}
	
	public static synchronized SessionFactory getSessionFactory() {
		if (factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}
		System.out.println("connexion done");
		return factory;
	}
	
}
