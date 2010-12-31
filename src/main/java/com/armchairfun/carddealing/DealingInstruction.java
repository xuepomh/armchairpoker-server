package com.armchairfun.carddealing;

/** 
 Copyright (C) 2008 Secure Card Dealer, LLC

 SCD Playing Cards

 This library is free software; you can redistribute it and/or modify it under the terms
 of the GNU Lesser General Public License as published by the Free Software Foundation; 
 either version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 See the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License along with this
 library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 Boston, MA 02111-1307 USA 

 Secure Card Dealer, LLC, hereby disclaims all copyright interest in the library 
 `scd-playingcards.jar' (a library for simulating electronic playing cards) written by 
 Secure Card Dealer, LLC.

 Secure Card Dealer, LLC
 http://www.securecarddealer.com
 info@securecarddealer.com
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Tells the card dealer how to deal the next round of cards
 * to the card holders. The name of the deal is given along with
 * the card's orientation.
 * 
 * A set of convenience methods are provided for dealing instructions
 * for various rounds of standard games like Hold'em, Omaha and Stud.
 * 
 * @author scdnick
 *
 */
public class DealingInstruction {
	public static final String COMMUNITY_CARD_1 = "COM CARD 1";
	public static final String COMMUNITY_CARD_2 = "COM CARD 2";
	public static final String COMMUNITY_CARD_3 = "COM CARD 3";
	public static final String COMMUNITY_CARD_4 = "COM CARD 4";
	public static final String COMMUNITY_CARD_5 = "COM CARD 5";
	public static final String PLAYER_CARD_1 = "PLR CARD 1";
	public static final String PLAYER_CARD_2 = "PLR CARD 2";
	public static final String PLAYER_CARD_3 = "PLR CARD 3";
	public static final String PLAYER_CARD_4 = "PLR CARD 4";
	public static final String PLAYER_CARD_5 = "PLR CARD 5";
	public static final String PLAYER_CARD_6 = "PLR CARD 6";
	public static final String PLAYER_CARD_7 = "PLR CARD 7";

	protected String dealName;
	protected String cardOrientation;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("dealName=" + dealName);
		buffer.append(", cardOrientation=" + cardOrientation);
		return buffer.toString();
	}

	public DealingInstruction(final String handCardName, final String handCardOrientation) {
		setDealName(handCardName);
		setCardOrientation(handCardOrientation);
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(final String dealName) {
		this.dealName = dealName;
	}

	public String getCardOrientation() {
		return cardOrientation;
	}

	public void setCardOrientation(final String cardOrientation) {
		this.cardOrientation = cardOrientation;
	}

	/**
	 * Create a list of dealing instructions for hold'em players.
	 * 
	 * @return
	 */
	public static final List<DealingInstruction> holdemPlayers() {
		List<DealingInstruction> instructions = new ArrayList<DealingInstruction>();
		instructions.add(new DealingInstruction(PLAYER_CARD_1, DealtCard.DOWNCARD));
		instructions.add(new DealingInstruction(PLAYER_CARD_2, DealtCard.DOWNCARD));
		return instructions;
	}
	
	/**
	 * Create a list of dealing instructions for omaha players.
	 * 
	 * @return
	 */
	public static final List<DealingInstruction> omahaPlayers() {
		List<DealingInstruction> instructions = new ArrayList<DealingInstruction>();
		instructions.add(new DealingInstruction(PLAYER_CARD_1, DealtCard.DOWNCARD));
		instructions.add(new DealingInstruction(PLAYER_CARD_2, DealtCard.DOWNCARD));
		instructions.add(new DealingInstruction(PLAYER_CARD_3, DealtCard.DOWNCARD));
		instructions.add(new DealingInstruction(PLAYER_CARD_4, DealtCard.DOWNCARD));
		return instructions;
	}

	/**
	 * Create a list of dealing instructions for the opening deal of a stud game.
	 * @return
	 */
	public static final List<DealingInstruction> studPlayers() {
		List<DealingInstruction> instructions = new ArrayList<DealingInstruction>();
		instructions.add(new DealingInstruction(PLAYER_CARD_1, DealtCard.DOWNCARD));
		instructions.add(new DealingInstruction(PLAYER_CARD_2, DealtCard.DOWNCARD));
		instructions.add(new DealingInstruction(PLAYER_CARD_3, DealtCard.UPCARD));
		return instructions;
	}

	/**
	 * Create a list of dealing instructions for a flop.
	 * 
	 * @return
	 */
	public static final List<DealingInstruction> flopInstructions() {
		List<DealingInstruction> instructions = new ArrayList<DealingInstruction>();
		instructions.add(new DealingInstruction(COMMUNITY_CARD_1, DealtCard.UPCARD));
		instructions.add(new DealingInstruction(COMMUNITY_CARD_2, DealtCard.UPCARD));
		instructions.add(new DealingInstruction(COMMUNITY_CARD_3, DealtCard.UPCARD));
		return instructions;
	}

	/**
	 * Create a list of instructions for the turn.
	 * 
	 * @return
	 */
	public static final List<DealingInstruction> turnInstructions() {
		List<DealingInstruction> instructions = new ArrayList<DealingInstruction>();
		instructions.add(new DealingInstruction(COMMUNITY_CARD_4, DealtCard.UPCARD));
		return instructions;
	}

	/**
	 * Create a list of instructions for the river.
	 * 
	 * @return
	 */
	public static final List<DealingInstruction> riverInstructions() {
		List<DealingInstruction> instructions = new ArrayList<DealingInstruction>();
		instructions.add(new DealingInstruction(COMMUNITY_CARD_5, DealtCard.UPCARD));
		return instructions;
	}

}