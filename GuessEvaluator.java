package Java.Wordle;

public class GuessEvaluator {
    private static final int WORD_LENGTH = 5;

    public static String evaluate(String guess, String targetWord) {
        // ANSI escape codes for colors
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RED = "\u001B[31m";

        StringBuilder result = new StringBuilder();
        char[] targetChars = targetWord.toCharArray();
        char[] guessChars = guess.toCharArray();
        boolean[] matched = new boolean[WORD_LENGTH];

        // First pass: correct positions
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guessChars[i] == targetChars[i]) {
                result.append(ANSI_GREEN).append(guessChars[i]).append(ANSI_RESET);
                matched[i] = true;
            } else {
                result.append(" ");
            }
        }

        // Second pass: correct letters in wrong positions
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guessChars[i] != targetChars[i]) {
                boolean found = false;
                for (int j = 0; j < WORD_LENGTH; j++) {
                    if (!matched[j] && guessChars[i] == targetChars[j]) {
                        // Replace the space with the colored letter
                        result.replace(i, i + 1, ANSI_YELLOW + guessChars[i] + ANSI_RESET);
                        matched[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.replace(i, i + 1, ANSI_RED + guessChars[i] + ANSI_RESET);
                }
            }
        }

        return result.toString();
    }
}
