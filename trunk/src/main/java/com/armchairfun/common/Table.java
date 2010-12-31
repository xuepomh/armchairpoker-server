package com.armchairfun.common;

import java.util.ArrayList;
import java.util.List;

public class Table implements XMLDescribable
{
    private int id;
	private List<User> players;
    private User dealer;
    private int smallBlind;
    private int bigBlind;
    private int tableSize = TableConstants.DEFAULT_TABLE_SIZE;
    
    public Table() {
    	this.players = new ArrayList<User>();
    }
    
    public Table(String xml) {
    	//TODO construct a table from xml
    }
    
    public void addPlayerToTable(User player) {
    	// TODO if not full and not already at table then add
    	players.add(player);
    	
    }
    
    public void initialize() {
    	this.dealer = players.get(0);
    }
    
    protected void dealPlayerCards() {
    	// deal cards, starting with the one left from the dealer
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
