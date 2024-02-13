package it.unibo.jurassiko.controller.game.api;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Territory;

import java.util.List;
import java.util.Map;

public interface MainController {
    /**
     * Get for the map of territories.
     * 
     * @return map of the territories with the color and the amount of dino
     */
    public Map<Territory, Pair<GameColor,Integer>> getTerritoriesMap();

    public List<Player> getPlayers() throws CloneNotSupportedException;
    
}
