package it.unibo.jurassiko.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.jurassiko.model.territory.api.BoardArea;

/**
 * Abstract class implementing common functionality for both Territory and
 * Ocean.
 * 
 * @param <T> The type of the specific area (Territory or Ocean)
 */
public abstract class AbstractBoardArea<T extends BoardArea<T>> implements BoardArea<T> {

    private String name;
    @JsonProperty("neighbours")
    private Set<String> neighbourNames;

    @JsonIgnore
    private Set<T> neighbours;

    protected AbstractBoardArea() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getNeighbourNames() {
        return Set.copyOf(neighbourNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNeighbours(Set<T> neighbours) {
        this.neighbours = new HashSet<>(neighbours);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<T> getNeighbours() {
        return Set.copyOf(neighbours);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNeighbour(String name) {
        return neighbours.stream()
                .map(BoardArea::getName)
                .anyMatch(name::equals);
    }

}
