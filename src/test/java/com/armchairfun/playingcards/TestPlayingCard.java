package com.armchairfun.playingcards;

import junit.framework.TestCase;

public class TestPlayingCard extends TestCase {

	public void testConstructor() {
		PlayingCard card = new PlayingCard("7H");
		assertTrue(card.getCardSuit().equals('H'));
		assertTrue(card.getCardValue().equals('7'));
	}

	public void testCompare() {
		PlayingCard card1 = new PlayingCard("7H");
		PlayingCard card2 = new PlayingCard("8H");
		assertTrue(card2.compareTo(card1) == 1);
		assertFalse(card1.equals(""));
	}

	public void testBadCard() {
		PlayingCard badCard;
		try {
			badCard = new PlayingCard("X");
		} catch (RuntimeException e) {

		} 
	}
}
