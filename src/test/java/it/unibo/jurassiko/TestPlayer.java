package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;



public class TestPlayer {
    private Player player;

    @BeforeEach
    public void setUp() {
        this.player = new PlayerImpl();
    }

    @Test
    public void testSetUp() {
        assertNotEquals(null, player);
    }

    @Test
    public void testRoll() {
        final Set<Integer> diceValues = new HashSet<>();
        final List<Integer> counter = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            diceValues.add(i);
            counter.add(0);
        }

        for (int i = 0; i < 100; i++) {
            final int temp = dice.roll();
            assertTrue(diceValues.contains(temp));
            counter.set(temp - 1, counter.get(temp - 1) + 1);
        }
        System.out.println(counter);
    }

}