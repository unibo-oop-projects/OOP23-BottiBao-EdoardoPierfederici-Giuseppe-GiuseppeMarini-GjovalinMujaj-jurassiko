package it.unibo.jurassiko.reader.impl;

import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;

/**
 * Implementation of JSONFileReader for objectives.
 */
public class ObjectiveReader extends AbstractJSONFileReader<Objective> {

    /**
     * Creates an ObjectiveReader.
     */
    public ObjectiveReader() {
        super(Objective.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildAttributes(final Set<Objective> objectives) {
        writeDescriptions(objectives);
    }

    /**
     * Sets the description on each objective.
     * 
     * @param objectives the set containing the objectives
     */
    private void writeDescriptions(final Set<Objective> objectives) {
        for (final var objective : objectives) {
            objective.writeDescription();
        }
    }

}
