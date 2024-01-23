package it.unibo.jurassiko.model.player.api;

import java.util.Set;

public interface Player {

    enum PlayerColor {
        Color1, Color2
    }

    PlayerColor getColor();

    Objective getObjective();

    Set<Territorie> getOwnedTerritories();

    Set<Continent> getOwnedContinents();

    // ??? getBonusHand();

}
