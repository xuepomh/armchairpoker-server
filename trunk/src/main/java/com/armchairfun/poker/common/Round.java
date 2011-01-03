package com.armchairfun.poker.common;

import java.math.BigDecimal;
import java.util.List;

public class Round 
{
	private int Id;
    private Table table;
    private List<User> roundWinner; // can be a shared pot
    private BigDecimal pot;
    private String roundStatus = RoundStatus.NOT_STARTED;
    
    public void startRound() {
    	this.pot = new BigDecimal(0);
    	this.roundStatus = RoundStatus.IN_PROGRESS;
    	table.dealPlayerCards();
    }
    
    public void dealNextCommunalCard() {
    	if (roundEnded()) {
    		return;
    	}
    	
    	// get a card from the pack
    	// validate we don't deal more than 3 communal cards
    	// start the betting
    	
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

	public BigDecimal getPot() {
		return pot;
	}

	public void setPot(BigDecimal pot) {
		this.pot = pot;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}
    
    
}
