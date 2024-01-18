package it.unibo.jurassiko.model.territory.impl;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Territory;

public class TerritoryImpl implements Territory {

    private String name;
    private String continent;
    private Set<String> neighbourNames;

    private Set<Territory> neighbours;
    private int dinoAmount = 0;

    private TerritoryImpl() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContinent() {
        return continent;
    }

    @Override
    public Set<String> getNeighbourNames() {
        return neighbourNames;
    }

    @Override
    public void setNeighbours(Set<Territory> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public Set<Territory> getNeighbours() {
        return neighbours;
    }

    @Override
    public void changeDinoAmount(int delta) {
        this.dinoAmount += delta;
    }

    @Override
    public int getDinoAmount() {
        return dinoAmount;
    }

}
