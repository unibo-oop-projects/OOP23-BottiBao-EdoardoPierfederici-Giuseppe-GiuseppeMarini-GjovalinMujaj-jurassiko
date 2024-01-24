package it.unibo.jurassiko.model.ocean.api;

import java.util.Set;

public interface Ocean {

    String getName();

    Set<String> getNeighbourNames();

    void setNeighbours(Set<Ocean> neighbours);

    Set<Ocean> getNeighbours();

    boolean isNeighbour(String oceanName);

    // changeDinoAmount()

    // getDinoAmount()

}
