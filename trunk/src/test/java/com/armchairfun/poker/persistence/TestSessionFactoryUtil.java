package com.armchairfun.poker.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestSessionFactoryUtil extends ArmchairFunTestCase {

	public void testGetInstance() {
		SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
		assertNotNull(sessionFactory);
	}
	
	public void testOpenSession() {
		SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
		sessionFactory.openSession();
		assertTrue(sessionFactory.getCurrentSession().isOpen());
	}
	
	public void testClose() {
		assertFalse(SessionFactoryUtil.getInstance().isClosed());
		SessionFactoryUtil.close();
		assertTrue(SessionFactoryUtil.getInstance().isClosed());
	}
	
	public void testGetCurrentSession() {
		Session session = SessionFactoryUtil.getCurrentSession();
		assertNotNull(session);
		assertTrue(session.isOpen());
	}
	
}
