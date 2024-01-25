package it.unibo.jurassiko.model;

import java.util.Set;

public interface BoardArea<T extends BoardArea<T>> {

    String getName();

    Set<String> getNeighbourNames();

    void setNeighbours(Set<T> neighbours);

    Set<T> getNeighbours();

    boolean isNeighbour(String name);

}