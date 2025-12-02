package utils;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteManager {

    private static final Map<String, Image> images = new HashMap<>();

    // Load all assets at once
    static {
        // Background
        load("game-bg", "/bg/TileableBackGround.png");

        //Sprites
        load("snake-head", "/sprites/snakesprites/png/snake_green_head_64.png");
        load("snake-body", "/sprites/snakesprites/png/snake_green_blob_64.png");
        load("apple", "/sprites/apples_pack_30x30px/apple_regular_30_30px.png");
    }

    private static void load(String key, String path) {

        try {
            images.put(key, ImageIO.read(SpriteManager.class.getResource(path)));
        } catch(IOException e) {
            System.err.println("Failed to load asset:" + path);
            e.printStackTrace();
        }

    }

    public static Image get(String key) {

        Image img = images.get(key);
        if(img == null) {
            throw new IllegalArgumentException("Asset not found: " + key);
        }
        return img;

    }

}
