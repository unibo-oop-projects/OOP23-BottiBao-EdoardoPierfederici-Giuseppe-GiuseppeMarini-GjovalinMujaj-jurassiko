package it.unibo.jurassiko.model.objective.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConquerTerritoriesObjective extends AbstractObjective {

    private static final String TYPE = "conquerTerritories";

    @JsonProperty("value")
    private int numTerritories;
    private int minDinos;

    @Override
    public String getType() {
        return TYPE;
    }

    public int getNumTerritories() {
        return numTerritories;
    }

    public int getMinDinos() {
        return minDinos;
    }

    @Override
    public void writeDescription() {
        String result = "Conquista " + this.numTerritories + " territori";
        if (this.minDinos > 1) {
            result = result.concat(" con almeno " + this.minDinos + " ciascuno");
        }
        result = result.concat(".");

        this.description = result;
    }

}
