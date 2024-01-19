package it.unibo.jurassiko.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.api.TerritoryFactory;
import it.unibo.jurassiko.reader.api.JSONFileReader;
import it.unibo.jurassiko.reader.impl.TerritoryReader;

public class TerritoryFactoryImpl implements TerritoryFactory {

    private static final String PATH = "territories.json";

    @Override
    public Set<Territory> createTerritories() {
        final JSONFileReader<Territory> territoryReader = new TerritoryReader();
        Set<Territory> territories = territoryReader.readFileData(PATH);
        return new HashSet<>(territories);
    }

}
