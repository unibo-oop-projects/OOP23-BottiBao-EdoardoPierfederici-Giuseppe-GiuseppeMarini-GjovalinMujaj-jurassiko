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
import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.controller.game.impl.MainControllerImpl;
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
    private MainController controller;
    private final Random ran;
    private Map<Territory, Pair<GameColor, Integer>> territories;

    public GameEngineImpl(){
        this.gamePhase = new GamePhaseImpl();
        this.ran = new Random();
        this.controller = new MainControllerImpl();
        try {
            this.playerTurn = new PlayerTurnImpl(this.controller.getPlayers());
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to create a new istance of the player", e);
        }
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

    @Override
    public GamePhase getGamePhase() {
        return new GamePhaseImpl(gamePhase);
    }

    @Override
    public PlayerTurn getPlayerTurn() {
        return new PlayerTurnImpl(playerTurn);
    }

}
