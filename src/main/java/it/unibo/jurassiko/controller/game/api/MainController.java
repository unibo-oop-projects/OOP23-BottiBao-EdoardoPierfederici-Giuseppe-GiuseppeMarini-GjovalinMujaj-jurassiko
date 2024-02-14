package it.unibo.jurassiko.controller.game.api;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Territory;

import java.util.List;
import java.util.Map;

/**
 * Interface for the MainController, the core controller.
 */
public interface MainController {

    /**
     * Gets the map of territories.
     * 
     * @return map of the territories with the color and the amount of dino
     */
    Map<Territory, Pair<GameColor, Integer>> getTerritoriesMap();

    /**
     * Creates and returns a list of all the players.
     * 
     * @return list of the players
     * @throws CloneNotSupportedException if fails to clone the players
     */
    List<Player> getPlayers() throws CloneNotSupportedException;

    /**
     * Updates and shows the buttons in the frame.
     */
    void openTerritorySelector();

    /**
     * Closes the frame of territories.
     */
    void closeTerritorySelector();

    /**
     * Opens the OptionPane containing the objective.
     */
    void openObjectiveCard();

    /**
     * Shows and updates the main frame.
     */
    void openView();

    /**
     * Gets the actual game phase from the GameEngine.
     * 
     * @return actual game phase
     */
    GamePhase.Phase getGamePhase();

    /**
     * Adds an amount of dino at the territory in the map.
     * 
     * @param territoryName name of the territory
     * @param amount        amount of dino to add at the map
     */
    void placeGroundDino(String territoryName, int amount);

    /**
     * Updates the panel of dinos.
     */
    void updateBoard();

    /**
     * Tells the game engine to start the loop.
     */
    void startGameLoop();

    /**
     * Checks if the player has the territory in the map.
     * 
     * @param territoryName name of the territory
     * @return true if current player has the territory passed as input
     */
    boolean isPlayerTerritory(String territoryName);

    /**
     * Returns current player.
     * 
     * @return current player
     */
    Player getCurrentPlayer();

    /**
     * Based on the phase and the color of the current player.
     * It manages the various phases.
     * 
     * @param territory name of the territory
     */
    void manageSelection(String territory);

    /**
     * Resets the amount of total clicks.
     */
    void resetTotalClick();

    /**
     * Returns the amount of clicks.
     * 
     * @return amount of clicks
     */
    int getTotalClick();

    /**
     * @return true if its the first turn, false otherwise
     */
    boolean getFirstTurn();

    /**
     * Pass the Turn to the next Player.
     */
    void endTurn();

    /**
     * Set the phase of the Game.
     * 
     * @param phase phase to set
     */
    void setGamePhase(GamePhase.Phase phase);
}
