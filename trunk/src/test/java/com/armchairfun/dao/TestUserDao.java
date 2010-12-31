package com.armchairfun.dao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.armchairfun.common.User;
import com.armchairfun.dao.UserDao;
import com.armchairfun.dao.UserNotFoundException;

public class TestUserDao extends TestCase {
	
	UserDao userDao = new UserDao();

	public TestUserDao(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TestUserDao.class);
	}

	public void testGetUser() throws Exception {
		User user = userDao.getUser("cjohn", "password");
		assertTrue(user != null);
		assertTrue(user.getUsername().equals("cjohn"));
		System.out.println(user.toXML());
	}

	public void testGetUserNotFound() {
		User user;
		try {
			user = userDao.getUser("cjohn2", "password2");
			assertTrue(user != null);
			assertTrue(user.getUsername().equals("cjohn"));
			fail("expected a UserNotFoundException for user cjohn2");
		} catch (UserNotFoundException e) {
			System.out.println(e.toString());
		}

	}
}
