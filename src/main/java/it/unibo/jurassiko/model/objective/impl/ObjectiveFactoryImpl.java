package it.unibo.jurassiko.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.api.ObjectiveFactory;
import it.unibo.jurassiko.reader.impl.ObjectiveReader;

/**
 * Implementation of {@link ObjectiveFactory}.
 */
public class ObjectiveFactoryImpl implements ObjectiveFactory {

    private static final String PATH = "config/objectives.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Objective> createObjectives() {
        final var objectiveReader = new ObjectiveReader();
        final Set<Objective> objectives = objectiveReader.readFileData(PATH);
        return new HashSet<>(objectives);
    }

}
