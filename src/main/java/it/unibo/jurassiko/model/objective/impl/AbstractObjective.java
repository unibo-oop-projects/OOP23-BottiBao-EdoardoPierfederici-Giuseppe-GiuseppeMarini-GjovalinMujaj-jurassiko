package it.unibo.jurassiko.model.objective.impl;

import it.unibo.jurassiko.model.objective.api.Objective;

/**
 * Abstract class implementing common functionality for game objectives.
 */
public abstract class AbstractObjective implements Objective {

    private static final String DEFAULT_OBJECTIVE_DESCRIPTION = "Conquista 12 territori.";

    protected String description = "";
    private boolean achieved = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAchieved() {
        return achieved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void writeDescription();

    /**
     * @return the description of the default objective
     */
    protected String getDefaultObjectiveDescription() {
        return DEFAULT_OBJECTIVE_DESCRIPTION;
    }

}
