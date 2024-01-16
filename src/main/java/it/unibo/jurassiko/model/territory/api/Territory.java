package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

public interface Territory {

    String getName();

    String getContinent();

    // Player getOwner();

    Set<String> getNeighbourNames();

    void setNeighbours(Set<Territory> neighbours);

    Set<Territory> getNeighbours();

    void changeDinoAmount(int delta);

    int getDinoAmount();

}
