package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

/**
 * Interface that represents a generic area of the board.
 * 
 * @param <T> The type of the specific area (Territory or Ocean)
 */
public interface BoardArea<T extends BoardArea<T>> {

    /**
     * @return the name of the area
     */
    String getName();

    /**
     * @return the name of the neighbouring areas
     */
    Set<String> getNeighbourNames();

    /**
     * Sets the neighbours on the specific board area.
     * 
     * @param neighbours
     */
    void setNeighbours(Set<T> neighbours);

    /**
     * @return the instance of the neighbours
     */
    Set<T> getNeighbours();

    /**
     * Checks if the given area is a neighbour.
     * 
     * @param name the name of the area to check
     * @return true if the area is a neighbour, false otherwise
     */
    boolean isNeighbour(String name);

}