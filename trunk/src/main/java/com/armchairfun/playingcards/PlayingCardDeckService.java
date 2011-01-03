package com.armchairfun.playingcards;

import org.apache.log4j.Logger;

/**
 * Retrieve a new deck of cards. Return a shuffled and cut deck. Log a server
 * error.
 * 
 * @param roomId
 * @param tableId
 * @param handId
 * @return
 */
public class PlayingCardDeckService {

	protected Logger logger = Logger.getLogger(this.getClass());
	protected PlayingCardShuffler shuffler = new PlayingCardShuffler();

	public PlayingCardDeck getStandardDeck() {
		PlayingCardDeck cardDeck = new PlayingCardDeck();
		shuffler.fullShuffleAndCut(cardDeck);
		if (cardDeck.isDeckOk()) {
			return cardDeck;
		}
		throw new RuntimeException("Internal error creating virtual playing card deck");
	}

	public void setShuffler(PlayingCardShuffler cardShuffler) {
		shuffler = cardShuffler;
	}

	public PlayingCardShuffler getShuffler() {
		return shuffler;
	}
}