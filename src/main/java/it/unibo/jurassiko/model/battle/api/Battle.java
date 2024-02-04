package it.unibo.jurassiko.model.battle.api;

import java.util.List;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.territory.impl.*;

public interface Battle {
    
    Pair<Integer, Integer> attack(int ntruppeattacco, TerritoryImpl paesepartenzattacco, TerritoryImpl paeseattaccato);

    List<Integer> tiroattacco(int ntruppeattacco);

    List<Integer> tirodifesa(int ntruppedifesa);

    boolean checktruppe(int ntruppe);

    boolean checkterritori(TerritoryImpl paesepartenzattacco, TerritoryImpl paeseattaccato);
    
}
