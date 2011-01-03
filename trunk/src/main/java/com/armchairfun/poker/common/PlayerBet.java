package com.armchairfun.poker.common;

import java.math.BigDecimal;

/**
 * Represents a bet made by a player, used for history tracking
 * 
 * @author cjohn
 * 
 */
public class PlayerBet {

	private User player;
	private BigDecimal amount;

	public PlayerBet(User player, BigDecimal amount) {
		this.player = player;
		this.amount = amount;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
