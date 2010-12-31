package com.armchairfun.carddealing;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDealingInstruction extends TestCase {

	public static Test suite() {
		return new TestSuite(TestDealingInstruction.class);
	}

	/** Test something. **/
	public void testHoldemPlayers() throws Exception {

		List<DealingInstruction> instructions = DealingInstruction
				.holdemPlayers();
		assertNotNull(instructions);
		assertTrue(instructions.size() == 2);
		DealingInstruction instruction1 = instructions.get(0);
		DealingInstruction instruction2 = instructions.get(1);
		instruction1.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction1.getDealName().equals(DealingInstruction.PLAYER_CARD_1);
		instruction2.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction2.getDealName().equals(DealingInstruction.PLAYER_CARD_2);

	}

	public void testFlopInstructions() throws Exception {

		List<DealingInstruction> instructions = DealingInstruction
				.flopInstructions();
		assertNotNull(instructions);
		assertTrue(instructions.size() == 3);
		DealingInstruction instruction1 = instructions.get(0);
		DealingInstruction instruction2 = instructions.get(1);
		DealingInstruction instruction3 = instructions.get(2);
		instruction1.getCardOrientation().equals(DealtCard.UPCARD);
		instruction2.getCardOrientation().equals(DealtCard.UPCARD);
		instruction3.getCardOrientation().equals(DealtCard.UPCARD);
		instruction1.getDealName().equals(DealingInstruction.COMMUNITY_CARD_1);
		instruction2.getDealName().equals(DealingInstruction.COMMUNITY_CARD_2);
		instruction3.getDealName().equals(DealingInstruction.COMMUNITY_CARD_3);

	}

	public void testTurnInstructions() throws Exception {

		List<DealingInstruction> instructions = DealingInstruction
				.turnInstructions();
		assertNotNull(instructions);
		assertTrue(instructions.size() == 1);
		DealingInstruction instruction1 = instructions.get(0);
		instruction1.getCardOrientation().equals(DealtCard.UPCARD);
		instruction1.getDealName().equals(DealingInstruction.COMMUNITY_CARD_4);

	}

	public void testRiverInstructions() throws Exception {

		List<DealingInstruction> instructions = DealingInstruction
				.riverInstructions();
		assertNotNull(instructions);
		assertTrue(instructions.size() == 1);
		DealingInstruction instruction1 = instructions.get(0);
		instruction1.getCardOrientation().equals(DealtCard.UPCARD);
		instruction1.getDealName().equals(DealingInstruction.COMMUNITY_CARD_5);

	}

	public void testOmahaPlayers() throws Exception {

		List<DealingInstruction> instructions = DealingInstruction
				.omahaPlayers();
		assertNotNull(instructions);
		assertTrue(instructions.size() == 4);
		DealingInstruction instruction1 = instructions.get(0);
		DealingInstruction instruction2 = instructions.get(1);
		DealingInstruction instruction3 = instructions.get(2);
		DealingInstruction instruction4 = instructions.get(3);
		instruction1.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction2.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction3.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction4.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction1.getDealName().equals(DealingInstruction.PLAYER_CARD_1);
		instruction2.getDealName().equals(DealingInstruction.PLAYER_CARD_2);
		instruction3.getDealName().equals(DealingInstruction.PLAYER_CARD_3);
		instruction4.getDealName().equals(DealingInstruction.PLAYER_CARD_4);

	}

	public void testStudPlayers() throws Exception {

		List<DealingInstruction> instructions = DealingInstruction
				.studPlayers();
		assertNotNull(instructions);
		assertTrue(instructions.size() == 3);
		DealingInstruction instruction1 = instructions.get(0);
		DealingInstruction instruction2 = instructions.get(1);
		DealingInstruction instruction3 = instructions.get(2);
		instruction1.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction2.getCardOrientation().equals(DealtCard.DOWNCARD);
		instruction3.getCardOrientation().equals(DealtCard.UPCARD);
		instruction1.getDealName().equals(DealingInstruction.PLAYER_CARD_1);
		instruction2.getDealName().equals(DealingInstruction.PLAYER_CARD_2);
		instruction3.getDealName().equals(DealingInstruction.PLAYER_CARD_3);

	}

}
