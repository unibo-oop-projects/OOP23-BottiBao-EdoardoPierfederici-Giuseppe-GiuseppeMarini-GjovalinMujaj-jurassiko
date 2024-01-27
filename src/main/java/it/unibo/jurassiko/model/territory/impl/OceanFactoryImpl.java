package it.unibo.jurassiko.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.OceanFactory;
import it.unibo.jurassiko.reader.api.JSONFileReader;
import it.unibo.jurassiko.reader.impl.OceanReader;

/**
 * Implementation of the factory for the game oceans.
 */
public class OceanFactoryImpl implements OceanFactory {

    private static final String PATH = "oceans.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ocean> createOceans() {
        final JSONFileReader<Ocean> oceanReader = new OceanReader();
        Set<Ocean> oceans = oceanReader.readFileData(PATH);
        return new HashSet<>(oceans);
    }

}
