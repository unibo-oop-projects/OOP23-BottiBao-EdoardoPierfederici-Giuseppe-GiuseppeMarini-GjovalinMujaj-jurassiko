package it.unibo.jurassiko.controller.game.api;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.view.window.TerritorySelector;

import java.util.List;
import java.util.Map;

public interface MainController {
    /**
     * Get for the map of territories.
     * 
     * @return map of the territories with the color and the amount of dino
     */
    Map<Territory, Pair<GameColor, Integer>> getTerritoriesMap();

    List<Player> getPlayers() throws CloneNotSupportedException;

    void openTerritorySelector();

    void closeTerritorySelector();

    void openView();

    GamePhase getGamePhase();

    void placeGroundDino(String territoryName, int amount);

    void updateBoard();

    TerritorySelector getTerritorySelector();

    void startGameLoop();

    boolean isPlayerTerritory(String territoryName);

}
