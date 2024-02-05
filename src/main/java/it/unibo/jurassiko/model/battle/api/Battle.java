package it.unibo.jurassiko.model.battle.api;

import java.util.List;

import it.unibo.jurassiko.common.Pair;

public interface Battle {

    Pair<Integer, Integer> attack();

    List<Integer> rollAttack(int nTroopsAttack);

    List<Integer> rollDefense(int nTroopsDefense);

    boolean checkTroops(int nTroops);

}
