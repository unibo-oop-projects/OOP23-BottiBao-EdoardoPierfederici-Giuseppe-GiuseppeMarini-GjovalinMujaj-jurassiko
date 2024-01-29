package it.unibo.jurassiko.model.objective.impl;

import it.unibo.jurassiko.model.objective.api.Objective;

/**
 * Abstract class implementing common functionality for game objectives.
 */
public abstract class AbstractObjective implements Objective {

    private static final String DEFAULT_OBJECTIVE_DESCRIPTION = "Conquista 12 territori.";

    private String description = "";
    private final boolean achieved = false;

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
     * Sets the description of the objective.
     * 
     * @param description the objective description
     */
    protected void setDescription(final String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void writeDescription();

    /**
     * @return the description of the default objective
     */
    protected String getDefaultObjectiveDescription() {
        return DEFAULT_OBJECTIVE_DESCRIPTION;
    }

}
