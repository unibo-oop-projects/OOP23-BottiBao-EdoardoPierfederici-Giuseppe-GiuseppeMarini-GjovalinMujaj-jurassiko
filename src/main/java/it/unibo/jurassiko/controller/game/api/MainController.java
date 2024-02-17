package it.unibo.jurassiko.controller.game.api;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
     * Gets the pair containing the ocean and the current owner.
     * 
     * @return a pair with the selected ocean and its owner.
     */
    Optional<Pair<Ocean, GameColor>> getCurrentOcean();

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
    boolean isAllyTerritory(String territoryName);

    /**
     * Same as isAllyTerritory but it must have more than one Dino.
     * 
     * @param territoryName name of the territory
     * @return true if current player has the territory with more than one dino
     */
    boolean isAllyTerritoryWithMoreThanOne(String territoryName);

    /**
     * Checks if the input territory has al least one adjEnemy.
     * 
     * @param territoryName name of the territory
     * @return true if has one adj Enemy, false otherwise
     */
    boolean hasAdjEnemy(String territoryName);

    /**
     * Checks if the input territory has al least one adjAlly.
     * 
     * @param territoryName name of the territory
     * @return true if has one adj Ally, false otherwise
     */
    boolean hasAdjAlly(String territoryName);

    /**
     * Get a Set of Adj territory name of the input.
     * 
     * @param territoryName name of the territory
     * @return a Set of String, name of the territory,
     *         that is Adj to the territoryName
     */
    Set<String> getAdj(String territoryName);

    /**
     * Returns current player.
     * 
     * @return current player
     */
    Player getCurrentPlayer();

    /**
     * Get the SelectedTerritory from the TerritorySelector.
     * 
     * @return the Name of the Territory if is present, Optional.empty otherwise
     */
    Optional<String> getSelectedTerritory();

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
    boolean isFirstTurn();

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

    public int getDinoToPlace();

    void showWinnerName(GameColor winner);

    void closeGame();
}
