package com.armchairfun.playingcards;

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

import org.apache.log4j.Logger;

/**
 * A standard playing deck of 52 playing cards.

 *
 */
public class PlayingCardDeck extends ArrayList<PlayingCard> {
	private static final long serialVersionUID = -4862254673549616881L;
	protected Logger logger = Logger.getLogger(getClass());

	private static List<Character> valueList = new ArrayList<Character>();
	private static List<Character> suitList = new ArrayList<Character>();
	private static Character[] cardValues = { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A' };
	private static Character[] cardSuites = { 'S', 'C', 'H', 'D' };

	static {
		for (Character value : cardValues) {
			valueList.add(value);
		}
		for (Character suit : cardSuites) {
			suitList.add(suit);
		}
	}

	public static List<Character> cardSuites() {
		return suitList;
	}

	public static List<Character> cardValues() {
		return valueList;
	}

	public static int rankOf(PlayingCard card) {
		return valueList.indexOf(card.getCardValue());
	}

	/**
	 * This is the number of cards in this deck.
	 * 
	 * @return
	 */
	public static int fullDeckCount() {
		return cardSuites().size() * cardValues().size();
	}

	/**
	 * Return true if the value is valid for this kind of deck.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isValidValue(final Character value) {
		return cardValues().indexOf(value) >= 0;
	}

	/**
	 * Return true if this is a valid suit for this kind deck.
	 * 
	 * @param suite
	 * @return
	 */
	public static boolean isValidSuite(final Character suit) {
		return cardSuites().indexOf(suit) >= 0;
	}

	/**
	 * Return a brand new deck
	 */
	public PlayingCardDeck() {
		super();
		initialzeCards();
	}

	/**
	 * Return a deck with this list of cards.
	 * 
	 * @param cardList
	 * @throws Exception
	 */
	public PlayingCardDeck(List<PlayingCard> cardList) throws Exception {
		super(cardList);
		checkValidCards();
	}

	/**
	 * Does the deck contain a full number of cards and one of each card.
	 * 
	 * @return
	 */
	public boolean isDeckOk() {
		if (getNumberOfCards() != fullDeckCount()) {
			return false;
		}
		for (PlayingCard card : new PlayingCardDeck()) {
			if (countCardInstances(card) != 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Does the deck contain all valid playing cards.
	 * 
	 * @throws Exception
	 */
	private void checkValidCards() throws Exception {
		for (PlayingCard card : this) {
			if (!isValidSuite(card.getCardSuit())) {
				throw new Exception("Invalid Card Suite:  " + card.getCardSuit());
			}
			if (!isValidValue(card.getCardValue())) {
				throw new Exception("Invalid Card Value:  " + card.getCardValue());
			}
		}
	}

	/**
	 * Where is the card located in the deck.
	 * 
	 * @param card
	 * @return
	 */
	public int findCardIndex(final PlayingCard card) {
		return indexOf(card);
	}

	/**
	 * How many of the cards in the deck match this card.
	 * 
	 * @param card
	 * @return
	 */
	public int countCardInstances(final PlayingCard card) {
		int cardInstances = 0;
		for (PlayingCard nextCard : this) {
			if (nextCard.equals(card)) {
				cardInstances++;
			}
		}
		return cardInstances;
	}

	/**
	 * How many cards are in the deck.
	 * 
	 * @return
	 */
	public int getNumberOfCards() {
		return size();
	}

	/**
	 * Initialize the band new deck of cards.
	 */
	protected void initialzeCards() {
		for (Character suite : cardSuites()) {
			for (Character value : cardValues()) {
				add(new PlayingCard(value, suite));
			}
		}
	}

	/**
	 * Remove the bottom card from the deck.
	 * 
	 * @return
	 */
	public PlayingCard removeBottomCard() {
		if (size() <= 0) {
			return null;
		}
		return removeBottomCards(1).get(0);
	}

	/**
	 * Remove n number of cards from the bottom of the deck.
	 * 
	 * @param cardsToRemove
	 * @return
	 */
	public PlayingCardDeck removeBottomCards(final int cardsToRemove) {
		if (getNumberOfCards() < cardsToRemove) {
			return removeRest();
		}
		final PlayingCardDeck removedCards = new EmptyPlayingCardDeck();
		final int firstCardtoGet = getNumberOfCards() - cardsToRemove;
		removedCards.addAll(subList(firstCardtoGet, getNumberOfCards()));
		removeAll(removedCards);
		return removedCards;
	}

	/**
	 * Remove the remaining cards from the deck.
	 * 
	 * @return
	 */
	public PlayingCardDeck removeRest() {
		final PlayingCardDeck remainingCards = new EmptyPlayingCardDeck();
		remainingCards.addAll(this);
		clear();
		return remainingCards;
	}

	/**
	 * Remove the top card from the deck.
	 * 
	 * @return
	 */
	public PlayingCard removeTopCard() {
		if (size() <= 0) {
			return null;
		}
		return removeTopCards(1).get(0);
	}

	/**
	 * Remove n cards from the top of the deck.
	 * 
	 * @param cardsToRemove
	 * @return
	 */
	public PlayingCardDeck removeTopCards(final int cardsToRemove) {
		if (getNumberOfCards() <= cardsToRemove) {
			return removeRest();
		}
		final PlayingCardDeck removedCards = new EmptyPlayingCardDeck();
		removedCards.addAll(subList(0, cardsToRemove));
		removeAll(removedCards);
		return removedCards;
	}

	/**
	 * Return a string with all the cards (no separators)
	 */
	public String toString() {
		final StringBuffer cardString = new StringBuffer();
		for (PlayingCard nextCard : this) {
			cardString.append(nextCard.toString());
		}
		return cardString.toString();
	}

	/**
	 * Return a string with all the cards (no separators)
	 */
	public String asCSV() {
		final StringBuffer cardString = new StringBuffer();
		for (PlayingCard nextCard : this) {
			cardString.append(nextCard.toString()).append(",");
		}
		return cardString.substring(0, cardString.length());
	}

	/**
	 * Remove card at a certain position
	 * 
	 * @param cardPosition
	 * @return
	 */
	public PlayingCard removeCardAt(final int cardPosition) {
		return remove(cardPosition);
	}
}
