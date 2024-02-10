package it.unibo.jurassiko.view.gamescreen.impl;

import it.unibo.jurassiko.controller.impl.MenuContollerImpl;
import it.unibo.jurassiko.view.gamescreen.api.View;
import it.unibo.jurassiko.view.panel.MenuPanel;

import javax.swing.JFrame;

/**
 * Graphical representation of the start Menu.
 */
public class StartMenu extends JFrame implements View {

    private static final String TITLE = "Jurassiko";

    /**
     * Creating the frame for the Menu.
     */
    public StartMenu() {
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new MenuPanel(new MenuContollerImpl(), this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
