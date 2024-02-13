package it.unibo.jurassiko.model.battle.impl;

import java.util.List;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.battle.api.Battle;
import it.unibo.jurassiko.model.dice.api.Dice;
import it.unibo.jurassiko.model.dice.impl.DiceImpl;
import it.unibo.jurassiko.model.territory.api.Territory;

public class BattleImpl implements Battle {

    private static final int MAXNUMBER_DICE = 3;
    Dice dice = new DiceImpl();
    Territory attacker;
    Territory defender;

    public BattleImpl(Territory tAttacker, Territory tDefender) {
        attacker = tAttacker;
        defender = tDefender;
    }

    @Override
    public Pair<Integer, Integer> attack(final int nTroopsAttack, final int nTroopsDefence) {
        List<Integer> listRollAttack = dice.rollMultiple(nTroopsAttack);
        List<Integer> listRollDefence = dice.rollMultiple(nTroopsDefence);
        int nTroopsAttackDeath = 0;
        int nTroopsDefenceDeath = 0;
        // if (checkTroops(attacker, nTroopsAttack, true) && checkTroops(defender,
        // nTroopsDefence, false)) {
        for (int i = 0; i < MAXNUMBER_DICE; i++) {
            if (listRollDefence.get(i) >= listRollAttack.get(i)) {
                nTroopsAttackDeath++;
            } else {
                nTroopsDefenceDeath++;
            }
            // }
        }
        return new Pair<Integer, Integer>(nTroopsAttackDeath, nTroopsDefenceDeath);
    }

    // private boolean checkTroops(Territory terrytory, final int nTroops, boolean
    // offensive) {
    // return offensive ? terrytory.getDinoAmount() > nTroops &&
    // terrytory.getDinoAmount() != 1
    // : terrytory.getDinoAmount() > nTroops;
    // }
}
