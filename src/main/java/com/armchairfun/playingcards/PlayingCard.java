package com.armchairfun.playingcards;

import org.apache.log4j.Logger;

/**
 * A simple playing card with a character for the suit and one for the value A-K.
 *
 */
public class PlayingCard implements Comparable<PlayingCard> {
	protected static final Logger logger = Logger.getLogger(PlayingCard.class);
	private Character suit;
	private Character value;

	public PlayingCard(final Character cardValue, final Character cardSuite) {
		super();
		setCardValue(cardValue);
		setCardSuit(cardSuite);
	}

	public PlayingCard(final String valueSuite) {
		super();
		if (valueSuite.length() < 2)
			throw new RuntimeException("Invalid parameter <" + valueSuite + ">");

		setCardValue(new Character(valueSuite.charAt(0)));
		setCardSuit(new Character(valueSuite.charAt(1)));
	}

	public Character getCardSuit() {
		return suit;
	}

	public final void setCardSuit(final Character suite) {
		this.suit = suite;
	}

	public Character getCardValue() {
		return value;
	}

	public final void setCardValue(final Character value) {
		this.value = value;
	}

	public boolean equals(final Object card) {
		if (card instanceof PlayingCard) {
			final PlayingCard otherCard = (PlayingCard) card;
			return sameSuite(otherCard) && sameValue(otherCard);
		}
		return false;
	}

	public boolean sameSuite(final PlayingCard card) {
		return getCardSuit().equals(card.getCardSuit());
	}

	public boolean sameValue(final PlayingCard card) {
		return getCardValue().equals(card.getCardValue());
	}

	public String toString() {
		return getCardValue().toString() + getCardSuit().toString();
	}

	public int compareTo(final PlayingCard o) {
		return PlayingCardDeck.rankOf(this) - PlayingCardDeck.rankOf(o);
	}

}
