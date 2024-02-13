package it.unibo.jurassiko.controller.impl;

import it.unibo.jurassiko.controller.api.MenuController;
import it.unibo.jurassiko.controller.game.impl.MainControllerImpl;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Implementation of {@link MenuController} interface.
 */
public class MenuContollerImpl implements MenuController {

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        new ViewImpl(new MainControllerImpl());
    }
}
