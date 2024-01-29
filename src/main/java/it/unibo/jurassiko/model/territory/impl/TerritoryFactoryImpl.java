package it.unibo.jurassiko.model.territory.impl;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.api.TerritoryFactory;
import it.unibo.jurassiko.reader.api.JSONFileReader;
import it.unibo.jurassiko.reader.impl.TerritoryReader;

/**
 * Implementation of the factory for the game territories.
 */
public class TerritoryFactoryImpl implements TerritoryFactory {

    private static final String PATH = "territories.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> createTerritories() {
        final JSONFileReader<Territory> territoryReader = new TerritoryReader();
        Set<Territory> territories = territoryReader.readFileData(PATH);
        return Set.copyOf(territories);
    }

}
