package application;

import java.util.Scanner;
import java.util.Arrays;

public class FarkleProgram {

	public static final int WINNING_SCORE = 10000;
	private boolean winner = false;
	private Player p0;
	private Player p1;
	private Player currentPlayer;
	private Dice [] roll;
	private int[] sorted;
	private int diceInHand;
	private int round = 1;
	private int game = 1;
	
	public FarkleProgram(String name, int choice) {
		p0 = new Player(name, 0, 0); //Person is p0
		currentPlayer = p0;
		if (choice == 1) {
			p1 = new Player("Easy Computer", 0, 1);} //Easy opponent
		
		else if (choice == 2) {
			p1 = new Player("Hard Computer", 0, 2);//Hard opponent
		}
		//Start game
		while (!winner) {
		//Start round
			diceInHand = 6;
				if (currentPlayer.getPlayerNum() == 0) {
					currentPlayer.setRoundScore(0);
					roll = new Dice[6];
					rollDice(roll);
					checkScoringDice();
					if (winner) {
						System.out.println(p0.getName() + " is the winner!");
					}
					//Examine dice for possible melds
					//Let player choose scoring dice
					//Add scoring dice value to score and subtract # of scoring dice from diceInHand
					//Roll again
					//Repeat until player farkles or banks
					//set currentPlayer = p1
				}
				currentPlayer.addToScore(currentPlayer.currentRoundScore);
				System.out.println(currentPlayer.getName() + " has earned " +
						currentPlayer.getCurrentRoundScore() + " points this round and has " +
						 currentPlayer.getCurrentScore() + " total points");
				currentPlayer.setRoundScore(0);
				if (!winner) currentPlayer = p1;
				if (currentPlayer.getPlayerNum() == 1){
					currentPlayer.setRoundScore(0);
						roll = new Dice[6];
						rollDice(roll);
						checkScoringDice();
					if (winner) {
						System.out.println(p1.getName() + " is the winner!");	
					}
				}
				if (currentPlayer.getPlayerNum() == 2){
					currentPlayer.setRoundScore(0);
					roll = new Dice[6];
					rollDice(roll);
					checkScoringDice();
					if (winner) {
						System.out.println(p1.getName() + " is the winner!");
					}
				}
				currentPlayer.addToScore(currentPlayer.currentRoundScore);
				if (currentPlayer.equals(p1)) {
				System.out.println(currentPlayer.getName() + " has earned " +
						currentPlayer.getCurrentRoundScore() + " points this round and has " +
						 currentPlayer.getCurrentScore() + " total points");
				currentPlayer.setRoundScore(0);
				}
				if (!winner) currentPlayer = p0;
				round++;
			}
			game++;
		}
	
