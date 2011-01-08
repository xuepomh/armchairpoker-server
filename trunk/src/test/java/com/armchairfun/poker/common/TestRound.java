package com.armchairfun.poker.common;

import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.dao.ServerErrorException;

public class TestRound extends ArmchairFunTestCase{

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
	
	public void testAssignDealer() {
		Table table = new Table();
		table.setBigBlind(100);
		table.setTableSize(4);
		User player1 = new User("user1", "pw1");
		player1.setId(1);
		User player2 = new User("user2", "pw2");
		player2.setId(2);
		User player3 = new User("user3", "pw3");
		player3.setId(3);
		User player4 = new User("user4", "pw4");
		player4.setId(4);
		player1.setPlayCash(500);
		player2.setPlayCash(1000);
		player3.setPlayCash(2000);
		player4.setPlayCash(4000);
		
		try {
			table.addPlayerToTable(player1);
			table.addPlayerToTable(player2);
			table.addPlayerToTable(player3);
			table.addPlayerToTable(player4);
			Round round = new Round();
			round.setTable(table);
			assertTrue(round.getRoundStatus().equals(RoundStatus.NOT_STARTED));
			round.startRound();
			// player 1 should be in first seat position, and be the current dealer
			Seat dealerSeat = round.getTable().getSeats().get(0);
			assertTrue(dealerSeat.getPlayer().equals(player1));
			assertTrue(round.getTable().getCurrentDealer().equals(dealerSeat));
			// now try the next dealer, should be player 2
			round.assignDealer();
			dealerSeat = round.getTable().getCurrentDealer();
			assertTrue(dealerSeat.getPlayer().equals(player2));
			// now try the next dealer, should be player 3
			round.assignDealer();
			dealerSeat = round.getTable().getCurrentDealer();
			assertTrue(dealerSeat.getPlayer().equals(player3));
			// now try the next dealer, should be player 4
			round.assignDealer();
			dealerSeat = round.getTable().getCurrentDealer();
			assertTrue(dealerSeat.getPlayer().equals(player4));
			// now try the next dealer, should loop back to start and be player 1
			round.assignDealer();
			dealerSeat = round.getTable().getCurrentDealer();
			assertTrue(dealerSeat.getPlayer().equals(player1));
		} catch (ServerErrorException e) {
			fail(e.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	public void testAddAllEligiblePlayers() {
		Round r = new Round();
		try {
			r.addEligiblePlayers();
			fail("Expected ServerErrorException when round has no table");
		} catch (ServerErrorException e) {
			assertEquals(ErrorIds.NO_TABLE_FOR_ROUND, e.getErrorId());
		}
		
		try {
			r.setTable(new Table(0));
			r.addEligiblePlayers();
			fail("Expected ServerErrorException when not enough players for round");
		} catch (ServerErrorException e) {
			assertEquals(ErrorIds.NOT_ENOUGH_PLAYERS_FOR_ROUND, e.getErrorId());
		}
	}
}
