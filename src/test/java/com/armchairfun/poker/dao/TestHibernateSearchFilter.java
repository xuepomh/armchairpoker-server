package com.armchairfun.poker.dao;

import org.hibernate.criterion.Criterion;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestHibernateSearchFilter extends ArmchairFunTestCase {


	public void testConstructor() {
		HibernateSearchFilter filter = new HibernateSearchFilter("name", "=", "value");
		assertNotNull(filter);
		assertEquals(filter.getName(), "name");
		assertEquals(filter.getOperation(), "=");
		assertEquals(filter.getValue(), "value");
		assertEquals(filter.toString(), "name = value");
	}
	
	public void testGetRestriction() {
		HibernateSearchFilter filter = new HibernateSearchFilter("name", "%", "value");
		try {
			filter.getRestriction();
			fail("Expected a ServerErrorException when getting a restriction for " + filter.toString());
		} catch (Exception e) {
			assertTrue(e instanceof ServerErrorException);
		}
	}
	
	public void testSetters() {
		String testName = "name";
		String testOp = "=";
		String testValue = "testValue";
		
		HibernateSearchFilter filter = new HibernateSearchFilter();
		filter.setName(testName);
		filter.setOperation(testOp);
		filter.setValue(testValue);
		assertEquals(filter.getName(), testName);
		assertEquals(filter.getOperation(), testOp);
		assertEquals(filter.getValue(), testValue);
	}

}
