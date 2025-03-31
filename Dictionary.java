package Java.Wordle;

import java.util.*;

public class Dictionary {
    // private static final int WORD_LENGTH = 5;
    private static final List<String> WORDS = Arrays.asList(
        "APPLE", "BEACH", "CHART", "DANCE", "EARTH", "FLAME", "GLOBE", "HEART", "IMAGE", "JUICE",
        "KNIFE", "LEMON", "MUSIC", "NIGHT", "OCEAN", "PIANO", "QUEEN", "RIVER", "STORM", "TIGER",
        "UNITY", "VOICE", "WATER", "XENON", "YACHT", "ZEBRA", "BRAVE", "CRISP", "DREAM", "FROST"
    );

    public String getRandomWord() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
