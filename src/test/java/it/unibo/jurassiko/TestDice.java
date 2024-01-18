package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.dice.impl.DiceImpl;

public class TestDice {
    private DiceImpl dice;

    @BeforeEach
    public void setUp(){
        this.dice = new DiceImpl();
    }

    @Test
    public void testSetUp(){
        assertNotEquals(null, dice);
    }

    @Test
    public void testRoll(){
        Set<Integer> diceValues = new HashSet<>();
        for (int i = 1; i <=6; i++){
            diceValues.add(i);
        }
        for (int i = 0; i < 100; i++){
            assertTrue(diceValues.contains(dice.roll()));
        }
    }
    
}