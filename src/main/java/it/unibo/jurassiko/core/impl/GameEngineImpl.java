package it.unibo.jurassiko.core.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.core.api.GameEngine;
import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.core.api.PlayerTurn;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

public class GameEngineImpl implements GameEngine {

    private static final int MAX_PLAYERS = 3;

    private final GamePhase gamePhase;
    private final PlayerTurn playerTurn;
    private final Random ran;
    private Map<Territory, Pair<GameColor, Integer>> territories;

    public GameEngineImpl() {
        this.gamePhase = new GamePhaseImpl();
        this.ran = new Random();
        this.playerTurn = new PlayerTurnImpl(initPlayer());
    }

    private List<Player> initPlayer() {
        final var objectives = new ArrayList<>(new ObjectiveFactoryImpl().createObjectives());
        Collections.shuffle(objectives);
        final var territoryIte = shuffleTerritories(new TerritoryFactoryImpl().createTerritories());
        final List<Player> result = new ArrayList<>();
        result.add(createPlayer(Player.GameColor.BLUE,
                objectives.get(ran.nextInt(objectives.size())),
                territoryIte.next()));
        result.add(createPlayer(Player.GameColor.GREEN,
                objectives.get(ran.nextInt(objectives.size())),
                territoryIte.next()));
        result.add(createPlayer(Player.GameColor.RED,
                objectives.get(ran.nextInt(objectives.size())),
                territoryIte.next()));

        return result;
    }

    private Player createPlayer(final Player.GameColor color, final Objective obj, final List<Territory> t) {
        return new PlayerImpl(Player.GameColor.BLUE,
                obj.getClone(),
                new HashSet<>(t),
                new HashSet<>());
    }

    private Iterator<List<Territory>> shuffleTerritories(Set<Territory> t) {
        final var territories = new ArrayList<>(new TerritoryFactoryImpl().createTerritories());
        Collections.shuffle(territories);
        final List<List<Territory>> terrList = new ArrayList<>();
        final List<Territory> temp = new ArrayList<>();
        for (final var x : territories) {
            temp.add(x);
            if (temp.size() == territories.size() / MAX_PLAYERS) {
                terrList.add(new ArrayList<>(temp));
                temp.clear();
            }
        }
        return terrList.iterator();
    }

    @Override
    public void startGameLoop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGameLoop'");
    }

    @Override
    public void startPlacing() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startPlacing'");
    }

    @Override
    public void startCombat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startCombat'");
    }

    @Override
    public void movimentPhase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movimentPhase'");
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

    @Override
    public Player getWinner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWinner'");
    }

}
