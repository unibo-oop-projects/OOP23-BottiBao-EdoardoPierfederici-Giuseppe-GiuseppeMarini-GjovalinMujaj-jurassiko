package it.unibo.jurassiko.model.dice.impl;

import java.util.Random;

import it.unibo.jurassiko.model.dice.api.Dice;

/**
 * Implementation of {@link Dice} interface
 */
public class DiceImpl implements Dice{

    private static final int DICE_FACES = 6;
    private final Random random = new Random(System.currentTimeMillis());

    /**
     * {@inheritDoc}
     */
    @Override
    public int roll() {
        return random.nextInt(DICE_FACES) + 1;
    }
    
}
