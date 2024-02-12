package it.unibo.jurassiko.core.api;

public interface GamePhase {

    enum Phase {
        PLACEMENT, ATTACK, MOVEMENT
    }

    /**
     * Get the Phase of the game.
     * 
     * @return the Phase of the game.
     */
    Phase getPhase();

    /**
     * Go to the next Phase.
     */
    void goNext();
}
