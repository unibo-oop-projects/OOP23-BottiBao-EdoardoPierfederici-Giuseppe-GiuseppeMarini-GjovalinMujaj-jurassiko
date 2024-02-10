package it.unibo.jurassiko.view.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.jurassiko.controller.api.MenuController;
import it.unibo.jurassiko.controller.impl.MenuContollerImpl;
import it.unibo.jurassiko.view.gamescreen.impl.StartMenu;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

public class MenuPanel extends JPanel {

    private static final String START = "Start";
    private static final String QUIT = "Quit";
    private static final int FONT_SIZE = 24;
    private static final Dimension PREFERED_SIZE = new Dimension(200,50);
    private final MenuController controller;
    private final JFrame frame;

    public MenuPanel(final MenuContollerImpl controller, final StartMenu frame) {
        this.controller = controller;
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        loadButton();
    }

    private void loadButton() {
        final Font font = new Font("Serif", Font.BOLD, FONT_SIZE);
        final JButton start = createButton(START, font);
        final JButton quit = createButton(QUIT, font);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        addButton(start, gbc, PREFERED_SIZE);
        addButton(quit, gbc, PREFERED_SIZE);
        start.addActionListener(e -> {
            this.frame.dispose();
            this.controller.startGame();
        });

        quit.addActionListener(e -> {
            final String[] options = { "Yes", "No" };
            final var result = JOptionPane.showOptionDialog(this, "Do you want to QUIT the game?",
                    QUIT,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, options, options);
            if (result == 0) {
                this.frame.dispose();
            }
        });
    }

    private JButton createButton(final String name, final Font font){
        final JButton button = new JButton(name);
        button.setFont(font);
        return button;
    }

    private void addButton(final JButton button, GridBagConstraints gbc, final Dimension dimension){
        button.setPreferredSize(dimension);
        this.add(button, gbc);
        gbc.gridy++;
    }
}
