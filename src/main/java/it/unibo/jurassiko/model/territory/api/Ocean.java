package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.jurassiko.model.territory.impl.OceanImpl;

/**
 * Interface that represents an Ocean, a special type of game territory.
 */
@JsonDeserialize(as = OceanImpl.class)
public interface Ocean extends BoardArea<Ocean> {

    /**
     * Returns the names of the adjacent territories.
     * 
     * @return a copy of the set with the names of the adjacent territories
     */
    Set<String> getAdjTerritoryNames();

    /**
     * Sets the adjacent territories of the ocean.
     * 
     * @param adjTerritories a set containing the adjacent territories
     */
    void setAdjTerritories(Set<Territory> adjTerritories);

    /**
     * Return the instance of the adjacent territories.
     * 
     * @return a copy of the set containing the adjacent territories
     */
    Set<Territory> getAdjTerritories();

    /**
     * Checks if the territory with the given name is adjacent.
     * 
     * @param territoryName the name of the territory to check
     * @return true if the territory is adjacent, false otherwise
     */
    boolean isAdjTerritory(String territoryName);

    // changeDinoAmount()

    // getDinoAmount()

}
