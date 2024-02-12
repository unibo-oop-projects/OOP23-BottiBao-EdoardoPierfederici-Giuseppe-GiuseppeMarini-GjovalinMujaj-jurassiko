package it.unibo.jurassiko.core.impl;

import it.unibo.jurassiko.core.api.GamePhase;

public class GamePhaseImpl implements GamePhase{

    private Phase index;

    public GamePhaseImpl(){
        index = Phase.PLACEMENT;
    }

    @Override
    public Phase getPhase() {
        return index;
    }

    @Override
    public void goNext() {
        index = Phase.values()[(index.ordinal() + 1) % Phase.values().length];
    }
    
}
