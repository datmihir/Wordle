package Java.Wordle;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        String targetWord = dictionary.getRandomWord();
        WordleGame game = new WordleGame(targetWord);
        game.play();
    }
}
