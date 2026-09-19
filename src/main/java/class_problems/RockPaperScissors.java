import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        String p = playerMove.trim().toLowerCase();
        String c = computerMove.trim().toLowerCase();

        if (p.equals(c)) {
            return "Draw";
        }

        if ((p.equals("rock") && c.equals("scissors")) ||
            (p.equals("paper") && c.equals("rock")) ||
            (p.equals("scissors") && c.equals("paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();

        int totalRounds = 5;
        String[] playerMoves = new String[totalRounds];
        String[] computerMoves = new String[totalRounds];
        String[] results = new String[totalRounds];

        String[] demoMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};

        Scanner scanner = null;
        boolean hasInput = false;
        try {
            if (System.in.available() > 0) {
                scanner = new Scanner(System.in);
                hasInput = true;
            }
        } catch (IOException e) {
            hasInput = false;
        }

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < totalRounds; i++) {
            String pMove = demoMoves[i];
            if (hasInput && scanner != null && scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    pMove = line;
                }
            }

            pMove = pMove.substring(0, 1).toUpperCase() + pMove.substring(1).toLowerCase();
            String cMove = choices[random.nextInt(3)];

            String result = playRound(pMove, cMove);

            playerMoves[i] = pMove;
            computerMoves[i] = cMove;
            results[i] = result;

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;

            System.out.println("Round " + (i + 1) + " - Player: " + pMove + ", Computer: " + cMove + " -> " + result);
        }

        System.out.println("\nFinal Summary (after " + totalRounds + " rounds)");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-8s | %-15s | %-15s | %-15s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-8d | %-15s | %-15s | %-15s%n", (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
        System.out.println("------------------------------------------------------------------");

        double winPercentage = ((double) wins / totalRounds) * 100.0;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);

        if (scanner != null) {
            scanner.close();
        }
    }
}