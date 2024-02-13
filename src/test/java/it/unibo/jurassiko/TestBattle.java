package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.battle.api.Battle;
import it.unibo.jurassiko.model.battle.impl.BattleImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Test for the Dice Class.
 */
public class TestBattle {

    private static final int MAXNUMBER_DICE = 3;
    private Battle battle;
    private Pair<Integer, Integer> dinoDeaths;

    /**
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        var territories = new TerritoryFactoryImpl().createTerritories();
        var territorio1 = territories.stream().filter(t -> t.getName().equals("Messico")).findAny().get();
        var territorio2 = territories.stream().filter(t -> t.getName().equals("Canada")).findAny().get();
        this.battle = new BattleImpl(territorio1, territorio2);
    }

    @Test
    public void testBattle() {
        for (int i = 1; i < MAXNUMBER_DICE; i++) {
            for (int j = 1; j < MAXNUMBER_DICE; j++) {
                this.dinoDeaths = this.battle.attack(i, j);
                assertTrue(this.dinoDeaths.x() >= 0 && this.dinoDeaths.x() < i + 1 && this.dinoDeaths.y() >= 0
                        && this.dinoDeaths.y() < j + 1);
            }
        }
    }
}
