package it.unibo.jurassiko.view.panel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.controller.game.impl.MainControllerImpl;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.reader.impl.OceanSpritePositionReader;
import it.unibo.jurassiko.reader.impl.TerritorySpritePositionReader;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Set the map for the GUI.
 */
public class MapPanel extends JPanel {

    private static final long serialVersionUID = -6881386592874173612L;
    private static final String OCEAN_PATH = "spritepositions/oceanpositions.json";
    private static final String TERRITORY_PATH = "spritepositions/territorypositions.json";
    private static final double HEIGHT_RATIO = 0.8;
    private static final double WIDTH_RATIO = 0.8;
    private static final String URL_IMAGE = "images/mappa.png";

    private final Map<String, DinoDisplay> territoryViews;
    private final Map<String, Map<GameColor, DinoDisplay>> oceanViews;
    private final MainController main;

    /**
     * Set the map in the relevant label and add it to the LayeredPane.
     */
    public MapPanel() {
        //TODO: main controller impl?
        this.main = new MainControllerImpl();
        this.territoryViews = new HashMap<>();
        this.oceanViews = new HashMap<>();

        BufferedImage mapImage;
        try {
            mapImage = ImageIO.read(ClassLoader.getSystemResource(URL_IMAGE));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read the map file", e);
        }

        final int width = (int) (WIDTH_RATIO * ViewImpl.getScreenSize().getWidth());
        final int height = (int) (HEIGHT_RATIO * ViewImpl.getScreenSize().getHeight());
        final ImageIcon map = ViewImpl.scaleImage(mapImage, width, height);
        final JLabel mapLabel = new JLabel(map);
        mapLabel.setBounds(0, 0, width, height);

        final JLayeredPane layPane = new JLayeredPane();
        layPane.add(mapLabel, JLayeredPane.DEFAULT_LAYER);

        final SpriteLoader spriteLoader = new SpriteLoader();
        createTerritoryDisplays(spriteLoader, width, height);
        this.territoryViews.entrySet().stream()
                .forEach(t -> layPane.add(t.getValue(), JLayeredPane.PALETTE_LAYER));

        createOceanDisplays(spriteLoader, width, height);
        this.oceanViews.entrySet().stream()
                .forEach(t -> t.getValue().entrySet()
                        .forEach(u -> layPane.add(u.getValue(), JLayeredPane.PALETTE_LAYER)));

        layPane.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.add(layPane);
    }

    public void updateBoard(){
        final var map = this.main.getTerritoriesMap();
        map.entrySet().stream().forEach(t -> {
            final String territoryName = t.getKey().getName();
            final DinoDisplay display = this.territoryViews.get(territoryName);
            display.setSpriteColor(t.getValue().x());
            display.setNumber(t.getValue().y());
        });
    }

    private void createTerritoryDisplays(final SpriteLoader spriteLoader, final int width, final int height) {
        final var territoryPositions = new TerritorySpritePositionReader().readFileData(TERRITORY_PATH);
        territoryPositions.entrySet().stream()
                .forEach(t -> this.territoryViews.put(t.getKey(),
                        new DinoDisplay(spriteLoader, false, calculatePosition(t.getValue().x(), width),
                                calculatePosition(t.getValue().y(), height))));
    }

    private void createOceanDisplays(final SpriteLoader spriteLoader, final int width, final int height) {
        final var oceanPositions = new OceanSpritePositionReader().readFileData(OCEAN_PATH);
        oceanPositions.entrySet().stream()
                .forEach(t -> {
                    final Map<GameColor, DinoDisplay> colorViews = new HashMap<>();
                    t.getValue().entrySet().stream()
                            .forEach(u -> colorViews.put(u.getKey(),
                                    new DinoDisplay(spriteLoader, true, calculatePosition(u.getValue().x(), width),
                                            calculatePosition(u.getValue().y(), height))));
                    this.oceanViews.put(t.getKey(), colorViews);
                });
    }

    /**
     * Calculate the position of dino in proportion of panel.
     * 
     * @param percentage the position in percent
     * @param size       size of the panel
     * @return the calculated position in the map
     */
    private int calculatePosition(final double percentage, final int size) {
        return (int) (percentage / 100 * size);
    }

}
