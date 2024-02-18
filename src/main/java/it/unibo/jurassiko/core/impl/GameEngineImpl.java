package it.unibo.jurassiko.core.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jurassiko.controller.api.MainController;
import it.unibo.jurassiko.core.api.GameEngine;
import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.core.api.PlayerTurn;
import it.unibo.jurassiko.core.api.WinCondition;
import it.unibo.jurassiko.core.api.GamePhase.Phase;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;

/**
 * Implementation of the interface {@GameEngine}.
 */
public class GameEngineImpl implements GameEngine {

    private static final int MAX_PLAYERS = 3;
    private static final int FIRST_TURN_BONUS = 13;

    private final GamePhase gamePhase;
    private final PlayerTurn playerTurn;
    private final MainController controller;
    private final WinCondition winCondition;

    private boolean firstTurn;
    private boolean once;
    private Optional<Player> winner;

    /**
     * Contructor for the GameEngine.
     * 
     * @param controller the MainController used to interact with the view
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "MainController instance is needed on this class by design")
    public GameEngineImpl(final MainController controller) {
        this.gamePhase = new GamePhaseImpl();
        this.controller = controller;
        this.winCondition = new WinConditionImpl();
        try {
            this.playerTurn = new PlayerTurnImpl(this.controller.getPlayers());
        } catch (final CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to create a new istance of the player", e);
        }
        this.firstTurn = true;
        this.winner = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGameLoop() {
        placementPhase();
        movimentPhase();
        controller.updateBoard();
        if (isOver()) {
            controller.showWinnerName(getWinner().getColor());
            controller.closeGame();
        }
    }

    /**
     * Manage the Placement Phase of the game.
     */
    private void placementPhase() {
        if (firstTurn) {
            firstTurnPlacement();
            return;
        }
        if (gamePhase.getPhase().equals(GamePhase.Phase.PLACEMENT)) {
            controller.updateBoard();
            controller.openTerritorySelector();
            final var bonusGroundDino = playerTurn.getCurrentPlayerTurn().getBonusGroundDino();
            final var bonusWaterDino = playerTurn.getCurrentPlayerTurn().getBonusWaterDino();
            if (controller.getTotalClicks() == bonusGroundDino + bonusWaterDino) {
                gamePhase.goNext();
                controller.resetTotalClicks();
                controller.closeTerritorySelector();
            }
        }
    }

    /**
     * Init the first Placing Phase of the game.
     */
    private void firstTurnPlacement() {
        final var firstColor = playerTurn.getPlayers().get(0).getColor();
        controller.updateBoard();
        controller.openTerritorySelector();
        if (playerTurn.getCurrentPlayerTurn().getColor().equals(firstColor) && once) {
            controller.openObjectiveCard();
            once = false;
        }
        if (controller.getTotalClicks() == FIRST_TURN_BONUS) {
            playerTurn.goNext();
            controller.resetTotalClicks();
            controller.updateBoard();
            if (!playerTurn.getCurrentPlayerTurn().getColor().equals(firstColor)) {
                controller.openObjectiveCard();
            }
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
                + Math.toIntExact(temp.stream().filter(t -> t.x().equals(GameColor.RED)).count());
        return temp.stream()
                .mapToInt(value -> value.y())
                .sum() == initDinoAmount * MAX_PLAYERS;
    }

    private void movimentPhase() {
        if (gamePhase.getPhase().equals(Phase.MOVEMENT_FIRST_PART)) {
            controller.openTerritorySelector();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        playerTurn.goNext();
        while (playerTurn.getCurrentPlayerTurn().getOwnedTerritories().size() == 0) {
            playerTurn.goNext();
        }
        gamePhase.setPhase(Phase.PLACEMENT);
        controller.updateBoard();
    }

    /**
     * Is game Over?.
     * 
     * @return true if the game is over, false otherwise
     */
    private boolean isOver() {
        final var currentPlayer = this.controller.getCurrentPlayer();
        final Optional<Player> winner = this.winCondition
                .getWinner(this.controller.getTerritoriesMap(), currentPlayer, currentPlayer.getObjective());
        final boolean condition = winner.isPresent();
        if (condition) {
            this.winner = winner;
            return true;
        }
        return false;
    }

    /**
     * Get the winner of the game.
     * 
     * @return the winner of the game.
     */
    private Player getWinner() {
        return this.winner.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getGamePhase() {
        return gamePhase.getPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGamePhase(final Phase phase) {
        gamePhase.setPhase(phase);
        controller.updateBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemainingDinoToPlace() {
        final var currentPlayer = playerTurn.getCurrentPlayerTurn();
        return firstTurn ? FIRST_TURN_BONUS - controller.getTotalClicks()
                : currentPlayer.getBonusGroundDino() + currentPlayer.getBonusWaterDino() - controller.getTotalClicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayerTurn() {
        return playerTurn.getCurrentPlayerTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFirstTurn() {
        return firstTurn;
    }

}
