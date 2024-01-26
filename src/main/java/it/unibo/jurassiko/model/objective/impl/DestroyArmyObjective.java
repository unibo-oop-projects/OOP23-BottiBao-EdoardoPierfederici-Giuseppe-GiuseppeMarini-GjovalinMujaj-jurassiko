package it.unibo.jurassiko.model.objective.impl;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DestroyArmyObjective extends AbstractObjective {

    private static final String TYPE = "destroyArmy";
    @JsonProperty("value")
    private String armyColor;

    public String getType() {
        return TYPE;
    }

    public String getArmyColor() {
        return armyColor;
    }

    @Override
    public void writeDescription() {
        this.description = "Distruggi l'armata di colore "
                + this.armyColor.toLowerCase(Locale.ROOT)
                + ". Se l'armata non è in gioco, "
                + super.getDefaultObjectiveDescription().toLowerCase(Locale.ROOT);
    }

}
