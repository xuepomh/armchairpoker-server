package com.armchairfun.poker.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents bets placed by players, tied to a particular Round of a Table. A
 * table can have many rounds, each round must have at LEAST one Pot, but can
 * have more than one Pot (split pot).
 * 
 * @author cjohn
 * 
 */
public class Pot {

	private BigDecimal potValue;
	private Round round;
	private Pot parentPot; // if a pot is a result from a split pot
	private List<User> players;
	private List<PlayerBet> potHistory;

	public Pot(Round round) {
		this.round = round;
		this.potValue = new BigDecimal(0);
		this.potHistory = new ArrayList<PlayerBet>();
	}

	public Pot(Round round, List<User> players) {
		this.round = round;
		this.potValue = new BigDecimal(0);
		this.players = players;
		this.potHistory = new ArrayList<PlayerBet>();
	}

	/**
	 * Adds a bet from a player to the current pot after validating that the
	 * player is eligible to bet
	 */
	public void addBetToPot(User player, int amount) {
		if (players.contains(player)) {
			BigDecimal bdAmount = new BigDecimal(amount);
			potValue = potValue.add(bdAmount);
			// store the bet in the pot history
			potHistory.add(new PlayerBet(player, bdAmount));
		}
	}

	public BigDecimal getPotValue() {
		return potValue;
	}

	public void setPotValue(BigDecimal potValue) {
		this.potValue = potValue;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Pot getParentPot() {
		return parentPot;
	}

	public void setParentPot(Pot parentPot) {
		this.parentPot = parentPot;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public List<PlayerBet> getPotHistory() {
		return potHistory;
	}

	public void setPotHistory(List<PlayerBet> potHistory) {
		this.potHistory = potHistory;
	}

}
