package it.unibo.jurassiko.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.jurassiko.model.territory.api.BoardArea;

public abstract class AbstractBoardArea<T extends BoardArea<T>> implements BoardArea<T> {

    private String name;
    @JsonProperty("neighbours")
    private Set<String> neighbourNames;

    @JsonIgnore
    private Set<T> neighbours;

    protected AbstractBoardArea() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getNeighbourNames() {
        return Set.copyOf(neighbourNames);
    }

    @Override
    public void setNeighbours(Set<T> neighbours) {
        this.neighbours = new HashSet<>(neighbours);
    }

    @Override
    public Set<T> getNeighbours() {
        return Set.copyOf(neighbours);
    }

    @Override
    public boolean isNeighbour(String name) {
        return neighbours.stream()
                .map(BoardArea::getName)
                .anyMatch(name::equals);
    }

}
