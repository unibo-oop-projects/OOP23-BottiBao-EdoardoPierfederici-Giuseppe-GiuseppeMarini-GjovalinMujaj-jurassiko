package it.unibo.jurassiko.model.battle.impl;

import java.util.List;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.battle.api.Battle;
import it.unibo.jurassiko.model.dice.impl.*;
import it.unibo.jurassiko.model.player.impl.*;
import it.unibo.jurassiko.model.territory.impl.*;

public class BattleImpl implements Battle {

    @Override
    public Pair<Integer,Integer> attack(int ntruppeattacco, TerritoryImpl paesepartenzattacco, TerritoryImpl paeseattaccato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public List<Integer> tiroattacco(int ntruppeattacco) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tiroattacco'");
    }

    @Override
    public List<Integer> tirodifesa(int ntruppedifesa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tirodifesa'");
    }

    @Override
    public boolean checktruppe(int ntruppe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checktruppe'");
    }

    @Override
    public boolean checkterritori(TerritoryImpl paesepartenzattacco, TerritoryImpl paeseattaccato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkterritori'");
    }
    
}
