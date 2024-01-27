package it.unibo.jurassiko.model.player.api;

import java.util.Set;

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
         * @return color name
         */
        public String getColor() {
            return color;
        }
    }

    /**
     * @return player color
     */
    Color getPlayerColor();

    /**
     * @param objective player objective
     */
    void setPlayerObjective(Objective objective);

    /**
     * @return player objective
     */
    Objective getObjective();

    /**
     * @return {@code true} if the objective is completed, {@code false} if otherwise
     */
    boolean isObjectiveComplete();

    /**
     * @param territorie the territory to add
     */
    void addPlayerTerritory(Territory territorie);

    /**
     * @param territorie the territory to remove
     */
    void removePlayerTerritory(Territory territorie);

    /**
     * @return set of player's territories
     */
    Set<Territory> getOwnedTerritories();

    //Set<Continent> getOwnedContinents();

    // ??? getBonusHand();

}
