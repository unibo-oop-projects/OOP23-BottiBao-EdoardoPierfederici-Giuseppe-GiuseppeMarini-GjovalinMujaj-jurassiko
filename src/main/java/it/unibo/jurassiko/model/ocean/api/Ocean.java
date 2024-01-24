package it.unibo.jurassiko.model.ocean.api;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Territory;

public interface Ocean {

    String getName();

    Set<String> getNeighbourNames();

    Set<String> getAdjTerritoryNames();

    void setNeighbours(Set<Ocean> neighbours);

    void setAdjTerritories(Set<Territory> adjTerritories);

    Set<Ocean> getNeighbours();

    Set<Territory> getAdjTerritories();

    boolean isNeighbour(String oceanName);

    boolean isAdjTerritory(String territoryName);

    // changeDinoAmount()

    // getDinoAmount()

}
