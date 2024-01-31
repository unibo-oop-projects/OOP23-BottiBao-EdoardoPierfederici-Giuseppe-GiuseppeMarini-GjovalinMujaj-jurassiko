package it.unibo.jurassiko.model.player.api;

import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Interface who provide method for the Player
 */
public interface Player {
    /**
     * Enum for the colors
     */
    enum Color {
        RED("Red", 255, 0, 0),
        GREEN("Green", 0, 255, 0),
        BLUE("Blue", 0, 0, 255);

        private final String color;
        private final int red;
        private final int green;
        private final int blue;

        /**
         * Constructor for the Colors
         * 
         * @param color       color name
         * @param RedAmount   red color amount
         * @param GreenAmount green color amount
         * @param BlueAmount  blue color amount
         */
        private Color(final String color, final int RedAmount, final int GreenAmount, final int BlueAmount) {
            this.color = color;
            this.red = RedAmount;
            this.green = GreenAmount;
            this.blue = BlueAmount;
        }

        /**
         * Get the color name.
         * 
         * @return color name
         */
        public String getColor() {
            return color;
        }

        /**
         * Get the blue amount.
         * 
         * @return blue amount
         */
        public int getBlue() {
            return blue;
        }

        /**
         * Get the green amount.
         * 
         * @return green amount
         */
        public int getGreen() {
            return green;
        }

        /**
         * Get the red amount.
         * 
         * @return red amount
         */
        public int getRed() {
            return red;
        }
    }

    /**
     * Get the player color.
     * 
     * @return player color
     */
    Color getPlayerColor();

    /**
     * Set the player objective.
     * 
     * @param objective player objective
     */
    void setPlayerObjective(Objective objective);

    /**
     * Get the player Objective.
     * 
     * @return player objective
     */
    Objective getObjective();

    /**
     * @return {@code true} if the objective is completed, {@code false} if
     *         otherwise
     */
    boolean isObjectiveComplete();

    /**
     * Add a {@code Territory} to the player.
     * 
     * @param territory the territory to add
     */
    void addPlayerTerritory(Territory territory);

    /**
     * Remove a {@code Territory} to the player.
     * 
     * @param territory the territory to remove
     */
    void removePlayerTerritory(Territory territory);

    /**
     * Get a Set of {@code Territory} owned by the player.
     * 
     * @return set of player's territories
     */
    Set<Territory> getOwnedTerritories();

    /**
     * Add a {@code Ocean} to the player.
     * 
     * @param ocean the ocean to add
     */
    void addPlayerOcean(Ocean ocean);

    /**
     * Remove {@code Ocean} to the player.
     * 
     * @param ocean the ocean to remove
     */
    void removePlayerOcean(Ocean ocean);

    /**
     * Get a Set of {@code Ocean} owned by the player
     * 
     * @return set of player's oceans
     */
    Set<Ocean> getOwnedOceans();

    /**
     * Set the groundDino amount to add in each turn.
     * 
     * @param amount quantity of GroundDino to add each turn
     */
    void setBonusGroundDino(int amount);

    /**
     * Set the waterDino amount to add in each turn.
     * 
     * @param amount quantity of WaterDino to add each turn
     */
    void setBonusWaterDino(int amount);

    /**
     * Get the groundDino amount to add in each turn.
     * 
     * @return the player Ground Dino amount to add each turn
     */
    int getBonusGroundDino();

    /**
     * Get the waterDino amount to add in each turn.
     * 
     * @return the player Water Dino amount to add each turn
     */
    int getWaterDino();

    // TODO:
    // Set<Continent> getOwnedContinents();

}
