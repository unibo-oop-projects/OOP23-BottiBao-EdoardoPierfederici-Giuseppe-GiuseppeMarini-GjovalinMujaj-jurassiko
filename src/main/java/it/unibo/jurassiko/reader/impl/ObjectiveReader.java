package it.unibo.jurassiko.reader.impl;

import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;

public class ObjectiveReader extends AbstractJSONFileReader<Objective> {

    public ObjectiveReader() {
        super(Objective.class);
    }

    @Override
    protected void buildAttributes(final Set<Objective> objectives) {
        writeDescriptions(objectives);
    }

    private void writeDescriptions(final Set<Objective> objectives) {
        for (final var objective : objectives) {
            objective.writeDescription();
        }
    }

}
