package it.unibo.jurassiko.model.player.api;

public interface Player {
    enum PlayerColor {
        Color1, Color2
    }

    PlayerColor getColor();

    // ? getObjective();

    
}
