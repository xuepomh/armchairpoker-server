package com.armchairfun.poker.common;

import junit.framework.TestCase;

public class TestHandRank extends TestCase {

	public void testHandRankOrder() {
		assertTrue(HandRank.ROYAL_FLUSH > HandRank.STRAIGHT_FLUSH);
		assertTrue(HandRank.STRAIGHT_FLUSH > HandRank.FOUR_OF_A_KIND);
		assertTrue(HandRank.FOUR_OF_A_KIND > HandRank.FULL_HOUSE);
		assertTrue(HandRank.FULL_HOUSE > HandRank.FLUSH);
		assertTrue(HandRank.FLUSH > HandRank.STRAIGHT);
		assertTrue(HandRank.STRAIGHT > HandRank.THREE_OF_A_KIND);
		assertTrue(HandRank.THREE_OF_A_KIND > HandRank.TWO_PAIR);
		assertTrue(HandRank.TWO_PAIR > HandRank.PAIR);
		assertTrue(HandRank.PAIR > HandRank.HIGH_CARD);
	}
}
