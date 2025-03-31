package Java.Wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    private static final int WORD_LENGTH = 5;
    private List<String> words;

    public Dictionary() {
        words = new ArrayList<>(Arrays.asList(
            "APPLE", "BEACH", "CHART", "DANCE", "EARTH", "FLAME", "GLOBE", "HEART", "IMAGE", "JUICE",
            "KNIFE", "LEMON", "MUSIC", "NIGHT", "OCEAN", "PIANO", "QUEEN", "RIVER", "STORM", "TIGER",
            "UNITY", "VOICE", "WATER", "XENON", "YACHT", "ZEBRA", "BRAVE", "CRISP", "DREAM", "FROST",
            "GRAPE", "HONEY", "IRONY", "JOKER", "KAYAK", "LILAC", "MANGO", "NOVEL", "OPERA", "PARIS",
            "QUICK", "ROBOT", "SALAD", "TREND", "ULTRA", "VIVID", "WALTZ", "YOUNG", "ZESTY", "BLISS",
            "CANDY", "DELTA", "EMBER", "FABLE", "GHOST", "HORSE", "IVORY", "JAZZY", "KOALA", "LEMUR"
        ));
        loadFromFile("words.txt");
    }

    private void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> loadedWords = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toUpperCase();
                if (line.length() == WORD_LENGTH && line.matches("[A-Z]{5}")) {
                    loadedWords.add(line);
                }
            }
            if (!loadedWords.isEmpty()) {
                words = loadedWords;
                System.out.println("Loaded " + words.size() + " words from dictionary file.");
            } else {
                System.out.println("Using expanded default dictionary (file empty or invalid).");
            }
        } catch (IOException e) {
            System.out.println("Using expanded default dictionary.");
        }
    }

    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
