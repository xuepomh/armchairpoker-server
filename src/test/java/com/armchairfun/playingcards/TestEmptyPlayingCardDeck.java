package com.armchairfun.playingcards;

import junit.framework.TestCase;

public class TestEmptyPlayingCardDeck extends TestCase {

	public void testFromEmptyString() {
		EmptyPlayingCardDeck deck = new EmptyPlayingCardDeck();
		deck.fromString("");
		assertTrue(deck.getNumberOfCards() == 0);
		deck.fromString("2S3S4S5S6S7S8S9S");
		assertTrue(deck.getNumberOfCards() == 8);
	}

}
