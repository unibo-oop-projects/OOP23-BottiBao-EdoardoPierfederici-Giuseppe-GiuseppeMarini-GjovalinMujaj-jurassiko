package it.unibo.jurassiko.reader.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Implementation of JSONFileReader for oceans.
 */
public class OceanReader extends AbstractBoardDataReader<Ocean> {

    /**
     * Creates an OceanReader.
     */
    public OceanReader() {
        super(Ocean.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildAttributes(final Set<Ocean> oceans) {
        defineNeighbours(oceans);
        defineAdjTerritories(oceans);
    }

    /**
     * Processes and sets the adjacent territories.
     * 
     * @param oceans the set containing the oceans read by the parser
     */
    private void defineAdjTerritories(final Set<Ocean> oceans) {
        final var allTerritories = new TerritoryFactoryImpl().createTerritories();

        oceans.forEach(o -> {
            final Set<Territory> adjTerritories = o.getAdjTerritoryNames().stream()
                    .map(tn -> getBoardAreaByName(tn, allTerritories))
                    .collect(Collectors.toSet());
            o.setAdjTerritories(adjTerritories);
        });
    }

}
