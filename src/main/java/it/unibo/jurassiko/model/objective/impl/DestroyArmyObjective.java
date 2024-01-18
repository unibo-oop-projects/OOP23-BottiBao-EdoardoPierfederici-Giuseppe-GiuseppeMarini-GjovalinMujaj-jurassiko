package it.unibo.jurassiko.model.objective.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DestroyArmyObjective extends AbstractObjective {

    private final String type = "destroyArmy";
    @JsonProperty("value")
    private String armyColor;

    public String getType() {
        return type;
    }

    public String getArmyColor() {
        return armyColor;
    }

    @Override
    public void writeDescription() {
        this.description = "Distruggi l'armata di colore " + this.armyColor.toLowerCase() +
                ". Se l'armata non è in gioco, "
                + super.getDefaultObjectiveDescription().toLowerCase();
    }
}
