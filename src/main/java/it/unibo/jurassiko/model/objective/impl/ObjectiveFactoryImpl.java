package it.unibo.jurassiko.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.api.ObjectiveFactory;
import it.unibo.jurassiko.reader.api.JSONFileReader;
import it.unibo.jurassiko.reader.impl.ObjectiveReader;

public class ObjectiveFactoryImpl implements ObjectiveFactory {

    private static final String PATH = "objectives.json";

    @Override
    public Set<Objective> createObjectives() {
        final JSONFileReader<Objective> objectiveReader = new ObjectiveReader();
        Set<Objective> objectives = objectiveReader.readFileData(PATH);
        return new HashSet<>(objectives);
    }

}
