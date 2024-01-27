package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.dice.api.Dice;
import it.unibo.jurassiko.model.dice.impl.DiceImpl;

/**
 * Test for the Dice Class
 */
public class TestDice {

    private Dice dice;
    private Set<Integer> possibleValues;

    @BeforeEach
    public void setUp() {
        this.dice = new DiceImpl();
        possibleValues = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            possibleValues.add(i);
        }
    }

    @Test
    public void testSetUp() {
        assertNotEquals(null, dice);
        for (int i = 1; i <= 6; i++) {
            assertTrue(possibleValues.contains(i));
        }
    }

    @Test
    public void testRoll() {
        for (int i = 0; i < 10; i++) {
            assertTrue(possibleValues.contains(dice.roll()));
        }
    }

    @Test
    public void testMultiple() {
        List<Integer> tempList;

        tempList = dice.rollMultiple(10);

        assertEquals(10, tempList.size());

        for (final int i : tempList) {
            assertTrue(possibleValues.contains(i));
        }

    }

}