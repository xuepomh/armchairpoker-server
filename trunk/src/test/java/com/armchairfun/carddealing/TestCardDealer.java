package com.armchairfun.carddealing;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.playingcards.PlayingCardDeck;

import junit.framework.TestCase;

public class TestCardDealer extends TestCase {

	public void testDealFlop() {
		PlayingCardDeck deck = new PlayingCardDeck();
		CardDealer dealer = new CardDealer();
		List<DealtCard> theFlop = dealer.dealFlop(deck);
		assertTrue(theFlop.size() ==3);
		assertTrue(isAllUpcards(theFlop));
	}
	
	public void testDealTurn() {
		PlayingCardDeck deck = new PlayingCardDeck();
		CardDealer dealer = new CardDealer();
		List<DealtCard> theTurn = dealer.dealTurn(deck);
		assertTrue(theTurn.size() ==1);
		assertTrue(isAllUpcards(theTurn));
	}
	
	public void testDealRiver() {
		PlayingCardDeck deck = new PlayingCardDeck();
		CardDealer dealer = new CardDealer();
		List<DealtCard> theRiver = dealer.dealRiver(deck);
		assertTrue(theRiver.size() ==1);
		assertTrue(isAllUpcards(theRiver));
	}
	
	public void testDealDowncards() {
		PlayingCardDeck deck = new PlayingCardDeck();
		CardDealer dealer = new CardDealer();
		List<String> cardHolderIds = new ArrayList<String>();
		cardHolderIds.add("player1");
		cardHolderIds.add("player2");
		cardHolderIds.add("player3");
		cardHolderIds.add("player4");
		List<DealtCard> dealtCards = dealer.dealDownCards("D1", cardHolderIds, deck);
		assertNotNull(dealtCards);
		assertTrue(dealtCards.size() == 4);
	}
	
	public void testDealUpcards() {
		PlayingCardDeck deck = new PlayingCardDeck();
		CardDealer dealer = new CardDealer();
		List<String> cardHolderIds = new ArrayList<String>();
		cardHolderIds.add("player1");
		cardHolderIds.add("player2");
		cardHolderIds.add("player3");
		cardHolderIds.add("player4");
		List<DealtCard> dealtCards = dealer.dealUpCards("D1", cardHolderIds, deck);
		assertNotNull(dealtCards);
		assertTrue(dealtCards.size() == 4);
	}
	public boolean isAllUpcards(List<DealtCard> flop ) {
		
		for(DealtCard card: flop) {
			if (!card.isUpcard()) {
				return false;
			}
		}
		return true;
	}
}
