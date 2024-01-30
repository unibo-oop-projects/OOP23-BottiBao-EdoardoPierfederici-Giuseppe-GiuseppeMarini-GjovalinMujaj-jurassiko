package it.unibo.jurassiko.view;

import it.unibo.jurassiko.controller.api.StartController;

/**
 * Graphical representation of the start Menu.
 */
public final class StartMenu {

    private final JFrame frame = new JFrame();
    private final StartController controller;

    public StartMenu(StartController controller) {
        this.controller = controller;
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
    }
}
