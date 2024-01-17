package it.unibo.jurassiko.model.objective.impl;

import java.util.Set;

public class ConquerContinentsObjective extends AbstractObjective {

    private final String type = "conquerContinents";
    private Set<String> continents;

    public String getType() {
        return type;
    }

    public Set<String> getContinents() {
        return continents;
    }

    @Override
    public void writeDescription() {
        String result = "Conquista i seguenti continenti: ";
        result = result.concat(String.join(", ", this.continents)).concat(".");
        this.description = result;
    }
}
