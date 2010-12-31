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
import com.armchairfun.playingcards.PlayingCard;

/**
 * A card that has been dealt to a card holding entity of a game.
 * The card holder can be a player, dealer or a community board.
 * The orientation of the card is denoted so that the game engine
 * knows how to distribute the card to the holder.
 * 
 * @author scdnick
 *
 */
public class DealtCard {
	private static final String SEP = ":";
	public static final String UPCARD = "U";
	public static final String DOWNCARD = "D";
	protected String cardHolder;
	protected String dealName;
	protected String orientation;
	protected PlayingCard card;

	public PlayingCard getCard() {
		return card;
	}

	public static DealtCard createDowncard(final String holderId, final String id, final PlayingCard card) {
		return new DealtCard(holderId, id, DOWNCARD, card);
	}

	public static DealtCard createUpcard(final String holderId, final String id, final PlayingCard card) {
		return new DealtCard(holderId, id, UPCARD, card);
	}

	private DealtCard(final String holderId, final String id, final String upOrDown, final PlayingCard card) {
		setCardHolder(holderId);
		setDealName(id);
		setOrientation(upOrDown);
		setCard(card);
	}

	public String toString() {
		return getCardHolder() + SEP + getDealName() + " (" + getOrientation() + ") "
				+ (isUpcard() ? card.toString() : "[DOWN_CARD]");
	}

	public String toStoredString() {
		return getCardHolder() + SEP + getDealName() + SEP + getOrientation() + SEP + card.toString();
	}

	private void setOrientation(String upOrDown) {
		this.orientation = upOrDown;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String cardId) {
		this.dealName = cardId;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setUpcard() {
		this.orientation = UPCARD;
	}

	public void setDowncard() {
		this.orientation = DOWNCARD;
	}

	public boolean isUpcard() {
		return UPCARD.equals(orientation);
	}

	public void setCard(PlayingCard cardValue) {
		this.card = cardValue;
	}

	public String getCardString() {
		return card.toString();
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
}
