package com.armchairfun.poker.common;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.poker.dao.ServerErrorException;

import junit.framework.TestCase;

public class TestPot extends TestCase {

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
		Pot pot = new Pot(new Round());
		assertTrue(pot.getPotValue().intValue() == 0);
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

}
