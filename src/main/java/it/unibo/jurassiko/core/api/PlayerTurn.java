package it.unibo.jurassiko.core.api;

import java.util.List;

import it.unibo.jurassiko.model.player.api.Player;

/**
 * Manage the turn of the Player.
 */
public interface PlayerTurn {
    /**
     * get current Player Color in the turn.
     * 
     * @return Player Color
     */
    Player.GameColor getCurrentPlayerTurn();

    /**
     * get all the Players.
     * 
     * @return Set of all the Players
     */
    List<Player> getPlayers();

    /**
     * Finish the Turn of the vurrent Player and go to the next Player.
     */
    void goNext();
}
