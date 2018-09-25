
// Name:Sarthak Seth
// USC NetID:sethsart@usc.edu
// CSCI455 PA2
// Spring 2018

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * class BulgarianSolitaireSimulator
 * contains the main method.checks the value for three options and 
 * whichever value holds true ,runs the corresponding method for it.each method gets a size of pile.
 * In each round you take one card from each pile, forming a new pile with these cards.
 */

public class BulgarianSolitaireSimulator {

	public static void main(String[] args) {
		Random generator = new Random();
		Scanner in = new Scanner(System.in);
		boolean singleStep = false;
		boolean userConfig = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}

		// <add code here>
		ArrayList<Integer> solitaire_Pile = new ArrayList<Integer>();
		if (userConfig) {
			userConfig(solitaire_Pile, in);
		} else if (singleStep) {
			singleStep(generator, solitaire_Pile, in);
		} else if (singleStep == false && userConfig == false) {
			neitherSingleStepNorUserConfig(generator, solitaire_Pile);
		}

	}

	/**
	 * runs if user config  option is selected. prompts for cards whose total should be equal to Card_Total
	 * 
	 * 
	 * @param ArrayList<integer> pile Array  giving the info about the content of the Solitairpiles
	 * @	param Scanner  scanner is passed for scanning  a line of numbers
	 * 
	 */
	public static void userConfig(ArrayList<Integer> pile, Scanner firstScan) {
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out
				.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
		System.out.println("Please enter a space-separated list of positive integers followed by newline:");

		Scanner secondScan = new Scanner(firstScan.nextLine());
		boolean loopTester = false;
		int cardTotalCheck = 0;

		tester(loopTester, cardTotalCheck, secondScan, pile, firstScan);

		System.out.printf("Initial configuration: ");

		for (int a = 0; a < pile.size(); a++) {
			System.out.printf(" " + pile.get(a));
		}
		System.out.println();
		SolitaireBoard userConfig = new SolitaireBoard(pile);
		int stepNumber = 0;

		while (userConfig.isDone() != true) {
			stepNumber++;
			userConfig.playRound();
			String finalString = userConfig.configString().replace(",", "").replace("[", "").replace("]", "");
			System.out.print("[" + stepNumber + "]" + "Current configuration: " + finalString);
			System.out.println();
		}
		System.out.println("Done!");

	}

	/**
	 * runs if neither config  option is selected.  the program will take no user input,
	 * and just show the initial configuration followed by the numbered result of each round until it finishes
	 * 
	 * 
	 * @param ArrayList<integer> pile Array  giving the info about the content of the Solitairpiles
	 * @	param Random  Random object to get any random value in the Solitairpiles
	 * 
	 */
	public static void neitherSingleStepNorUserConfig(Random number, ArrayList<Integer> piles) {
		int loopBreaker = 0;
		int tester1 = SolitaireBoard.CARD_TOTAL;
		for (int i = 0; i < SolitaireBoard.CARD_TOTAL; i++) {
			int newPile = number.nextInt(tester1) + 1;

			piles.add(newPile);
			tester1 = tester1 - newPile;
			loopBreaker = loopBreaker + newPile;
			if (loopBreaker == SolitaireBoard.CARD_TOTAL)
				break;

		}
		SolitaireBoard neitherStepnorUser = new SolitaireBoard(piles);
		System.out.printf("Initial configuration: ");
		for (int a = 0; a < piles.size(); a++) {
			System.out.printf(" " + piles.get(a));
		}
		System.out.println();
		int stepNumber = 0;
		while (neitherStepnorUser.isDone() != true) {
			stepNumber++;
			neitherStepnorUser.playRound();
			String finalPile = neitherStepnorUser.configString().replace(",", "").replace("[", "").replace("]", "");
			System.out.println("[" + stepNumber + "]" + "Current configuration: " + finalPile);

		}
		System.out.println("Done!");
	}
	/**
	 * runs if Singlestep config  option is selected.  the program will take no user input,
	 * and just show the initial configuration followed by the numbered result of each round until it finishes.
	 * But it goes to next step as we press enter
	 * 
	 * 
	 * @param ArrayList<integer> pile Array  giving the info about the content of the Solitairpiles
	 * @	param Random  Random object to get any random value in the Solitairpiles
	 * @param Scanner for implementing the enter functionality
	 */
	public static void singleStep(Random number, ArrayList<Integer> piles, Scanner n) {
		int loopBreaker = 0;
		int tester1 = SolitaireBoard.CARD_TOTAL;
		for (int i = 0; i < SolitaireBoard.CARD_TOTAL; i++) {
			int newPile = number.nextInt(tester1) + 1;

			piles.add(newPile);
			tester1 = tester1 - newPile;
			loopBreaker = loopBreaker + newPile;
			if (loopBreaker == SolitaireBoard.CARD_TOTAL)
				break;

		}
		SolitaireBoard singleStep = new SolitaireBoard(piles);
		System.out.printf("Initial configuration:");
		for (int a = 0; a < piles.size(); a++) {
			System.out.printf(" " + piles.get(a));
		}
		System.out.println();
		int stepNumber = 0;
		while (singleStep.isDone() != true) {
			stepNumber++;
			singleStep.playRound();
			String finalPile = singleStep.configString().replace(",", "").replace("[", "").replace("]", "");
			System.out.println("[" + stepNumber + "]" + "Current configuration:" + finalPile);
			System.out.printf("<Type return to continue>");
			n.nextLine();
		}
		System.out.println("Done!");
	}
	/**
	 * runs if user config  option is selected.  the program will check for invalid values and
	 * return an error statement
	 * 
	 * 
	 * @param ArrayList<integer> pile Array  giving the info about the content of the Solitairpiles
	 * @	param boolean check for reurning a check value in case there is no error
	 * @param total used in getting the total of the values in cards
	 * @param scanner def for scanning the values that are entered
	 * @scanner n for scanning a new set of values
	 * 
	 */
	public static void tester(boolean check, int total, Scanner def, ArrayList<Integer> piless, Scanner n) {
		while (check == false) {
			while (def.hasNextInt()) {

				{
					int input = def.nextInt();
					total = total + input;
					if (input > 0)
						piless.add(input);
					else {
						System.out.println(
								"ERROR: Each pile must have at least one card and the total number of cards must be 45\n"
										+ "Please enter a space-separated list of positive integers followed by newline:");
						piless.clear();
						total = 0;
						def = new Scanner(n.nextLine());
					}
				}
			}
			if (def.hasNext()) {
				System.out.println(
						"ERROR: Each pile must have at least one card and the total number of cards must be 45\n"
								+ "Please enter a space-separated list of positive integers followed by newline:");
				piless.clear();
				total = 0;
				def = new Scanner(n.nextLine());
			} else if (total != SolitaireBoard.CARD_TOTAL) {
				System.out.println(
						"ERROR: Each pile must have at least one card and the total number of cards must be 45\n"
								+ "Please enter a space-separated list of positive integers followed by newline:");
				piless.clear();
				total = 0;
				def = new Scanner(n.nextLine());

			} else {
				check = true;
			}

		}
	}
}