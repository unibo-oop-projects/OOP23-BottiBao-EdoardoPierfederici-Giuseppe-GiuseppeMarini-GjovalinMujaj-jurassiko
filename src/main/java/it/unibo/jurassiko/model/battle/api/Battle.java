package it.unibo.jurassiko.model.battle.api;

import it.unibo.jurassiko.common.Pair;

/**
 * Interface to calcolate the death of troops during the battle.
 */
public interface Battle {

    /**
     * Calculates the death of troops based on the roll of the attack and defense
     * dice.
     * 
     * @param nTroopsAttack  number of troops of attacking territory
     * @param nTroopsDefence number of troops of defending territory
     * @param nDiceAttack    number of dice rolled by the attack
     * @param nDiceDefence   number of dice rolled by the defence
     * @return a pair with the number of deaths
     */
    Pair<Integer, Integer> attack(int nTroopsAttack, int nTroopsDefence, int nDiceAttack, int nDiceDefence);

}
