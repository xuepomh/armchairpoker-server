package com.armchairfun.carddealing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.armchairfun.playingcards.PlayingCard;

public class TestDealtCard extends TestCase {

	private static final String HOLDER_ID = "H1";
	private static final String ID = "1";
	
	public static Test suite() {
		return new TestSuite(TestDealtCard.class);
	}
	
	

	public void testCreateDowncard() throws Exception {

		//ace of spades is the test card
		PlayingCard card = new PlayingCard('A', 'S');
		DealtCard downcard = DealtCard.createDowncard(HOLDER_ID, ID, card);
		assertTrue(downcard.getCardHolder().equals(HOLDER_ID));
		assertTrue(downcard.getDealName().equals(ID));
		assertTrue(downcard.getOrientation().equals(DealtCard.DOWNCARD));
		assertTrue(downcard.getCardString().equals(card.toString()));
		assertEquals(downcard.toString(), HOLDER_ID+":"+ID+" "+"(D)"+" [DOWN_CARD]");
	
	}

	public void testCreateUpcard() throws Exception {

		//ace of spades is the test card
		PlayingCard card = new PlayingCard('A', 'S');
		DealtCard upcard = DealtCard.createUpcard(HOLDER_ID, ID, card);
		assertTrue(upcard.getCardHolder().equals(HOLDER_ID));
		assertTrue(upcard.getDealName().equals(ID));
		assertTrue(upcard.getOrientation().equals(DealtCard.UPCARD));
		assertTrue(upcard.isUpcard());
		assertTrue(upcard.getCardString().equals(card.toString()));
		assertEquals(upcard.toString(), HOLDER_ID+":"+ID+" "+"(U)"+" AS");

	}
	
	
}
