package com.buyNowKart.dao;

import org.hibernate.Session;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import com.buyNowKart.entities.User;

public class UserDao {
	private SessionFactory sessionFactory;
	
	public UserDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public User getUserByEmailAndPassword(String userEmail,String userPassword) {
		User user = null;
		
		try {

			Session session = this.sessionFactory.openSession();
			
			String jpql = "SELECT u FROM User u WHERE u.email = :userEmail AND u.password = :userPassword";
			TypedQuery<User> query = session.createQuery(jpql, User.class);
			query.setParameter("userEmail", userEmail);
			query.setParameter("userPassword", userPassword);
			user = query.getSingleResult();
			session.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	
}
