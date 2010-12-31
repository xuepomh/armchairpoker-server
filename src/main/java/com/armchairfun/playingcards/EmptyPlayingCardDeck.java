package com.armchairfun.playingcards;

import org.apache.commons.lang.StringUtils;

/**
 * Useful when constructing your own deck of cards.
 *
 */
public class EmptyPlayingCardDeck extends PlayingCardDeck {
	private static final long serialVersionUID = 5886852915084540530L;

	protected void initialzeCards() {
		clear();
	}

	public void fromString(final String deckString) {
		if (StringUtils.isEmpty(deckString))
			return;
		for (int i = 0; i < deckString.length() - 1; i++) {
			String currentCard = deckString.substring(i, i + 2);
			i++;
			add(new PlayingCard(currentCard));
		}
	}

}
