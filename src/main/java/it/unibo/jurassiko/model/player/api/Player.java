package it.unibo.jurassiko.model.player.api;

import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Interface for the Player.
 */
public interface Player {
    /**
     * Enum for the colors.
     */
    enum GameColor {
        /**
         * Default color represented by Black.
         */
        DEFAULT("Black", "000000"),
        /**
         * Color Red.
         */
        RED("Red", "#ff0000"),
        /**
         * Color Green.
         */
        GREEN("Green", "#00ff00"),
        /**
         * Color Blue.
         */
        BLUE("Blue", "#0000ff");

        private final String color;
        private final String hexString;

        /**
         * Constructor for the Colors.
         * 
         * @param color   color name
         * @param hexCode HexCode of the color
         */
        GameColor(final String color, final String hexCode) {
            this.color = color;
            this.hexString = hexCode;
        }

        /**
         * Get the color name.
         * 
         * @return color name
         */
        public String getColorName() {
            return color;
        }

        /**
         * Get the HexCode.
         * 
         * @return HexCode
         */
        public String getHexCode() {
            return this.hexString;
        }
    }

    /**
     * Get the player color.
     * 
     * @return player color
     */
    GameColor getColor();

    /**
     * Get the player Objective.
     * 
     * @return player objective
     */
    Objective getObjective();

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
    int getBonusWaterDino();

    /**
     * Get a copy of the player.
     * 
     * @return the copy of the player
     * @throws CloneNotSupportedException
     */
    Player getPlayer() throws CloneNotSupportedException;

}
