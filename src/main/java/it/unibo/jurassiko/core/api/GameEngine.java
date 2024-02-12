package it.unibo.jurassiko.core.api;

public interface GameEngine {

    /**
     * Initialize the game, assign the territories to the Players
     */
    void initGame();

    /**
     * Start game loop.
     */
    void startGameLoop();

    /**
     * Start Placing Dinos phase.
     */
    void startPlacing();

    /**
     * Start Combat phase.
     */
    void startCombat();

    /**
     * Start Moviment phase.
     */
    void movimentPhase();

}
