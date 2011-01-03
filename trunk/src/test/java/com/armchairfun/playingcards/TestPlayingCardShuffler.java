package com.armchairfun.playingcards;

import java.util.Random;

import junit.framework.TestCase;

public class TestPlayingCardShuffler extends TestCase {

	public void testPerfectCut() {
		int cutPosition = new PlayingCardShuffler()
				.perfectCut(new PlayingCardDeck());
		assertNotNull(cutPosition);
		assertEquals(cutPosition, 26);
	}
	
	public void testPerfectCutBadAlgorithm() {
		int cutPosition = new PlayingCardShuffler("bad algorithm")
				.perfectCut(new PlayingCardDeck());
		assertNotNull(cutPosition);
		assertEquals(cutPosition, 26);
	}

	public void testFisherYatesOriginal() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardDeck originalDeck = (PlayingCardDeck)deck.clone();
		assertTrue(deck.isDeckOk());
		new PlayingCardShuffler()
				.fisherYatesOriginal(deck);
		assertTrue(deck.isDeckOk());
		assertFalse(areDecksEqual(originalDeck, deck));
	}

	public void testFisherYatesModern() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardDeck originalDeck = (PlayingCardDeck)deck.clone();
		assertTrue(deck.isDeckOk());
		new PlayingCardShuffler()
				.fisherYatesModern(deck);
		assertTrue(deck.isDeckOk());
		assertFalse(areDecksEqual(originalDeck, deck));
	}
	
	public void testRiffleShuffle() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardDeck originalDeck = (PlayingCardDeck)deck.clone();
		assertTrue(deck.isDeckOk());
		new PlayingCardShuffler()
				.riffleShuffle(deck);
		assertTrue(deck.isDeckOk());
		assertFalse(areDecksEqual(originalDeck, deck));
	}
	
	public void testStripShuffleBottom() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardDeck originalDeck = (PlayingCardDeck)deck.clone();
		assertTrue(deck.isDeckOk());
		new PlayingCardShuffler()
				.stripShuffleBottom(deck);
		assertTrue(deck.isDeckOk());
		assertFalse(areDecksEqual(originalDeck, deck));
	}
	
	public void testStripShuffleTop() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardDeck originalDeck = (PlayingCardDeck)deck.clone();
		assertTrue(deck.isDeckOk());
		new PlayingCardShuffler()
				.stripShuffleTop(deck);
		assertTrue(deck.isDeckOk());
		assertFalse(areDecksEqual(originalDeck, deck));
	}
	
	public void testSetFullShufflesToRun() {
		int shufflesToRun = 9;
		PlayingCardShuffler shuffler = new PlayingCardShuffler();
		shuffler.setFullShufflesToRun(shufflesToRun);
		assertEquals(shufflesToRun, shuffler.getFullShufflesToRun());
	}
	
	public void testSetRandom() {
		PlayingCardShuffler shuffler = new PlayingCardShuffler();
		Random random = new Random();
		shuffler.setRandom(random);
		
	}
	
	public void testFullShuffle() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardDeck originalDeck = (PlayingCardDeck)deck.clone();
		PlayingCardShuffler shuffler = new PlayingCardShuffler();
		shuffler.setFullShufflesToRun(100);
		shuffler.fullShuffle(deck);
		assertFalse(areDecksEqual(originalDeck, deck));
	}
	
	public void testSetAlgorithm() {
		String algorithm = "madeUpAlgorithm";
		PlayingCardShuffler shuffler = new PlayingCardShuffler();
		shuffler.setAlgorithm(algorithm);
		assertEquals(algorithm, shuffler.getAlgorithm());
	}
	
	private boolean areDecksEqual(PlayingCardDeck deck1, PlayingCardDeck deck2) {
		for (int i = 0; i < deck1.size(); i++) {
			if (!areCardsEqual(deck1.get(i), deck2.get(i)));
				return false;
		}
		return true;
	}

	private boolean areCardsEqual(PlayingCard card1, PlayingCard card2) {
		return (card1.getCardSuit().equals(card2.getCardSuit()) && card1
				.getCardValue().equals(card2.getCardValue()));
	}
}
