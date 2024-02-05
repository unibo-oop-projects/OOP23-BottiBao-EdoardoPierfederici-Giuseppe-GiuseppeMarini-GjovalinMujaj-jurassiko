package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.battle.api.Battle;
import it.unibo.jurassiko.model.battle.impl.BattleImpl;

/**
 * Test for the Dice Class.
 */
public class TestBattle {

    private Battle battle;
    private List<Integer> results;
    private Pair<Integer, Integer> dinoDeaths;

    /**
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        this.battle = new BattleImpl();
        this.results = new ArrayList<>();
        // CHECKSTYLE: MagicNumber OFF
        // disable for test purpose
    }

    @Test
    public void testCheckTroops() {
        assertFalse(this.battle.checkTroops(0));
        assertFalse(this.battle.checkTroops(4));
        for (int i = 1; i < 4; i++) {
            assertTrue(this.battle.checkTroops(i));
        }
    }

    @Test
    public void testRollDefense() {
        this.results = this.battle.rollDefense(3);
        for (Integer element : this.results) {
            assertTrue(element < 7 || element > 0);
        }
    }

    @Test
    public void testRollAttacco() {
        this.results = this.battle.rollAttack(3);
        for (Integer element : this.results) {
            assertTrue(element < 7 || element > 0);
        }
    }

    @Test
    public void testBattle() {
        assertEquals(dinoDeaths, this.battle.attack());
    }

}
