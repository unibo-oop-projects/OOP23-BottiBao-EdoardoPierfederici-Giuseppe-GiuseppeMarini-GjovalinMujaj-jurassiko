package it.unibo.jurassiko.core.api;

/**
 * Interface for the GamePhase, manage the phase of the game.
 */
public interface GamePhase {
    /**
     * The Phase of the game.
     */
    enum Phase {
        /**
         * The placement phase, the player has to place
         * his bonus dino at the start of every turn.
         */
        PLACEMENT,
        /**
         * The attack phase, the player can attack other territories
         * in this phase or can decide to not attack.
         */
        ATTACK,
        /**
         * The movement phase, the player can move the dino from
         * one of his territoriy to other adj territories.
         */
        MOVEMENT
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

    /**
     * Change into specific Phase.
     * 
     * @param phase of the game to set
     */
    void setPhase(Phase phase);
}
