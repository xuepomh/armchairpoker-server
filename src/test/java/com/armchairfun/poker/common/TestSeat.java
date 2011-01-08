package com.armchairfun.poker.common;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestSeat extends ArmchairFunTestCase {


	public void testConstructor() {
		User u1 = new User();
		u1.setId(1);
		Table t = new Table(2);
		Seat s = new Seat();
		s.setLocation(6);
		assertFalse(s.isOccupied());
		s.setPlayer(u1);
		s.setTable(t);
		assertTrue(s.isOccupied());
		assertEquals(6, s.getLocation());
		assertEquals(u1, s.getPlayer());
		assertEquals(t, s.getTable());
		
	}

}
