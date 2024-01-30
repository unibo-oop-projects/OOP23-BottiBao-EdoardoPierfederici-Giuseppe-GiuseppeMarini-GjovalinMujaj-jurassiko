package it.unibo.jurassiko.view;

import it.unibo.jurassiko.controller.api.StartController;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Graphical representation of the start Menu.
 */
public final class StartMenu {
    private static final String START = "Start";
    private static final String QUIT = "Quit";
    private final JFrame frame = new JFrame();
    // private final StartController controller;

    /**
     * Creating the panel for the Menu.
     * 
     * @param controller the controller used to control the menu panel
     */
    public StartMenu(final StartController controller) {
        // TODO: do the starting controller
        // this.controller = controller;
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JButton start = new JButton(START);
        final JButton quit = new JButton(QUIT);
        final JPanel eastPanel = new JPanel();
        eastPanel.add(start);
        eastPanel.add(quit);
        canvas.add(eastPanel, BorderLayout.EAST);

        // TODO: start game button
        // start.addActionListener(e -> startgame);

        quit.addActionListener(e -> {
            final String[] options = { "Yes", "No" };
            final var result = JOptionPane.showOptionDialog(frame, "Do you want to QUIT the game?",
                    QUIT,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options);
            if (result == 0) {
                frame.dispose();
            }
        });

        // TODO: DA MODIFICARE
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);
        frame.setVisible(true);
    }
    /*
     * For Testing Purpose
     * public static void main(String[] args) {
     * new StartMenu(new StartContollerImpl());
     * }
     */
}
