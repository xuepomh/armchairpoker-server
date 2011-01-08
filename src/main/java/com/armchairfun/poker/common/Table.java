package com.armchairfun.poker.common;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.poker.dao.ServerErrorException;

public class Table implements XMLDescribable
{
    private int id;
	private List<User> players;
    private int smallBlind;
    private int bigBlind;
    private int tableSize;
    private int port;
    private List<Seat> seats;
    private Seat currentDealer; // marks the current dealer
    
    public Table() {
    	this.players = new ArrayList<User>();
    	this.tableSize = TableConstants.DEFAULT_TABLE_SIZE;
    	seats = new ArrayList<Seat>();
    	generateSeats();
    	
    }
    
    public Table(int tableSize) {
    	this.players = new ArrayList<User>();
    	this.tableSize = tableSize;
    	seats = new ArrayList<Seat>();
    	// create each seat for the table
    	generateSeats();
    	
    }
    
    public void generateSeats() {
    	for (int i = 0; i < tableSize; i++) {
    		Seat seat = new Seat(this, i);
    		seats.add(seat);
    	}
    }
    
    public void addPlayerToTable(User player) throws Exception {
    	
    	if (isFull()) {
    	throw new Exception("Cannot add a player to a full table. Table size: " + tableSize + " playerSize: " + players.size());
    	}
    	// find an empty seat for the new player
    	putPlayerInNextEmptySeat(player);
    	players.add(player);
    	
    }

    public boolean isFull() {
    	return (players.size() >= tableSize);
    }
    
    public void putPlayerInNextEmptySeat(User player) throws ServerErrorException {
    	for (Seat seat: seats) {
    		if (!seat.isOccupied()) {
    			seat.setPlayer(player);
    			return;
    		}
    	}
    	throw new ServerErrorException(ErrorIds.TABLE_FULL, "Table " + id + " is full! max size:" + tableSize);
    }
    
    public Seat getCurrentDealer() {
    	return currentDealer;
    }
    
    
    /**
     * A round requires a list of all eligible players, i.e. players that don't have enough for
     * the minimum bet shouldn't be added to the new round
     * @return
     */
    public List<User> getAllEligiblePlayers() {
    	if (players == null) {
    		return null;
    	}
    	
    	List<User> eligiblePlayers = new ArrayList<User>();
    	for (User player : players) {
    		if (player.getPlayCash() >= bigBlind) {
    			eligiblePlayers.add(player);
    		}
    	}
    	return eligiblePlayers;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTableSize() {
		return tableSize;
	}
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}
	public List<User> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<User> players) {
		this.players = players;
	}
	public int getSmallBlind() {
		return smallBlind;
	}
	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
	}
	public int getBigBlind() {
		return bigBlind;
	}
	public void setBigBlind(int largeBlind) {
		this.bigBlind = largeBlind;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	
	public List<Seat> getSeats() {
		return seats;
	}

	public void setCurrentDealer(Seat currentDealer) {
		this.currentDealer = currentDealer;
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t\t");
		sb.append("<table");
		sb.append(" id=\"");
		sb.append(id);
		sb.append("\"");
		sb.append(" smallBlind=\"");
		sb.append(smallBlind);
		sb.append("\"");
		sb.append(" bigBlind=\"");
		sb.append(bigBlind +"\"");
		sb.append(" tableSize=\"");
		sb.append(tableSize);
		sb.append("\"");
		sb.append("</>");
		return sb.toString();
	}
    
}
