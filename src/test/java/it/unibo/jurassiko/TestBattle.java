package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.battle.api.Battle;
import it.unibo.jurassiko.model.battle.impl.BattleImpl;

/**
 * Test for the Dice Class.
 */
public class TestBattle {

    private static final int MAXNUMBER_DICE = 3;
    private static final int TROOPS_ATTACK = 2;
    private static final int TROOPS_DEFENCE = 2;
    private Battle battle;
    private Pair<Integer, Integer> dinoDeaths;

    /**
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        this.battle = new BattleImpl();
    }

    @Test
    public void testBattle() {
        for (int i = 1; i < MAXNUMBER_DICE; i++) {
            for (int j = 1; j < MAXNUMBER_DICE; j++) {
                if (i < TROOPS_ATTACK && j < TROOPS_DEFENCE) {
                    this.dinoDeaths = this.battle.attack(TROOPS_ATTACK, TROOPS_DEFENCE, i, j);
                    assertTrue(this.dinoDeaths.x() >= 0 && this.dinoDeaths.x() < i + 1 && this.dinoDeaths.y() >= 0
                            && this.dinoDeaths.y() < j + 1);
                    assertTrue(this.dinoDeaths.x() > 0 || this.dinoDeaths.y() > 0);
                }
            }
        }
    }
}
