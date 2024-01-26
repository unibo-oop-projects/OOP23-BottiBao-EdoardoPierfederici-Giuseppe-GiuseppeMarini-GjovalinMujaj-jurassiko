package it.unibo.jurassiko.model.objective.impl;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConquerContinentsObjective extends AbstractObjective {

    private static final String type = "conquerContinents";
    @JsonProperty("value")
    private Set<String> continents;
    @JsonProperty("selectable")
    private boolean selectableContinent;

    public String getType() {
        return type;
    }

    public Set<String> getContinents() {
        return Set.copyOf(continents);
    }

    @Override
    public void writeDescription() {
        // TODO: semplificare con uno StringBuilder
        String result = "Conquista la totalità dei seguenti continenti: ";
        result = result.concat(String.join(", ", this.continents));
        if (this.selectableContinent) {
            result = result.concat("e un continente a scelta");
        }
        result = result.concat(".");
        this.description = result;
    }

}
