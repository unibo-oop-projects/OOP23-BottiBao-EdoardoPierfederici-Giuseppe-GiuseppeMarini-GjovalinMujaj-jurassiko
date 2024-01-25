package it.unibo.jurassiko.model.territory.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.jurassiko.model.BoardArea;
import it.unibo.jurassiko.model.territory.impl.TerritoryImpl;

@JsonDeserialize(as = TerritoryImpl.class)
public interface Territory extends BoardArea<Territory> {

    String getContinent();

    // Player getOwner();

    void changeDinoAmount(int delta);

    int getDinoAmount();

}
