package it.unibo.jurassiko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.jurassiko.controller.impl.MenuContollerImpl;
import it.unibo.jurassiko.view.gamescreen.impl.StartMenu;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Main class of the application.
 */
public final class Jurassiko {

    private static Logger logger = LoggerFactory.getLogger(Jurassiko.class);

    /**
     * Private constructor to protect the main class.
     */
    private Jurassiko() {
    }

    /**
     * Main method to launch the application.
     * 
     * @param args params
     */
    public static void main(final String[] args) {
        logger.info("Application started.");
        new StartMenu();
        
    }
}
