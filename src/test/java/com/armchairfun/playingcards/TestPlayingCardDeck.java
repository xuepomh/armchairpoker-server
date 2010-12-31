package com.armchairfun.playingcards;

import java.util.List;

import junit.framework.TestCase;

public class TestPlayingCardDeck extends TestCase {

	public void testFullCount() {
		PlayingCardDeck deck = new PlayingCardDeck();
		assertTrue(deck.fullDeckCount() == 52);
	}

	public void testIsDeckOk() {
		PlayingCardDeck deck = new PlayingCardDeck();
		assertTrue(deck.isDeckOk() == true);
		
		PlayingCard card = deck.removeBottomCard();
		assertTrue(deck.isDeckOk() == false);
	}
	
	public void testBadCardSuiteInDeck() {
		PlayingCardDeck deck = new PlayingCardDeck();
		List<PlayingCard> badCards = deck.subList(0, 51);
		badCards.add(new PlayingCard("13F"));
		try {
			PlayingCardDeck badDeck = new PlayingCardDeck(badCards);
		} catch (Exception e) {
			
		}
		
	}
	
	public void testBadCardValueInDeck() {
		PlayingCardDeck deck = new PlayingCardDeck();
		List<PlayingCard> badCards = deck.subList(0, 51);
		badCards.add(new PlayingCard("XS"));
		try {
			PlayingCardDeck badDeck = new PlayingCardDeck(badCards);
		} catch (Exception e) {
			
		}
		
	}
	
	public void testRemoveCardAt() {
		PlayingCardDeck deck = new PlayingCardDeck();
		deck.removeCardAt(0);
		assertTrue(deck.getNumberOfCards() == 51);
		deck.removeRest();
		assertTrue(deck.getNumberOfCards() == 0);
	}
}
