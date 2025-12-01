package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HighScoreManager {

    private static final Path HIGHSCORE_PATH = Paths.get(System.getProperty("user.home"), "SnakeGame", "highscore.txt");

    public static void saveHighScore(int score) {

        try {
            Files.createDirectories(HIGHSCORE_PATH.getParent());
            Files.writeString(HIGHSCORE_PATH, String.valueOf(score));
        } catch(IOException e) {
            System.err.println("Failed to save high score: " + e.getMessage());
        }

    }

    public static int loadHighScore() {

        try {
            if(!Files.exists(HIGHSCORE_PATH)) {
                return 0;
            }

            String fileContent = Files.readString(HIGHSCORE_PATH).trim();
            if(fileContent.isEmpty()) {
                return 0;
            }

            return Integer.parseInt(fileContent);
        } catch(IOException e) {
            System.err.println("Failed to read high score file: " + e.getMessage());
            return 0;
        } catch(NumberFormatException e) {
            System.err.println("High score file is corrupted. Resetting save to 0");
            return 0;
        }

    }

}
