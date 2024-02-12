package it.unibo.jurassiko.controller.game.impl;

import java.util.*;
import java.util.stream.Collectors;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

public class MainControllerImpl implements MainController {

    private static final int MAX_TERRITORIES = 7;
    private static final int START_AMOUNT_DINO = 1;

    private final Set<Territory> allTerritories;
    private final Set<Ocean> oceans;
    private final Set<Objective> objectives;
    private Map<Territory, Pair<GameColor,Integer>> mapTerritories;
    private Map<Ocean, GameColor> mapOcean;

    private Player greenPlayer, bluePlayer, redPlayer;

    public MainControllerImpl() {
        this.allTerritories = new TerritoryFactoryImpl().createTerritories();
        this.oceans = new OceanFactoryImpl().createOceans();
        this.objectives = new ObjectiveFactoryImpl().createObjectives();
        createPlayers();
        fullTerritories();
        fullOcean();
    }

    public Set<Territory> getTerritories(GameColor color){
        var setTerr = mapTerritories.entrySet()
            .stream()
            .filter(s -> s.getValue().x().equals(color))
            .map(s -> s.getKey())
            .collect(Collectors.toSet());
        return setTerr;
    }

    public Map<Territory, Pair<GameColor,Integer>> getTerritoriesMap() {
        return Map.copyOf(mapTerritories);
    }

    /**
     * Returns the color of the territory that we passed as input
     */
    private GameColor getColorTerritory(final Territory terr) {
        if(greenPlayer.getOwnedTerritories().contains(terr)){
            return greenPlayer.getColor();
        }
        if(bluePlayer.getOwnedTerritories().contains(terr)){
            return bluePlayer.getColor();
        }
        if(redPlayer.getOwnedTerritories().contains(terr)){
            return redPlayer.getColor();
        }
        throw new IllegalArgumentException("Territory not valid");
    }
/* 
    private GameColor getColorOcean(final Ocean oc) {
        if(greenPlayer.getOwnedOceans().contains(oc)){
            return greenPlayer.getColor();
        }
        if(bluePlayer.getOwnedOceans().contains(oc)){
            return bluePlayer.getColor();
        }
        if(redPlayer.getOwnedOceans().contains(oc)){
            return redPlayer.getColor();
        }
        throw new IllegalArgumentException("Ocean not valid");
    }
*/
    /**
     * Create all the players using the methods that we have created, 
     * inside the constructor.
     */
    private void createPlayers() {
        final Set<Territory> copyTerritories = new HashSet<>(this.allTerritories);
        final Set<Objective> copyObjectives = new HashSet<>(this.objectives);
        this.greenPlayer = new PlayerImpl(GameColor.GREEN,
            shuffleObjective(copyObjectives),
            shuffleTerritories(copyTerritories, MAX_TERRITORIES),
            Set.of());
        this.bluePlayer = new PlayerImpl(GameColor.BLUE, 
            shuffleObjective(copyObjectives),
            shuffleTerritories(copyTerritories, MAX_TERRITORIES),
            Set.of());
        this.redPlayer = new PlayerImpl(GameColor.RED, 
            shuffleObjective(copyObjectives),
            shuffleTerritories(copyTerritories, MAX_TERRITORIES),
            Set.of());
    }

    /**
     * Shuffle all the territories and return 7 territories
     * for the current player.
     * 
     * @param territories all territories in the game
     * @param maxTerritories max territories for each player
     * @return a set of 7 territories
     */
    private Set<Territory> shuffleTerritories(final Set<Territory> territories, final int maxTerritories) {
        final Set<Territory> temp = territories.stream()
            .limit(maxTerritories)
            .collect(Collectors.toSet());
        territories.removeAll(temp);
        return temp;
    }

    /**
     * Return a single objective for the current player and
     * remove it from the set.
     * 
     * @param objective all of the objectives
     * @return a single objective for the corresponding player
     */
    private Objective shuffleObjective(final Set<Objective> objective) {
        final Objective temp = objective.stream().findFirst().get();
        objective.remove(temp);
        return temp;
    }
/* 
    private Set<Ocean> shuffleOcean(final Set<Ocean> oceans) {
        final Set<Ocean> temp = oceans.stream().findFirst().stream().collect(Collectors.toSet());
        oceans.removeAll(temp);
        return temp;
    }
*/
    /**
     * Full the mapOcean only with the oceans
     */
    private void fullOcean() {
        mapOcean = new HashMap<>();
        oceans.stream().forEach(ocean -> 
            mapOcean.put(ocean, null));
    }

    /**
     * Full the mapTerritories with the territories and
     * the corresponding color and initial amout.
     */
    private void fullTerritories() {
        mapTerritories = new HashMap<>();
        allTerritories.stream().forEach(terr -> 
            mapTerritories.put(terr, new Pair<>(getColorTerritory(terr), START_AMOUNT_DINO)));
    }

}
