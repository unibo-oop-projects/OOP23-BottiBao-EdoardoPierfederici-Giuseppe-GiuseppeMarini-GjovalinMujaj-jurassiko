package it.unibo.jurassiko.model.ocean.api;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.jurassiko.model.ocean.impl.OceanImpl;
import it.unibo.jurassiko.model.territory.api.Territory;

@JsonDeserialize(as = OceanImpl.class)
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
