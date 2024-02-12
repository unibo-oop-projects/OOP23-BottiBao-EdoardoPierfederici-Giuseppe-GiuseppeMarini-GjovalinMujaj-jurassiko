package it.unibo.jurassiko.core.impl;

import java.util.List;

import it.unibo.jurassiko.core.api.PlayerTurn;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;

public class PlayerTurnImpl implements PlayerTurn {

    private final List<Player> players;
    private int index;

    public PlayerTurnImpl(final List<Player> players) {
        this.players = players;
        this.players.sort((o1, o2) -> o2.getColor().getColor().compareTo(o2.getColor().getColor()));
        index = 0;
    }

    @Override
    public GameColor getCurrentPlayerTurn() {
        return players.get(index).getColor();
    }

    @Override
    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    @Override
    public void goNext() {
        index++;
        if (index > players.size()) {
            index = 0;
        }
    }

}
