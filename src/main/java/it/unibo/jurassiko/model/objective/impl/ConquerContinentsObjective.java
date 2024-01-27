package it.unibo.jurassiko.model.objective.impl;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implementation of objectives based on conquest of continents.
 */
public class ConquerContinentsObjective extends AbstractObjective {

    private static final String TYPE = "conquerContinents";

    @JsonProperty("value")
    private Set<String> continents;
    @JsonProperty("selectable")
    private boolean selectableContinent;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @return a copy of the continents to conquer
     */
    public Set<String> getContinents() {
        return Set.copyOf(continents);
    }

    /**
     * {@inheritDoc}
     */
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
