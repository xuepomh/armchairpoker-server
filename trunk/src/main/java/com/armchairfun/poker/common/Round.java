package com.armchairfun.poker.common;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.poker.dao.ServerErrorException;

public class Round {
	private int Id;
	private Table table;
	private List<User> roundPlayers;
	private List<User> roundWinner; // can be a shared pot
	private List<Pot> pots;
	private String roundStatus = RoundStatus.NOT_STARTED;

	/**
	 * Starts a new round by adding all eligible players, creating an empty pot
	 * and dealing out the hands.
	 * 
	 * @throws ServerErrorException
	 */
	public void startRound() throws ServerErrorException {
		// only the eligible players at the table should added to the round
		addEligiblePlayers();
		this.roundStatus = RoundStatus.IN_PROGRESS;
		createStartingPot();
		// work out the next dealer for the rotation
		assignDealer();
		dealPlayerCards();
	}

	public void addEligiblePlayers() throws ServerErrorException{
		if (table == null) {
			throw new ServerErrorException(ErrorIds.NO_TABLE_FOR_ROUND, "Round " + Id + " has no table");
		}
		roundPlayers = table.getAllEligiblePlayers();
		// we need at least two players to start the round
		if (roundPlayers == null || roundPlayers.size() < 2) {
			throw new ServerErrorException(
					ErrorIds.NOT_ENOUGH_PLAYERS_FOR_ROUND,
					"Not enough valid players to start a new round");
		}
	}
	
	public void assignDealer() throws ServerErrorException {
		// get the current dealer from the table
		Seat currentDealer = table.getCurrentDealer();
		if (currentDealer == null) {
			// use the first available dealer
			getFirstAvailableDealer();
			return;
		}
		// work out the next dealer by the next seat occupied by an eligible player
		for (Seat seat: table.getSeats()) {
			if (seat.getLocation() > currentDealer.location && roundPlayers.contains(seat.getPlayer())) {
				// found the next eligible seat
				currentDealer = seat;
				table.setCurrentDealer(currentDealer);
				return;
			}
		}
		// we've got to the last seat and not found the next dealer, so try at the beginning of the list
		getFirstAvailableDealer();
		
	}
	
	public void getFirstAvailableDealer() throws ServerErrorException {
		for (Seat seat: table.getSeats()) {
			if (roundPlayers.contains(seat.getPlayer())) {
				// found the next eligible player
				Seat currentDealer = seat;
				table.setCurrentDealer(currentDealer);
				return;
			}
		}
		// no dealer found at all, this is bad
		throw new ServerErrorException(ErrorIds.CANNOT_ASSIGN_DEALER, "Cannot assign dealer for the round " + Id);
	}
	
	public void addPlayer(User player) throws ServerErrorException {
		if (roundPlayers == null) {
			roundPlayers = new ArrayList<User>();
		}

		if (roundPlayers.contains(player)) {
			throw new ServerErrorException(ErrorIds.PLAYER_ALREADY_IN_ROUND,
					"Player " + player.getId() + " is already part of Round "
							+ Id);

		}
		roundPlayers.add(player);
	}

	public void createStartingPot() {
		// all valid round players should be able to place into this pot
		Pot pot = new Pot(this, roundPlayers);
		pots = new ArrayList<Pot>();
		pots.add(pot);
	}

	public void dealNextCommunalCard() {
		if (roundEnded()) {
			return;
		}
		// TODO!
		// get a card from the pack
		// validate we don't deal more than 3 communal cards
		// start the betting

	}
	
    protected void dealPlayerCards() {
    	// TODO! shuffle cards
    	// TODO! deal cards, starting with the one left from the dealer
    }

	private boolean roundEnded() {
		return RoundStatus.ENDED.equals(roundStatus);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<User> getRoundWinner() {
		return roundWinner;
	}

	public void setRoundWinner(List<User> roundWinner) {
		this.roundWinner = roundWinner;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public List<User> getRoundPlayers() {
		return roundPlayers;
	}

	public void setRoundPlayers(List<User> roundPlayers) {
		this.roundPlayers = roundPlayers;
	}

	public List<Pot> getPots() {
		return pots;
	}

}
