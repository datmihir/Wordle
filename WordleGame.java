package Java.Wordle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordleGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int WORD_LENGTH = 5;

    private final String targetWord;
    private final Scanner scanner;
    private int attempts;
    private boolean gameWon;
    private List<String> previousGuesses;
    private List<String> previousFeedbacks;

    public WordleGame(String targetWord) {
        this.targetWord = targetWord;
        this.scanner = new Scanner(System.in);
        this.attempts = 0;
        this.gameWon = false;
        this.previousGuesses = new ArrayList<>();
        this.previousFeedbacks = new ArrayList<>();
    }

    public void play() {
        printWelcomeMessage();
        while (attempts < MAX_ATTEMPTS && !gameWon) {
            displayGameState();
            String guess = getValidGuess();
            String feedback = GuessEvaluator.evaluate(guess, targetWord);
            previousGuesses.add(guess);
            previousFeedbacks.add(feedback);
            attempts++;
            if (guess.equals(targetWord)) {
                gameWon = true;
                displayGameState();
                System.out.println("Congratulations! You guessed the word: " + targetWord);
                System.out.println("You won in " + attempts + " attempt" + (attempts == 1 ? "!" : "s!"));
            }
        }
        if (!gameWon) {
            displayGameState();
            System.out.println("Game over! You've used all your attempts.");
            System.out.println("The word was: " + targetWord);
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Try to guess the 5-letter word in " + MAX_ATTEMPTS + " attempts.");
        System.out.println("After each guess, you'll get feedback:");
        System.out.println("Green - Correct letter in correct position");
        System.out.println("Yellow - Correct letter in wrong position");
        System.out.println();
    }

    private void displayGameState() {
        System.out.println("\n----------------------------------");
        for (int i = 0; i < previousGuesses.size(); i++) {
            System.out.println(previousGuesses.get(i) + " " + previousFeedbacks.get(i));
        }
        for (int i = previousGuesses.size(); i < MAX_ATTEMPTS; i++) {
            System.out.println("_ _ _ _ _");
        }
        System.out.println("------------------------------------");
        if (attempts < MAX_ATTEMPTS && !gameWon) {
            System.out.println("Attempt " + (attempts + 1) + "/" + MAX_ATTEMPTS);
        }
    }

    private String getValidGuess() {
        String guess;
        boolean validGuess = false;
        do {
            System.out.print("Enter your guess (5 letters): ");
            guess = scanner.nextLine().toUpperCase();
            if (guess.length() != WORD_LENGTH) {
                System.out.println("Your guess must be exactly 5 letters.");
            } else if (!guess.matches("[A-Z]{5}")) {
                System.out.println("Your guess must contain only letters.");
            } else {
                validGuess = true;
            }
        } while (!validGuess);
        return guess;
    }
}
