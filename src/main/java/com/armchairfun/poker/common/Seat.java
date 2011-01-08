package com.armchairfun.poker.common;

/**
 * Represents a space on the table. Table has many seats, each seat may be occupied by no more
 * than one player
 * @author cjohn
 *
 */
public class Seat {

	Table table;
	int location;
	User player;
	
	public Seat() {
		
	}
	
	public Seat(Table table, int location) {
		this.table = table;
		this.location = location;
	}

	public boolean isOccupied() {
		if (player == null) {
			return false;
		} else {
			return true;
		}
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}
	
	
}
