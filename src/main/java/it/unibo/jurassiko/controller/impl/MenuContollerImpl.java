package it.unibo.jurassiko.controller.impl;

import it.unibo.jurassiko.controller.api.MenuController;
import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.controller.game.impl.MainControllerImpl;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Implementation of {@link MenuController} interface.
 */
public class MenuContollerImpl implements MenuController {

    private final MainController mainContr = new MainControllerImpl();
    private final ViewImpl mainFrame = new ViewImpl(mainContr);

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        mainFrame.display();
        mainFrame.updatePanel();
        mainContr.openTerritorySelector();
    }
}
