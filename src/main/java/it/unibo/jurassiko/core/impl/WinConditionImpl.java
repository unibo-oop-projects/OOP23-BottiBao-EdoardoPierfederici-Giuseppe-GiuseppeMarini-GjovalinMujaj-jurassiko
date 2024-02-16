package it.unibo.jurassiko.core.impl;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.RowFilter.Entry;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.core.api.WinCondition;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.objective.impl.ConquerTerritoriesObjective;
import it.unibo.jurassiko.model.objective.impl.DestroyArmyObjective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;

public class WinConditionImpl implements WinCondition {

    private Optional<Player> winner;

    public WinConditionImpl() {
        this.winner = Optional.empty();
    }

    @Override
    public Optional<Player> getWinner(final Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            final Player player,
            final Objective objective) {
        checkWinCondition(territoriesMap, player, objective);
        return winner;
    }

    private void checkWinCondition(final Map<Territory, Pair<GameColor, Integer>> territoriesMap, final Player player,
            final Objective objective) {
        final var playerColor = player.getColor();
        switch (objective.getType()) {
            case "conquerContinents" -> {
                final var continentsObjective = (ConquerContinentsObjective) objective;
                setWinner(checkConquerContinents(territoriesMap, playerColor, continentsObjective), player);
            }
            case "conquerTerritories" -> {
                final var territoriesObjective = (ConquerTerritoriesObjective) objective;
                setWinner(checkConquerContinents(territoriesMap, playerColor, territoriesObjective), player);
            }
            case "destroyArmy" -> {
                final var armyObjective = (DestroyArmyObjective) objective;
                setWinner(checkDestroyArmy(territoriesMap, playerColor, armyObjective), player);
            }
            default -> throw new IllegalArgumentException("Invalid objective type");
        }
    }

    private void setWinner(final boolean winCondition, final Player player) {
        if (winCondition) {
            this.winner = Optional.of(player);
        }
    }

    private boolean checkConquerContinents(final Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            final GameColor playerColor,
            final ConquerContinentsObjective objective) {
        final Set<String> continents = objective.getContinents();

        final boolean continentsConquered = territoriesMap.entrySet().stream()
                .filter(t -> {
                    final String continent = t.getKey().getContinent();
                    return continents.contains(continent);
                })
                .allMatch(t -> t.getValue().x().equals(playerColor));

        // TODO: Fix or remove selectableContinent
        final boolean selectableContinentConquered = true;
        if (objective.isSelectableContinent()) {
            final var selectableContinents = territoriesMap.entrySet().stream()
                    .filter(t -> {
                        final String continent = t.getKey().getContinent();
                        return !continents.contains(continent);
                    })
                    .map(entry -> entry.getKey().getContinent());
        }

        return continentsConquered && selectableContinentConquered;
    }

    private boolean checkConquerContinents(final Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            final GameColor playerColor,
            final ConquerTerritoriesObjective objective) {
        final int territoryAmount = objective.getNumTerritories();
        final int minDinos = objective.getMinDinos();
        final boolean numberReached = territoriesMap.values().stream()
                .filter(t -> t.x().equals(playerColor))
                .filter(t -> t.y() >= minDinos)
                .count() >= territoryAmount;

        return numberReached;
    }

    private boolean checkDestroyArmy(final Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            final GameColor playerColor,
            final DestroyArmyObjective objective) {
        final var armyObjective = (DestroyArmyObjective) objective;
        final var armyColor = armyObjective.getArmyColor();
        final boolean checkColorPresence = territoriesMap.values().stream().noneMatch(p -> p.x().equals(armyColor));

        return checkColorPresence;
    }
}
