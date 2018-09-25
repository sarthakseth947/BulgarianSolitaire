
// Name:Sarthak seth	
// USC NetID:sethsart@usc.edu
// CSCI455 PA2
// Spring 2018

import java.util.ArrayList;
import java.util.Arrays;

/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES = 4;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

	// Note to students: you may not use an ArrayList -- see assgt description for
	// details.

	/**
	 * Representation invariant:
	 * current size should be greater than 0 and less than CARD_TOTAL
	 * CARD_TOTAL should be a triangular number
	 * Data is always between 0 and currsize in the piles array
	 * Card total should be always equal to NUM_FINAL_PILES *(NUM_FINAL_PILES + 1) / 2;
	 * <put rep. invar. comment here>
	 * 
	 */

	// <add instance variables here>
	private int[] piles;
	private int currSize;

	/**
	 * Creates a solitaire board with the configuration specified in piles. piles
	 * has the number of cards in the first pile, then the number of cards in the
	 * second pile, etc. PRE: piles contains a sequence of positive numbers that sum
	 * to SolitaireBoard.CARD_TOTAL
	 */
	public SolitaireBoard(ArrayList<Integer> piles) {

		assert isValidSolitaireBoard(); // sample assert statement (you will be adding more of these calls)

		currSize = 0;
		this.piles = new int[CARD_TOTAL];
		for (int i = 0; i < piles.size(); i++) {
			this.piles[i] = piles.get(i);
			currSize++;

		}
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */
	public SolitaireBoard() {
		assert isValidSolitaireBoard();
	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration according
	 * to the rules of Bulgarian solitaire: Takes one card from each pile, and puts
	 * them all together in a new pile. The old piles that are left will be in the
	 * same relative order as before, and the new pile will be at the end.
	 */
	public void playRound() {
		assert isValidSolitaireBoard();
		for (int i = 0; i < currSize; i++) {
			this.piles[i] = this.piles[i] - 1;
		}
		int newPile = currSize;
		for (int j = 0; j < currSize; j++) {
			if (this.piles[j] == 0) {

				for (int k = j + 1; k < currSize; k++) {
					this.piles[k - 1] = this.piles[k];
				}

				currSize--;
				j--;
			}

		}

		currSize++;
		this.piles[currSize - 1] = newPile;

	}

	/**
	 * Returns true iff the current board is at the end of the game. That is, there
	 * are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES,
	 * in any order.
	 */

	public boolean isDone() {
		assert isValidSolitaireBoard();
		int test = 0;
		int[] tester = new int[NUM_FINAL_PILES];
		for (int l = 0; l < NUM_FINAL_PILES; l++) {
			tester[l] = l + 1;
		}
		for (int i = 0; i < NUM_FINAL_PILES; i++) {
			for (int j = 0; j < NUM_FINAL_PILES; j++) {
				if (this.piles[i] == tester[j])
					tester[j] = 0;
			}
		}
		for (int k = 0; k < NUM_FINAL_PILES; k++) {
			if (tester[k] == 0) {
				test++;
			}
		}

		if (test == NUM_FINAL_PILES) {
			return true;

		} else
			return false;

	}

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {
		assert isValidSolitaireBoard();
		int[] finalPiles = new int[currSize];
		for (int i = 0; i < currSize; i++) {
			finalPiles[i] = this.piles[i];
		}

		return (Arrays.toString(finalPiles)); // dummy code to get stub to compile
	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {
		int check = 0;
		for (int i = 0; i < currSize; i++) {
			if (piles[i] > 0 && piles[i] < CARD_TOTAL)
				;
			else {
				check++;
			}
		}
		if (check == 0)
			return true;
		else
			return false;
	}
	// <add any additional private methods here>

}
