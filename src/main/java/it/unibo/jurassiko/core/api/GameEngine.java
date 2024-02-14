package it.unibo.jurassiko.core.api;

import it.unibo.jurassiko.model.player.api.Player;

/**
 * TODO:
 */
public interface GameEngine {

    /**
     * Start game loop.
     */
    void startGameLoop();

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

    /**
     * Get a copy of the GamePhase.
     * 
     * @return a copy of the GamePhase
     */
    GamePhase getGamePhase();

    /**
     * Get a copy of the PlayerTurn.
     * 
     * @return a copy of the PlayerTurn
     */
    PlayerTurn getPlayerTurn();

    /**
     * Get the first turn.
     * 
     * @return true if its the first turn, false otherwise
     */
    boolean getFirstTurn();

}
