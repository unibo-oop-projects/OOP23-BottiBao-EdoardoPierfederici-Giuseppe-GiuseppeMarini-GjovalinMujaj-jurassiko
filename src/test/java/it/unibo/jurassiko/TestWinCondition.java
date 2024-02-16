package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.controller.game.impl.MainControllerImpl;
import it.unibo.jurassiko.core.api.WinCondition;
import it.unibo.jurassiko.core.impl.WinConditionImpl;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.objective.impl.ConquerTerritoriesObjective;
import it.unibo.jurassiko.model.objective.impl.DestroyArmyObjective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Territory;

class TestWinCondition {

    private WinCondition winCondition;
    private MainController controller;
    private Set<Objective> objectives;
    private Map<Territory, Pair<GameColor, Integer>> initialMap;
    private Player initialPlayer;
    private GameColor initialPlayerColor;

    @BeforeEach
    void init() {
        this.winCondition = new WinConditionImpl();
        this.controller = new MainControllerImpl();
        this.objectives = new ObjectiveFactoryImpl().createObjectives();
        this.initialMap = this.controller.getTerritoriesMap();
        this.initialPlayer = this.controller.getCurrentPlayer();
        this.initialPlayerColor = this.initialPlayer.getColor();
    }

    @Test
    void testConquerContinents() {
        var defaultContinentsObjective = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .map(ConquerContinentsObjective.class::cast)
                .filter(o -> !o.isSelectableContinent())
                .findFirst()
                .get();
        /*
         * var selectableContinentsObjective = objectives.stream()
         * .filter(ConquerContinentsObjective.class::isInstance)
         * .map(ConquerContinentsObjective.class::cast)
         * .filter(o -> o.isSelectableContinent())
         * .findFirst()
         * .get();
         */

        // At the beginning of the game winner must be empty
        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, defaultContinentsObjective));
        /*
         * assertEquals(Optional.empty(),
         * this.winCondition.getWinner(this.initialMap, this.initialPlayer,
         * selectableContinentsObjective));
         */

        Set<String> defaultContinents = defaultContinentsObjective.getContinents();
        Map<Territory, Pair<GameColor, Integer>> testMap = new HashMap<>(this.initialMap);
        testMap.entrySet().forEach(e -> {
            String continent = e.getKey().getContinent();
            if (defaultContinents.contains(continent)) {
                e.setValue(new Pair<>(this.initialPlayerColor, 1));
            }
        });
        assertEquals(Optional.of(this.initialPlayer),
                this.winCondition.getWinner(testMap, this.initialPlayer, defaultContinentsObjective));

        // TODO: Fix or remove selectableContinent
        /*
         * Set<String> nonSelectableContinents =
         * selectableContinentsObjective.getContinents();
         * Map<Territory, Pair<GameColor, Integer>> testMap2 = new
         * HashMap<>(this.initialMap);
         * 
         * assertEquals(Optional.empty(),
         * this.winCondition.getWinner(this.initialMap, this.initialPlayer,
         * selectableContinentsObjective));
         */

    }

    @Test
    void testConquerTerritories() {
        var territoriesObjective = objectives.stream()
                .filter(ConquerTerritoriesObjective.class::isInstance)
                .map(ConquerTerritoriesObjective.class::cast)
                .findFirst()
                .get();
        int numTerritories = territoriesObjective.getNumTerritories();
        int minDinos = territoriesObjective.getMinDinos();

        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, territoriesObjective));

        Map<Territory, Pair<GameColor, Integer>> testMap = new HashMap<>(this.initialMap);
        // testMap.entrySet();
    }

    @Test
    void testDestroyArmy() {
        var armyObjective = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .map(DestroyArmyObjective.class::cast)
                .findFirst()
                .get();
        GameColor armyColor = armyObjective.getArmyColor();

        assertEquals(Optional.empty(),
                this.winCondition.getWinner(this.initialMap, this.initialPlayer, armyObjective));
    }
}
