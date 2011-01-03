package com.armchairfun.playingcards;

import junit.framework.TestCase;

public class TestPlayingCardDeckService extends TestCase {

	public void testGetStandardDeck() {
		PlayingCardDeckService service = new PlayingCardDeckService();
		PlayingCardDeck deck = service.getStandardDeck();
		assertTrue(deck.getNumberOfCards() == 52);
	}

	public void testGetShuffler() {
		PlayingCardDeckService service = new PlayingCardDeckService();
		PlayingCardShuffler shuffler = service.getShuffler();
		assertTrue(shuffler != null);
	}
}
