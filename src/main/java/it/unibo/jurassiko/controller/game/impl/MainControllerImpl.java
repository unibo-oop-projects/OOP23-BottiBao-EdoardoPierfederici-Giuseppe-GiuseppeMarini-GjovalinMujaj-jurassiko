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
    private final Map<Territory, Pair<GameColor,Integer>> mapTerritories;
    private final Map<Ocean, Map<GameColor,Integer>> mapOcean;

    Player greenPlayer, bluePlayer, redPlayer;

    MainControllerImpl() {
        this.allTerritories = new TerritoryFactoryImpl().createTerritories();
        this.oceans = new OceanFactoryImpl().createOceans();
        this.objectives = new ObjectiveFactoryImpl().createObjectives();
        this.mapTerritories = new HashMap<>();
        this.mapOcean = new HashMap<>();
        createPlayers();
        fullTerritories();
        fullOcean();
        
    }

    public void updateView() {
        
    }

    private GameColor getColorTerritory(final Territory terr){
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

    private GameColor getColorOcean(final Ocean oc){
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

    private void createPlayers() {
        final Set<Territory> copyTerritories = new HashSet<>(this.allTerritories);
        final Set<Objective> copyObjectives = new HashSet<>(this.objectives);
        final Set<Ocean> copyOceans = new HashSet<>(this.oceans);
        this.greenPlayer = new PlayerImpl(GameColor.GREEN,
            shuffleObjective(copyObjectives),
            shuffleTerritories(copyTerritories, MAX_TERRITORIES),
            shuffleOcean(copyOceans), 0, 0);
        this.bluePlayer = new PlayerImpl(GameColor.BLUE, 
            shuffleObjective(copyObjectives),
            shuffleTerritories(copyTerritories, MAX_TERRITORIES),
            shuffleOcean(copyOceans), 0, 0);
        this.redPlayer = new PlayerImpl(GameColor.RED, 
            shuffleObjective(copyObjectives),
            shuffleTerritories(copyTerritories, MAX_TERRITORIES),
            shuffleOcean(copyOceans), 0, 0);
    }

    private Set<Territory> shuffleTerritories(final Set<Territory> territories, final int maxTerritories) {
        final Set<Territory> temp = territories.stream()
            .limit(maxTerritories)
            .collect(Collectors.toSet());
        territories.removeAll(temp);
        return temp;
    }

    private Objective shuffleObjective(final Set<Objective> objective) {
        final Objective temp = objective.stream().findFirst().get();
        objective.remove(temp);
        return temp;
    }

    private Set<Ocean> shuffleOcean(final Set<Ocean> oceans){
        final Set<Ocean> temp = oceans.stream().findFirst().stream().collect(Collectors.toSet());
        oceans.removeAll(temp);
        return temp;
    }

    private void fullOcean() {
        oceans.stream().forEach(ocean -> {
            final Map<GameColor, Integer> mapColor = new HashMap<>();
            mapColor.put(getColorOcean(ocean), START_AMOUNT_DINO);
            mapOcean.put(ocean, mapColor);
        });
    }

    private void fullTerritories(){
        allTerritories.stream().forEach(terr -> 
            mapTerritories.put(terr, new Pair<>(getColorTerritory(terr), START_AMOUNT_DINO)));
    }

}