	//Method to check and score dice
	public void checkScoringDice() {
		boolean farkle = false;
		boolean hotDice = false;
		int scoredDice = 0;
		int currentRollScore = 0;
		//check for straight
		if (sorted.length == 6) {
			boolean fullRun = true;
			for (int i = 0; i<6; i++) {
				if (sorted[i] != i + 1) {
					fullRun = false;
					break;
				}
			}
			if (fullRun) {
				currentRollScore += 1000;
				scoredDice = 6;
				hotDice = true;
			}
		}
		if (!hotDice) {
			//Array of index 5 that stores the amount of each face value in the current roll
			int[] pairs = new int[6];
			for (int i = 0; i<sorted.length; i++) {
				int num = sorted[i];
				pairs[num-1]++;
			}
			int pairCount = 0;
			int tripleCount = 0;
			int tripleNumber1 = 0;
			int tripleNumber2 = 0;
			int sixInARow = 0;
			int sixNumber = 0;
			for (int i = 0; i<sorted.length; i++) {
				if (pairs[i] == 2)
					pairCount++;
				if (pairs[i] == 3) {
					tripleCount++;
					if (tripleNumber1 == 0)
						tripleNumber1 = i + 1;
					if (tripleNumber1 == 1)
						tripleNumber2 = i + 1;
				}
				if (pairs[i] == 6) {
					sixInARow++;
					sixNumber = i + 1;
				}
			}
			if (pairCount == 3) {
				currentRollScore += 750;
				scoredDice = 6;
				hotDice = true;
			}
			if (tripleCount == 1 && tripleNumber1 != 1) {
				currentRollScore += tripleNumber1 * 100;
				scoredDice += 3;
			}
			if (tripleCount == 2) {
				if (tripleNumber1 == 1) {
					currentRollScore += (1000) + (tripleNumber2 * 100);
				}
				else {
					currentRollScore += (tripleNumber1 * 100) + (tripleNumber2 * 100);
				}
				scoredDice = 6;
				hotDice = true;
			}
			if (sixInARow == 1 && sixNumber != 1) {
				currentRollScore += (8 * (sixNumber * 100));
				scoredDice = 6;
				hotDice = true;
			}
			if (sixInARow == 1 && sixNumber == 1) {
				currentRollScore += 8000;
				scoredDice = 6;
				hotDice = true;
			}
		}
			if (!hotDice) {
			int oneCount = 0;
			int twoCount = 0;
			int threeCount = 0;
			int fourCount = 0;
			int fiveCount = 0;
			int sixCount = 0;
			for (int i = 0; i<sorted.length; i++) {
				if (sorted[i] == 1)
					oneCount++;
				if (sorted[i] == 2) 
					twoCount++;
				if (sorted[i] == 3)
					threeCount++;
				if (sorted[i] == 4)
					fourCount++;
				if (sorted[i] == 5)
					fiveCount++;
				if (sorted[i] == 6) 
					sixCount++;
			}
				if (oneCount == 5) {
					currentRollScore += 4000;
					scoredDice += 5;
				}
				else if (oneCount == 4) {
					currentRollScore += 2000;
					scoredDice += 4;
				}
				else if (oneCount == 3) {
					currentRollScore += 1000;
					scoredDice += 3;
				}
				else if (oneCount == 2) {
					currentRollScore += 200;
					scoredDice += 2;
				}
				else if (oneCount == 1) {
				
					currentRollScore += 100;
					scoredDice += 1;
				}
				if (twoCount == 5) {
					currentRollScore += 800;
					scoredDice += 5;
				}
				else if (twoCount == 4) {
					currentRollScore += 400;
					scoredDice += 4;
				}
				if (threeCount == 5) {
					currentRollScore += 1200;
					scoredDice += 5;
				}
				else if (threeCount == 4) {
					currentRollScore += 600;
					scoredDice += 4;
				}
				if (fourCount == 5) {
					currentRollScore += 1600;
					scoredDice += 5;
				}
				else if (fourCount == 4) {
					currentRollScore += 800;
					scoredDice += 4;
				}
				if (fiveCount == 5) {
					currentRollScore += 4000;
					scoredDice += 5;
				}
				else if (fiveCount == 4) {
					currentRollScore += 1000;
					scoredDice += 4;
				}
				else if (fiveCount == 2) {
					currentRollScore += 100;
					scoredDice += 2;
				}
				else if (fiveCount == 1) {
					currentRollScore += 50;
					scoredDice += 1;
				}
				if (sixCount == 5) {
					currentRollScore += 1800;
				}
				else if (sixCount == 4) {
					currentRollScore += 1200;
				}
			}
			if (scoredDice == 6) 
				hotDice = true;
			if (scoredDice == 0) {
				farkle = true;
				currentPlayer.setRoundScore(0);
				System.out.println("Sorry, you Farkled!");
			}
			if (!farkle) {
				currentPlayer.addToRoundScore(currentRollScore);
				if ((currentPlayer.getCurrentScore() + 
						currentPlayer.getCurrentRoundScore()) >= WINNING_SCORE) {
					winner = true;
				}
			if (!winner && currentPlayer.getName().equals("Easy Computer")) {
				if (currentPlayer.getCurrentRoundScore() < 250 && !farkle) {
					if (!hotDice) {
						diceInHand -= scoredDice;
						rollDice(roll);
						checkScoringDice();
					}
					if (hotDice) {
						System.out.println("Hot Dice! " + currentPlayer.getName() + " has scored " + 
								currentRollScore + " points and gets to roll again.");
						roll = new Dice[6];
						rollDice(roll);
						checkScoringDice();
					}
				}
			}
			if (!winner && currentPlayer.getName().equals("Hard Computer")) {
				if (currentPlayer.getCurrentRoundScore() < 250 && !farkle) {
					if (!hotDice) {
						diceInHand -= scoredDice;
						rollDice(roll);
						checkScoringDice();
					}
					if (hotDice) {
						System.out.println("Hot Dice! " + currentPlayer.getName() + " has scored " + 
								currentRollScore + " points and gets to roll again.");
						roll = new Dice[6];
						rollDice(roll);
						checkScoringDice();
					}
				}
			}
			if (!winner && currentPlayer.getPlayerNum() == 0 && hotDice) {
				System.out.println("Hot Dice! " + currentPlayer.getName() + " has scored " + 
						currentRollScore + " points and gets to roll again.");
					roll = new Dice[6];
					rollDice(roll);
					checkScoringDice();
			}
		}
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public String getPlayerName() {
		return this.getPlayerName();
	}
	
	public int getRound() {
		return round;
	}
	
	public int getGame() {
		return game;
	}
	
	public void rollDice(Dice [] roll) {
		sorted = new int[diceInHand];
		for (int i = 0; i < diceInHand; i++) {
			this.roll[i] = new Dice();
			sorted[i] = roll[i].getFaceValue(); 
		}
		Arrays.sort(sorted);
		for (int i = 0; i<sorted.length; i++) {
			System.out.print(sorted[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("What is your name?");
		String name = input.nextLine();
		System.out.println("Enter 1 for easy or 2 for a hard opponent");
		int choice = input.nextInt();
		if (choice != 1 || choice != 2)
			choice = 1;
		FarkleProgram game1 = new FarkleProgram(name, choice);
		input.close();
	}
}
