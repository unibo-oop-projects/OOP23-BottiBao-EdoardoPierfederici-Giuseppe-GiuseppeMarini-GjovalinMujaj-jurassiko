package it.unibo.jurassiko.reader.impl;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Implementation of JSONFileReader for territories.
 */
public class TerritoryReader extends AbstractJSONFileReader<Territory> {

    /**
     * Creates a TerritoryReader.
     */
    public TerritoryReader() {
        super(Territory.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildAttributes(final Set<Territory> data) {
        defineNeighbours(data);
    }

}
