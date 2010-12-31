package com.armchairfun.carddealing;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.playingcards.PlayingCard;
import com.armchairfun.playingcards.PlayingCardDeck;

/**
 * Given a deck of cards, either full or partial, deal a card to each
 * card holder in the orientation directed by the dealing instruction.
 * 
 * A couple of methods are provided for standard deals like flop, turn river.
 *
 */
public class CardDealer {
	private static final String COMMUNITY = "COMMUNITY";

	/**
	 * Create a deal result for a flop.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealFlop(final PlayingCardDeck cardDeck) {
		List<String> cardHolderIds = new ArrayList<String>();
		cardHolderIds.add(COMMUNITY);
		return dealCards(DealingInstruction.flopInstructions(), cardHolderIds, cardDeck);
	}

	/**
	 * Create a deal result for a turn.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealTurn(final PlayingCardDeck cardDeck) {
		List<String> cardHolderIds = new ArrayList<String>();
		cardHolderIds.add(COMMUNITY);
		return dealCards(DealingInstruction.turnInstructions(), cardHolderIds, cardDeck);
	}

	/**
	 * Create a deal result for a river.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealRiver(final PlayingCardDeck cardDeck) {
		List<String> cardHolderIds = new ArrayList<String>();
		cardHolderIds.add(COMMUNITY);
		return dealCards(DealingInstruction.riverInstructions(), cardHolderIds, cardDeck);
	}

	/**
	 * Deal cards from the top of a deck according to the dealing instructions for each card holder.
	 * @param dealInstructions
	 * @param cardHolderIds
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealCards(final List<DealingInstruction> dealInstructions, final List<String> cardHolderIds,
			final PlayingCardDeck cardDeck) {
		List<DealtCard> dealtCards = new ArrayList<DealtCard>();
		for (DealingInstruction dealInstruction : dealInstructions) {
			for (String holderId : cardHolderIds) {
				final PlayingCard card = cardDeck.removeTopCard();
				final DealtCard dealtCard = createDealtCard(holderId, dealInstruction, card);
				dealtCards.add(dealtCard);
			}
		}
		return dealtCards;
	}

	/**
	 * Deal a card to each card holder from the top of a deck according to the dealing instructions.
	 * @param dealInstructions
	 * @param cardHolderIds
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealCards(final DealingInstruction dealInstruction, final List<String> cardHolderIds,
			final PlayingCardDeck cardDeck) {
		List<DealtCard> dealtCards = new ArrayList<DealtCard>();
		for (String holderId : cardHolderIds) {
			final PlayingCard card = cardDeck.removeTopCard();
			final DealtCard dealtCard = createDealtCard(holderId, dealInstruction, card);
			dealtCards.add(dealtCard);
		}
		return dealtCards;
	}

	private DealtCard createDealtCard(String holderId, DealingInstruction dealInstruction, final PlayingCard card) {
		DealtCard dealtCard = null;
		if (DealtCard.UPCARD.equals(dealInstruction.getCardOrientation())) {
			dealtCard = DealtCard.createUpcard(holderId, dealInstruction.getDealName(), card);
		} else if (DealtCard.DOWNCARD.equals(dealInstruction.getCardOrientation())) {
			dealtCard = DealtCard.createDowncard(holderId, dealInstruction.getDealName(), card);
		}

		return dealtCard;
	}

	/**
	 * Deal cards face up for card holders from the top of a deck.
	 * 
	 * @param dealId
	 * @param cardHolderIds
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealUpCards(final String dealId, final List<String> cardHolderIds, final PlayingCardDeck cardDeck) {
		return dealCards(new DealingInstruction(dealId, DealtCard.UPCARD), cardHolderIds, cardDeck);
	}

	/**
	 * Deal cards face down for card holders from the top of a deck.
	 * 
	 * @param dealId
	 * @param cardHolderIds
	 * @param cardDeck
	 * @return
	 */
	public List<DealtCard> dealDownCards(final String dealId, List<String> cardHolderIds, final PlayingCardDeck cardDeck) {
		return dealCards(new DealingInstruction(dealId, DealtCard.DOWNCARD), cardHolderIds, cardDeck);
	}
}
