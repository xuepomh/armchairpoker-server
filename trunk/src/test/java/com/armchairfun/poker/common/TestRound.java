package com.armchairfun.poker.common;

import com.armchairfun.poker.common.Round;
import com.armchairfun.poker.common.RoundStatus;
import com.armchairfun.poker.common.Table;

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
