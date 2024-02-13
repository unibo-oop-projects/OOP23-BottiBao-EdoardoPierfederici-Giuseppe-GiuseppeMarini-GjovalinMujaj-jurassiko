package it.unibo.jurassiko.model.battle.api;

import it.unibo.jurassiko.common.Pair;

public interface Battle {

    Pair<Integer, Integer> attack(int nTroopsAttack, int nTroopsDefence);

}
