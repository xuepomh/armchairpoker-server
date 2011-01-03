package com.armchairfun.playingcards;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * This class will provide a statistically accurate simulation of a card shuffle
 * used by many casinos. The algorithms used by this class will produce an unbiased
 * random distribution of playing cards, regardless of the type of deck being shuffled.
 * 
 */
public class PlayingCardShuffler {
	protected static final Logger logger = Logger.getLogger(PlayingCardShuffler.class);
	/*
	 * This variance is used to determine a general location for about half the
	 * deck. This used in generating a random cut position. For a standard deck
	 * of 52 cards, the variance will be a percentage off 1/2 the deck or 26
	 * cards.
	 */
	public static final double _CARDCUTVARIANCE = .1;

	private Random random;
	private String algorithm;
	private String PRNGAlgorithm = "SHA1PRNG";
	private int fullShufflesToRun = 1;

	public PlayingCardShuffler() {
		this.algorithm = PRNGAlgorithm;
		initSecureRandom();
	}

	public PlayingCardShuffler(String algorithm) {
		this.algorithm = algorithm;
		initSecureRandom();
	}
	
	/**
	 * Try to get a cut position for the deck around the middle with a certain amount of variance.
	 * 
	 * @param numberOfCards
	 * @return
	 */
	public int generateCutPosition(final int numberOfCards) {
		final int halfDeck = numberOfCards / 2;
		final Double topCutRange = (int) halfDeck + (numberOfCards * _CARDCUTVARIANCE);
		final Double bottomCutRange = (int) halfDeck - (numberOfCards * _CARDCUTVARIANCE);
		int cutPosition = -1;
		while (cutPosition < bottomCutRange || cutPosition > topCutRange) {
			cutPosition = getRandom().nextInt(topCutRange.intValue());
		}
		return cutPosition;
	}

	/**
	 * Cut the deck right in the middle.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public int perfectCut(final PlayingCardDeck cardDeck) {
		return cutDeck(cardDeck, cardDeck.getNumberOfCards() / 2);
	}

	/**
	 * A standard cut around the middle.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public int cutDeck(final PlayingCardDeck cardDeck) {
		final int cutPosition = generateCutPosition(cardDeck.getNumberOfCards());
		return cutDeck(cardDeck, cutPosition);
	}

	/**
	 * Cut the deck at a specific position.
	 * 
	 * @param cardDeck
	 * @param cutPosition
	 * @return
	 */
	public int cutDeck(final PlayingCardDeck cardDeck, final int cutPosition) {
		cardDeck.addAll(cardDeck.removeTopCards(cutPosition));
		return cutPosition;
	}

	/**
	 * Create two decks from the one approximatly in the middle.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public PlayingCardDeck splitDeck(final PlayingCardDeck cardDeck) {
		return splitDeck(cardDeck, generateCutPosition(cardDeck.size()));
	}

	/**
	 * Create two decks from a specific position in the deck.
	 * 
	 * @param cardDeck
	 * @param cutPosition
	 * @return
	 */
	public PlayingCardDeck splitDeck(final PlayingCardDeck cardDeck, final int cutPosition) {
		return cardDeck.removeTopCards(cutPosition);
	}

	/**
	 * Simulate a deck mix.
	 * 
	 * @param deck
	 * @return
	 */
	public PlayingCardDeck mixDeck(final PlayingCardDeck deck) {
		logger.debug("Mix Shuffle");
		return fisherYatesModern(deck);
	}

	/**
	 * Shuffle the deck according to the original Fisher/Yates method.
	 * 
	 * @param deck
	 * @return
	 */
	public PlayingCardDeck fisherYatesOriginal(final PlayingCardDeck deck) {
		logger.debug("Fisher/Yates Original");
		final PlayingCardDeck mixedDeck = new EmptyPlayingCardDeck();
		while (deck.size() > 0) {
			final int mixCard = getRandom().nextInt(deck.size());
			mixedDeck.add(deck.remove(mixCard));
		}
		deck.addAll(mixedDeck);
		return deck;
	}

	/**
	 * Shuffle the deck according the modern version of the Fisher/Yates method.
	 * 
	 * @param deck
	 * @return
	 */
	public PlayingCardDeck fisherYatesModern(final PlayingCardDeck deck) {
		logger.debug("Fisher/Yates Modern");
		// The number of items left to shuffle (loop invariant).
		int n = deck.size();
		while (n > 1) {
			int k = getRandom().nextInt(n); // 0 <= k < n.
			n--; // n is now the last pertinent index;
			// Swap selected card with swap card, do nothing if k == n
			if (k != n) {
				PlayingCard selectedCard = deck.get(n);
				PlayingCard swapCard = deck.get(k);
				deck.set(n, swapCard);
				deck.set(k, selectedCard);
			}
		}
		return deck;
	}

