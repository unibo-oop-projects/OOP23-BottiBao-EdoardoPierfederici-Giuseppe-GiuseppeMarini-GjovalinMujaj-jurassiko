package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.jurassiko.model.territory.impl.TerritoryImpl;

@JsonDeserialize(as = TerritoryImpl.class)
public interface Territory {

    String getName();

    String getContinent();

    // Player getOwner();

    Set<String> getNeighbourNames();

    void setNeighbours(Set<Territory> neighbours);

    Set<Territory> getNeighbours();

    boolean isNeighbour(String territoryName);

    void changeDinoAmount(int delta);

    int getDinoAmount();

}
