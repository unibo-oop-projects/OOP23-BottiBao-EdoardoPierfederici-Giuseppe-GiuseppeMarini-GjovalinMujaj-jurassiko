package it.unibo.jurassiko.model.objective.impl;

public class ConquerTerritoriesObjective extends AbstractObjective {

    private final String type = "conquerTerritories";

    private int numTerritories;
    private int minDinos;

    public String getType() {
        return type;
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
