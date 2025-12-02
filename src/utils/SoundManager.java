package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;

public class SoundManager {

    private static Clip gameMusic;
    private static Clip gameOverMusic;

    static {

        try {
            URL gameMusicUrl = SoundManager.class.getResource("/music/HoliznaCC0 - Mutant Club.wav");
            URL gameOverMusicUrl = SoundManager.class.getResource("/music/173859__jivatma07__j1game_over_mono.wav");

            AudioInputStream gameMusicAis = AudioSystem.getAudioInputStream(gameMusicUrl);
            AudioInputStream gameOverMusicAis = AudioSystem.getAudioInputStream(gameOverMusicUrl);

            gameMusic = AudioSystem.getClip();
            gameMusic.open(gameMusicAis);

            gameOverMusic = AudioSystem.getClip();
            gameOverMusic.open(gameOverMusicAis);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void playGameMusic() {
        if(gameMusic != null) {
            gameMusic.setFramePosition(0); // restart from the beginning
            gameMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopGameMusic() {
        if(gameMusic != null) {
            gameMusic.stop();
        }
    }

    public static void playGameOverMusic() {
        if(gameOverMusic != null) {
            gameOverMusic.setFramePosition(0);
            gameOverMusic.start();
        }
    }

    public static void stopGameOverMusic() {
        if(gameOverMusic != null) {
            gameOverMusic.stop();
        }
    }

}
