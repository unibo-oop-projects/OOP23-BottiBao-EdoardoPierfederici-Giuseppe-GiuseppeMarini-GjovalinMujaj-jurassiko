package it.unibo.jurassiko.core.impl;

import it.unibo.jurassiko.controller.game.api.MainController;

import it.unibo.jurassiko.core.api.GameEngine;
import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.core.api.PlayerTurn;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;

public class GameEngineImpl implements GameEngine {

    private static final int MAX_PLAYERS = 3;
    private static final int FIRST_TURN_BONUS = 13;

    private final GamePhase gamePhase;
    private final PlayerTurn playerTurn;
    private MainController controller;

    private boolean firstTurn;

    /**
     * Contructor for the GameEngine.
     * 
     * @param controller the MainController used to interact with the view
     */
    public GameEngineImpl(MainController controller) {
        this.gamePhase = new GamePhaseImpl();

        this.controller = controller;
        try {
            this.playerTurn = new PlayerTurnImpl(this.controller.getPlayers());
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to create a new istance of the player", e);
        }
        this.firstTurn = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGameLoop() {
        startPlacing();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPlacing() {
        final var bonusDino = playerTurn.getCurrentPlayerTurn().getBonusGroundDino();
        if (firstTurn) {
            firstTurnPlacing();
            return;
        }
        if (gamePhase.getPhase().equals(GamePhase.Phase.PLACEMENT)) {
            // TODO
        }
    }

    /**
     * Init the first Placing Phase of the game.
     */
    private void firstTurnPlacing() {
        // controller.openObjectiveCard(); // TODO: it must open only once per player
        controller.openTerritorySelector();
        if (controller.getTotalClick() == FIRST_TURN_BONUS) {
            playerTurn.goNext();
            controller.resetTotalClick();
            if (checkInitDino()) {
                controller.closeTerritorySelector();
                firstTurn = false;
            }
        }
    }

    /**
     * Check if every player has placed the initial dino.
     * 
     * @return true if every player has placed the initial dino, false otherwise
     */
    private boolean checkInitDino() {
        final var temp = controller.getTerritoriesMap().values();
        final var initDinoAmount = FIRST_TURN_BONUS
                + (int) temp.stream().filter(t -> t.x().equals(GameColor.RED)).count();
        return temp.stream()
                .mapToInt(value -> value.y())
                .sum() == initDinoAmount * MAX_PLAYERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startCombat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startCombat'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movimentPhase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movimentPhase'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getWinner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWinner'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GamePhase getGamePhase() {
        return new GamePhaseImpl(gamePhase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerTurn getPlayerTurn() {
        return new PlayerTurnImpl(playerTurn);
    }

}
