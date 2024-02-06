package it.unibo.jurassiko.view.panel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Set the map for the GUI.
 */
public class MapPanel extends JPanel {

    private static final double HEIGHT_RATIO = 0.8;
    private static final double WIDTH_RATIO = 0.8;
    private static final String URL_IMAGE = "images/mappa.png";

    private final ImageIcon map;
    private final Dimension screenSize;
    private final JLayeredPane layPane;
    private final JLabel mapLabel;
    private transient BufferedImage imageMap;

    /**
     * Set the map in the relevant label and add it to the LayeredPane.
     */
    public MapPanel() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            imageMap = ImageIO.read(ClassLoader.getSystemResource(URL_IMAGE));
        } catch (final Exception e) {
            throw new IllegalStateException("Failed to read the map file", e);
        }
        final int width = (int) (WIDTH_RATIO * screenSize.getWidth());
        final int height = (int) (HEIGHT_RATIO * screenSize.getHeight());
        this.map = ViewImpl.scaleImage(imageMap, width, height);
        this.mapLabel = new JLabel(map);
        mapLabel.setBounds(0, 0, width, height);
        this.layPane = new JLayeredPane();
        this.layPane.add(mapLabel, JLayeredPane.DEFAULT_LAYER);
        this.layPane.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.add(layPane);
    }
}
