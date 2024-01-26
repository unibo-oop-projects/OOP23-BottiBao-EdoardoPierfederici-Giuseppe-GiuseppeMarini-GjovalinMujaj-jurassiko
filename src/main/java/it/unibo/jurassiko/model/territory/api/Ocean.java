package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.jurassiko.model.territory.impl.OceanImpl;

@JsonDeserialize(as = OceanImpl.class)
public interface Ocean extends BoardArea<Ocean> {

    Set<String> getAdjTerritoryNames();

    void setAdjTerritories(Set<Territory> adjTerritories);

    Set<Territory> getAdjTerritories();

    boolean isAdjTerritory(String territoryName);

    // changeDinoAmount()

    // getDinoAmount()

}
