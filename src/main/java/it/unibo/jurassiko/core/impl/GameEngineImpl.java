package it.unibo.jurassiko.core.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private static final int FIRST_TURN_BONUS = 13;
    private static final long PERIOD = 1000;

    private final GamePhase gamePhase;
    private final PlayerTurn playerTurn;
    private MainController controller;
    private final Random ran;
    private boolean firstTurn;

    public GameEngineImpl(MainController controller) {
        this.gamePhase = new GamePhaseImpl();
        this.ran = new Random();
        this.controller = controller;
        try {
            this.playerTurn = new PlayerTurnImpl(this.controller.getPlayers());
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to create a new istance of the player", e);
        }
        this.firstTurn = true;
    }

    @Override
    public void startGameLoop() {
        startPlacing();
    }

    @Override
    public void startPlacing() {
        final var bonusDino = playerTurn.getCurrentPlayerTurn().getBonusGroundDino();
        if (firstTurn) {
            firstTurnPlacing();
            return;
        }
        if (gamePhase.getPhase().equals(GamePhase.Phase.PLACEMENT)) {
            //TODO
        }
    }

    private void firstTurnPlacing() {
        controller.openTerritorySelector();
        final var tSelector = controller.getTerritorySelector();
        if (tSelector.getTotalClick() == FIRST_TURN_BONUS) {
            playerTurn.goNext();
            tSelector.resetTotalClick();
            if (checkInitDino()) {
                controller.closeTerritorySelector();
                firstTurn = false;
            }
        }
    }

    private boolean checkInitDino() {
        final var temp = controller.getTerritoriesMap().values();
        final var initDinoAmount = FIRST_TURN_BONUS
                + (int) temp.stream().filter(t -> t.x().equals(GameColor.RED)).count();
        return temp.stream()
                .mapToInt(value -> value.y())
                .sum() == initDinoAmount * MAX_PLAYERS;
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
