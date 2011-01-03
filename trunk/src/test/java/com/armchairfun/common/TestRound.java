package com.armchairfun.common;

import junit.framework.TestCase;

public class TestRound extends TestCase{

	public void testStartRound() {
		Round round = new Round();
		round.setTable(new Table());
		assertTrue(round.getRoundStatus().equals(RoundStatus.NOT_STARTED));
		round.startRound();
		assertTrue(round.getRoundStatus().equals(RoundStatus.IN_PROGRESS));
	}
}
