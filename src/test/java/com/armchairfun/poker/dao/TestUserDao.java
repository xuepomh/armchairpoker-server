package com.armchairfun.poker.dao;

import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.common.User;

public class TestUserDao extends ArmchairFunTestCase {

	UserDao userDao = new UserDao();

	public void testGetUser() throws Exception {
		User user = userDao.getUser("cjohn", "password");
		String expectedString = "0: cjohn";
		String expectedXml = "<user id=\"0\" username=\"cjohn\" playCash=\"500\" email=\"cjohn@gmail.com\" />";
		assertTrue(user != null);
		assertTrue(user.getUsername().equals("cjohn"));
		assertEquals(expectedString, user.toString());
		assertEquals(expectedXml, user.toXML());
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
