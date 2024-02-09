package it.unibo.jurassiko.view.panel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Class use to load the sprites.
 */
public class SpriteLoader {

    private static final String URL_REDDINO = "images/sprites/dinored.png";
    private static final String URL_BLUEDINO = "images/sprites/dinoblue.png";
    private static final String URL_GREENDINO = "images/sprites/dinogreen.png";
    private static final String URL_REDLAPRAS = "images/sprites/laprasred.png";
    private static final String URL_BLUELAPRAS = "images/sprites/laprasblue.png";
    private static final String URL_GREENLAPRAS = "images/sprites/laprasgreen.png";
    private static final double HEIGHT_RATIO = 0.05;
    private static final double WIDTH_RATIO = 0.025;

    private final Map<GameColor, ImageIcon> dinoSprites;
    private final Map<GameColor, ImageIcon> laprasSprites;

    /**
     * Creates and initializes a SpriteLoader.
     */
    public SpriteLoader() {
        this.dinoSprites = new HashMap<>();
        this.laprasSprites = new HashMap<>();
        loadSprites();
    }

    private void loadSprites() {
        BufferedImage redDino, blueDino, greenDino;
        BufferedImage redLapras, blueLapras, greenLapras;
        try {
            redDino = ImageIO.read(ClassLoader.getSystemResource(URL_REDDINO));
            blueDino = ImageIO.read(ClassLoader.getSystemResource(URL_BLUEDINO));
            greenDino = ImageIO.read(ClassLoader.getSystemResource(URL_GREENDINO));
            redLapras = ImageIO.read(ClassLoader.getSystemResource(URL_REDLAPRAS));
            blueLapras = ImageIO.read(ClassLoader.getSystemResource(URL_BLUELAPRAS));
            greenLapras = ImageIO.read(ClassLoader.getSystemResource(URL_GREENLAPRAS));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read sprite files", e);
        }
        final int width = (int) (WIDTH_RATIO * ViewImpl.getScreenSize().getWidth());
        final int height = (int) (HEIGHT_RATIO * ViewImpl.getScreenSize().getHeight());

        this.dinoSprites.put(GameColor.RED,
                ViewImpl.scaleImage(redDino, width, height));
        this.dinoSprites.put(
                GameColor.BLUE, ViewImpl.scaleImage(blueDino, width, height));
        this.dinoSprites.put(
                GameColor.GREEN, ViewImpl.scaleImage(greenDino, width, height));
        this.laprasSprites.put(GameColor.RED,
                ViewImpl.scaleImage(redLapras, width, height));
        this.laprasSprites.put(GameColor.BLUE,
                ViewImpl.scaleImage(blueLapras, width, height));
        this.laprasSprites.put(GameColor.GREEN,
                ViewImpl.scaleImage(greenLapras, width, height));
    }

    /**
     * 
     * @return a copy of map containing dino sprites
     */
    public Map<GameColor, ImageIcon> getDinoSprites() {
        return Map.copyOf(dinoSprites);
    }

    /**
     * 
     * @return a copy of map containing Lapras sprites
     */
    public Map<GameColor, ImageIcon> getLaprasSprites() {
        return Map.copyOf(laprasSprites);
    }

}
