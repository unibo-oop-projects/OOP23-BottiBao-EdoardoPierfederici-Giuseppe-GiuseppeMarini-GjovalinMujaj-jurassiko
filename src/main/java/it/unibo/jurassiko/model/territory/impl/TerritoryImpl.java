package it.unibo.jurassiko.model.territory.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unibo.jurassiko.model.territory.api.Territory;

public class TerritoryImpl extends AbstractBoardArea<Territory> implements Territory {

    private String continent;

    @JsonIgnore
    private int dinoAmount = 0;

    private TerritoryImpl() {

    }

    @Override
    public String getContinent() {
        return continent;
    }

    @Override
    public void changeDinoAmount(final int delta) {
        this.dinoAmount += delta;
    }

    @Override
    public int getDinoAmount() {
        return dinoAmount;
    }

}
