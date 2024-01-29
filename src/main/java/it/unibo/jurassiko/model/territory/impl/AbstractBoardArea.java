package it.unibo.jurassiko.model.territory.impl;

import java.util.HashSet;
import java.util.Objects;
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

    @JsonProperty("name")
    private String name;
    @JsonProperty("neighbours")
    private Set<String> neighbourNames;

    @JsonIgnore
    private Set<T> neighbours;

    /**
     * Blank constructor used by Jackson JSON parser.
     */
    protected AbstractBoardArea() {
        this.neighbours = new HashSet<>();
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
    public void setNeighbours(final Set<T> neighbours) {
        Objects.requireNonNull(neighbours);
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
    public boolean isNeighbour(final String name) {
        Objects.requireNonNull(neighbours);
        return neighbours.stream()
                .map(BoardArea::getName)
                .anyMatch(name::equals);
    }

}