	/**
	 * Simulate a riffle shuffle.
	 * 
	 * @param deck
	 * @return
	 */
	public PlayingCardDeck riffleShuffle(final PlayingCardDeck deck) {
		logger.debug("Riffle Shuffle");
		final PlayingCardDeck splitCards = splitDeck(deck);
		final PlayingCardDeck shuffledDeck = new EmptyPlayingCardDeck();

		while (!deck.isEmpty()) {
			if (getRandom().nextInt(2) == 1) {
				shuffledDeck.addAll(0, deck.removeBottomCards(getRandom().nextInt(3)));
				shuffledDeck.addAll(0, splitCards.removeBottomCards(getRandom().nextInt(3)));
			} else {
				shuffledDeck.addAll(0, splitCards.removeBottomCards(getRandom().nextInt(3)));
				shuffledDeck.addAll(0, deck.removeBottomCards(getRandom().nextInt(3)));
			}
		}
		deck.addAll(shuffledDeck);
		// Get any cards remaining in the split deck
		deck.addAll(splitCards);
		return deck;
	}

	/**
	 * Simulate a strip shuffle from the top.
	 * 
	 * @param deck
	 * @return
	 */
	public PlayingCardDeck stripShuffleTop(final PlayingCardDeck deck) {
		logger.debug("Strip Shuffle top");
		final PlayingCardDeck shuffledDeck = new EmptyPlayingCardDeck();
		while (!deck.isEmpty()) {
			shuffledDeck.addAll(0, deck.removeTopCards(getRandom().nextInt(4)));
		}
		deck.addAll(shuffledDeck);
		return deck;

	}

	/**
	 * Simulate a strip shuffle from the bottom.
	 * 
	 * @param deck
	 * @return
	 */
	public PlayingCardDeck stripShuffleBottom(final PlayingCardDeck deck) {
		logger.debug("Strip Shuffle bottom");
		final PlayingCardDeck shuffledDeck = new EmptyPlayingCardDeck();
		while (!deck.isEmpty()) {
			shuffledDeck.addAll(0, deck.removeBottomCards(getRandom().nextInt(4)));
		}
		deck.addAll(shuffledDeck);
		return deck;

	}

	/**
	 * Perform a full shuffle and cut the deck.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public PlayingCardDeck fullShuffleAndCut(final PlayingCardDeck cardDeck) {
		fullShuffle(cardDeck);
		cutDeck(cardDeck);
		return cardDeck;
	}

	/**
	 * A full shuffle is performed by first mixing the deck, then
	 * randomly selecting various shuffle types to run according
	 * the the number of shuffles defined.  The randomness is biased
	 * towards a Fisher/Yates modern.
	 * 
	 * @param cardDeck
	 * @return
	 */
	public PlayingCardDeck fullShuffle(final PlayingCardDeck cardDeck) {
		mixDeck(cardDeck);
		for (int i = 0; i < getFullShufflesToRun(); i++) {
			switch (random.nextInt(7)) {
			case (0):
				stripShuffleTop(cardDeck);
				break;
			case (1):
				stripShuffleBottom(cardDeck);
				break;
			case (2):
				riffleShuffle(cardDeck);
				break;
			case (3):
				fisherYatesModern(cardDeck);
				break;
			case (4):
				fisherYatesModern(cardDeck);
				break;
			case (5):
				fisherYatesModern(cardDeck);
				break;
			case (6):
				fisherYatesModern(cardDeck);
				break;
			default:
				fisherYatesModern(cardDeck);
			}
		}
		return cardDeck;
	}

	/**
	 * Initialize the secure random number generator.
	 */
	public void initSecureRandom() {
		try {
			SecureRandom secureRandom = SecureRandom.getInstance(algorithm);
			// Get 1024 random bits
			byte[] bytes = new byte[1024 / 8];
			secureRandom.nextBytes(bytes);
			int seedByteCount = 10;
			byte[] seed = secureRandom.generateSeed(seedByteCount);

			secureRandom = SecureRandom.getInstance(algorithm);
			secureRandom.setSeed(seed);
			random = secureRandom;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Could not establish Algorithm:  " + algorithm);
			random = new Random();
			random.setSeed(System.currentTimeMillis());
		}
	}

	public int getFullShufflesToRun() {
		return fullShufflesToRun;
	}

	public void setFullShufflesToRun(int fullShufflesToRun) {
		this.fullShufflesToRun = fullShufflesToRun;
	}

	public synchronized Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}
	
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getAlgorithm() {
		return this.algorithm;
	}
	
	
}