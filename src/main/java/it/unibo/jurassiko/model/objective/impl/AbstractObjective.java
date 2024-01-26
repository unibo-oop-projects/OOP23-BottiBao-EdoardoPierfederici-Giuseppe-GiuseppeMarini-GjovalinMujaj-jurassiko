package it.unibo.jurassiko.model.objective.impl;

import it.unibo.jurassiko.model.objective.api.Objective;

public abstract class AbstractObjective implements Objective {

    private final static String DEFAULT_OBJECTIVE_DESCRIPTION = "Conquista 12 territori.";

    protected String description = "";
    private boolean achieved = false;

    @Override
    public boolean isAchieved() {
        return achieved;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public abstract void writeDescription();

    protected String getDefaultObjectiveDescription() {
        return DEFAULT_OBJECTIVE_DESCRIPTION;
    }

}
