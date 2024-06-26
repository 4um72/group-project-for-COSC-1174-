package application;

import java.util.Random;

public class FarkleAI {
    private Random random = new Random();
    
    // Simulates rolling n dice and returns the results in an array
    private int[] rollDice(int n) {
        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            results[i] = random.nextInt(6) + 1;
        }
        return results;
    }

    // Placeholder for the scoring logic based on rolled dice
    private int score(int[] dice) {
        // Implement actual scoring logic of Farkle here
        // For simplicity, let's assume a random chance of scoring
        return random.nextDouble() > 0.3 ? random.nextInt(1000) : 0;
    }

    // Determines whether to continue rolling based on the current score and game status
    private boolean shouldContinue(int rollScore, int currentScore, int myTotalScore, int opponentScore) {
        int riskThreshold = 300;
        if (myTotalScore < opponentScore) {
            // More aggressive if behind
            return currentScore < riskThreshold || random.nextDouble() > 0.5;
        } else {
            // More conservative if ahead
            return currentScore < riskThreshold / 2;
        }
    }

    // AI's turn logic
    public int aiTurn(int myTotalScore, int opponentScore) {
        int remainingDice = 6;
        int turnScore = 0;
        
        while (remainingDice > 0) {
            int[] diceResults = rollDice(remainingDice);
            int rollScore = score(diceResults);
            
            if (rollScore == 0) { // Farkle
                System.out.println("Farkled!");
                return 0;
            }
            
            turnScore += rollScore;
            System.out.println("Rolled: " + arrayToString(diceResults) + " Scored: " + rollScore + " Total Turn Score: " + turnScore);
            
            if (!shouldContinue(rollScore, turnScore, myTotalScore, opponentScore)) {
                System.out.println("Banking points.");
                return turnScore;
            }
            
            // Calculate remaining dice or reset if all were scoring
            remainingDice = Math.max(0, remainingDice - diceResults.length) == 0 ? 6 : remainingDice - diceResults.length;
        }
        
        return turnScore;
    }
    
    // Helper method to print array elements as a string
    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int value : array) {
            sb.append(value + " ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        FarkleAI ai = new FarkleAI();
        int aiScore = 500;
        int opponentScore = 1000;
        int aiTurnScore = ai.aiTurn(aiScore, opponentScore);
        aiScore += aiTurnScore;
        System.out.println("AI new score: " + aiScore);
    }
}
