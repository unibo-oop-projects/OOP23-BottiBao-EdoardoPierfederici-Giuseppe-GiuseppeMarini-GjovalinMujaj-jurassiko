package it.unibo.jurassiko.view.panel;

import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Makes the top-bar for the GUI.
 */
public class TopBarPanel extends JPanel {

    private static final long serialVersionUID = 1379037036670658465L;

    private static final double HEIGHT_RATIO = 0.1;
    private static final double WIDTH_RATIO = 0.8;
    private static final int FONT_SIZE = 24;
    private static final int DISTANCE_BUTTON_L_R = 48;
    private static final String URL_IMAGE = "images/topbar.png";
    private final MainController main;
    private final JLabel topLabel;
    private JLabel currentPlayer;
    private GameColor currentColor;
    /**
     * Set the top-bar in the relevant label load the buttons in it,
     * and add all to the relevant panel.
     */
    public TopBarPanel(MainController main) {
        this.main = main;
        BufferedImage imageBar;
        try {
            imageBar = ImageIO.read(ClassLoader.getSystemResource(URL_IMAGE));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read the top bar file", e);
        }
        final int width = (int) (WIDTH_RATIO * ViewImpl.getScreenSize().getWidth());
        final int height = (int) (HEIGHT_RATIO * ViewImpl.getScreenSize().getHeight());
        final ImageIcon imageTopBar = ViewImpl.scaleImage(imageBar, width, height);
        this.topLabel = new JLabel(imageTopBar);
        this.topLabel.setLayout(new GridBagLayout());
        this.loadButton();
        this.topLabel.setBounds(0, 0, width, height);
        this.setLayout(new BorderLayout());
        this.add(topLabel);
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Load the button in the relevant label.
     */
    private void loadButton() {
        final JButton objective = new JButton("Obiettivo");
        final JButton attack = new JButton("Attacco");
        final JButton endTurn = new JButton("Fine turno");
        this.currentColor = this.main.getCurrentPlayer().getColor();
        this.currentPlayer = new JLabel("Player" + currentColor.getColor());
        final Font font = new Font("Serif", Font.BOLD, FONT_SIZE);
        objective.setFont(font);
        attack.setFont(font);
        endTurn.setFont(font);
        currentPlayer.setFont(font);
        final Insets insets = new Insets(8, DISTANCE_BUTTON_L_R, 8, DISTANCE_BUTTON_L_R);
        this.topLabel.add(currentPlayer,
            new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, insets, 0, 0));
        this.topLabel.add(objective,
            new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, insets, 0, 0));
        this.topLabel.add(attack, 
            new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, insets, 0, 0));
        this.topLabel.add(endTurn, 
            new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, insets, 0, 0));
    }

    private Color getLabelColor(GameColor color) {
        return switch (color) {
            case RED -> Color.RED;
            case GREEN -> Color.GREEN;
            case BLUE -> Color.BLUE;
            default -> throw new IllegalArgumentException("Invalid color");
        };
    }

    public void setColorPlayer(){
        currentPlayer.setForeground(getLabelColor(currentColor));
    }

    public void updateTopBar(){
        setColorPlayer();
    }
}
