package it.unibo.jurassiko.model.dice.impl;

import java.util.Random;

import it.unibo.jurassiko.model.dice.api.Dice;

public class DiceImpl implements Dice{

    //take the ran seed with the current time to make it as random as possible
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
    
}
