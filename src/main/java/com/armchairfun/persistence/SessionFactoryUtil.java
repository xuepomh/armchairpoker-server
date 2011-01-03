package com.armchairfun.persistence;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

  private static org.hibernate.SessionFactory sessionFactory;

	private SessionFactoryUtil() {
	}

	static{

		sessionFactory = new Configuration().configure().buildSessionFactory();
  }

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void close(){
		if (sessionFactory != null)
			sessionFactory.close();
	}
}