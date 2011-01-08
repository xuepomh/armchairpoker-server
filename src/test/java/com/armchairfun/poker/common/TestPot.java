package com.armchairfun.poker.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestPot extends ArmchairFunTestCase {

	public List<User> createTestPlayerList() {
		User player1 = createTestPlayer(1);
		User player2 = createTestPlayer(2);
		List<User> allPlayers = new ArrayList<User>();
		allPlayers.add(player1);
		allPlayers.add(player2);
		return allPlayers;
	}

	public User createTestPlayer(int id) {
		User player = new User();
		player.setId(id);
		return player;
	}

	public void testCreatePot() {
		Round r = new Round();
		Pot pot = new Pot(r);
		assertTrue(pot.getPotValue().intValue() == 0);
		assertTrue(pot.getRound().equals(r));
	}

	public void testAddToPot() {
		List<User> allPlayers = createTestPlayerList();
		Round round = new Round();
		int bidIncrement = 100;
		Pot pot = new Pot(round, allPlayers);
		pot.addBetToPot(allPlayers.get(0), bidIncrement);
		assertTrue(pot.getPotValue().intValue() == bidIncrement);
		pot.addBetToPot(allPlayers.get(1), bidIncrement);
		assertTrue(pot.getPotValue().intValue() == bidIncrement * 2);

	}

	public void testPotHistory() {
		List<User> allPlayers = createTestPlayerList();
		int bidIncrement = 100;
		Pot pot = new Pot(new Round(), allPlayers);
		pot.addBetToPot(allPlayers.get(0), bidIncrement);
		pot.addBetToPot(allPlayers.get(1), bidIncrement);
		pot.addBetToPot(allPlayers.get(0), bidIncrement);
		pot.addBetToPot(allPlayers.get(1), bidIncrement);
		pot.addBetToPot(allPlayers.get(0), bidIncrement);
		pot.addBetToPot(allPlayers.get(1), bidIncrement);
		pot.addBetToPot(allPlayers.get(0), bidIncrement);
		assertTrue(pot.getPotHistory().size() == 7);

	}
	
	public void testGetterSetters() {
		Pot p = new Pot(new Round());
		Pot parentPot = new Pot(new Round());
		p.setParentPot(parentPot);
		assertEquals(parentPot, p.getParentPot());
		User u1 = createTestPlayer(1);
		User u2 = createTestPlayer(2);
		List<User> players = new ArrayList<User>();
		players.add(u1);
		players.add(u2);
		p.setPlayers(players);
		assertEquals(players, p.getPlayers());
		p.setPotValue(new BigDecimal(1000));
		assertEquals(1000, p.getPotValue().intValue());
		
		PlayerBet bet1 = new PlayerBet(u1, new BigDecimal(100));
		List<PlayerBet> bets = new ArrayList<PlayerBet>();
		bets.add(bet1);
		p.setPotHistory(bets);
		assertTrue(p.getPotHistory().size() == 1);
		Round r1 = new Round();
		r1.setId(123);
		p.setRound(r1);
		assertEquals(r1, p.getRound());
	}

}
