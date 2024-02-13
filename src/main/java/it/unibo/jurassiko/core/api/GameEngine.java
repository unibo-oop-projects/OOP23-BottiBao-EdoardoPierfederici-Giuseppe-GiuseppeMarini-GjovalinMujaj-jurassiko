package it.unibo.jurassiko.core.api;

import it.unibo.jurassiko.model.player.api.Player;

/**
 * TODO: help?
 */
public interface GameEngine {

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

    /**
     * End the Turn of the Player.
     */
    void endTurn();

    /**
     * Is game Over?.
     * 
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

    /**
     * Get the winner of the game.
     * 
     * @return the winner of the game.
     */
    Player getWinner();

    GamePhase getGamePhase();

    PlayerTurn getPlayerTurn();

}
