package com.armchairfun.poker.common;

import com.armchairfun.poker.common.Round;
import com.armchairfun.poker.common.RoundStatus;
import com.armchairfun.poker.common.Table;
import com.armchairfun.poker.dao.ServerErrorException;

import junit.framework.TestCase;

public class TestRound extends TestCase{

	public void testStartRound() {
		Table table = new Table();
		table.setBigBlind(600);
		User player1 = new User("user1", "pw1");
		User player2 = new User("user2", "pw2");
		player1.setPlayCash(600);
		player2.setPlayCash(1000);
		
		try {
			table.addPlayerToTable(player1);
			table.addPlayerToTable(player2);
			Round round = new Round();
			round.setTable(table);
			assertTrue(round.getRoundStatus().equals(RoundStatus.NOT_STARTED));
			round.startRound();
			assertTrue(round.getRoundStatus().equals(RoundStatus.IN_PROGRESS));
			assertTrue(round.getRoundPlayers().size() == 2);
			assertTrue(round.getPots().size() == 1);
			assertTrue(round.getPots().get(0).getPotValue().intValue() == 0);
		} catch (ServerErrorException e) {
			fail(e.toString());
		} catch (Exception e) {
			fail(e.toString());
		}

	}
}
