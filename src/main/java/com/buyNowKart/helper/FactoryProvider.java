package com.buyNowKart.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
	public static SessionFactory factory;
	public static SessionFactory getSessionFactory() {		
		if(factory == null) {
			try {
				factory = new Configuration().configure().buildSessionFactory();
			}
			catch (Exception e) {
				System.out.println("error in FactoryProvider HibernateSession : "+e.getMessage());
			}
		}
		return factory;
	}
}
