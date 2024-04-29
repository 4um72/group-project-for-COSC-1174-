package application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FarkleGameTracker {
    public static void main(String[] args) {
        Map<String, Integer> playerScores = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the player's name or 'exit' to end the program:");
            String playerName = scanner.nextLine();

            if (playerName.equalsIgnoreCase("exit")) {
                break;
            }

            playerScores.putIfAbsent(playerName, 0);

            int currentScore = playerScores.get(playerName);
            playerScores.put(playerName, currentScore + 1);

            System.out.println(playerName + " has won " + playerScores.get(playerName) + " games.");
        }

        System.out.println("Game tracker has ended. Final scores:");
        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " games won.");
        }
    }
}
