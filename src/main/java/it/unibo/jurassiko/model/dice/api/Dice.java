package it.unibo.jurassiko.model.dice.api;

import java.util.List;

/**
 * Interface for the Dice.
 */
public interface Dice {

    /**
     * @return a random number between 1 to 6
     */
    int roll();

    /**
     * @param amount is the amount of rolls
     * @return {@code List} of integers
     */
    List<Integer> rollMultiple(int amount);
}
