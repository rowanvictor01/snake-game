package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundManager {

    private static Clip gameMusic;
    private static Clip gameOverMusic;

    static {

        try {
            // Gets URL to know sound location at runtime
            URL gameMusicUrl = SoundManager.class.getResource("/music/HoliznaCC0 - Mutant Club.wav");
            URL gameOverMusicUrl = SoundManager.class.getResource("/music/173859__jivatma07__j1game_over_mono.wav");

            // Open wav file and "reads" sound file
            AudioInputStream gameMusicAis = AudioSystem.getAudioInputStream(gameMusicUrl);
            AudioInputStream gameOverMusicAis = AudioSystem.getAudioInputStream(gameOverMusicUrl);

            // Creates new Clip object as container for sounds
            gameMusic = AudioSystem.getClip();

            // Loads entire audio file into memory
            gameMusic.open(gameMusicAis);

            // Adjusts Volume
            FloatControl musicVolume = (FloatControl) gameMusic.getControl(FloatControl.Type.MASTER_GAIN);
            musicVolume.setValue(-13.0f);

            gameOverMusic = AudioSystem.getClip();
            gameOverMusic.open(gameOverMusicAis);

            musicVolume = (FloatControl) gameOverMusic.getControl(FloatControl.Type.MASTER_GAIN);
            musicVolume.setValue(-16.0f);

            // Close audio streams after use to free up resources
            gameMusicAis.close();
            gameOverMusicAis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void playSfx(String sfxName) {
        try{
            URL url = SoundManager.class.getResource("/sfx/" + sfxName + ".wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch(Exception e) {
            System.err.println("Failed to play sfx: " + e.getMessage());
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
