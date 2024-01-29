package it.unibo.jurassiko.reader.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.api.TerritoryFactory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

// TODO: complete defineAdjTerritories if needed

/**
 * Implementation of JSONFileReader for oceans.
 */
public class OceanReader extends AbstractJSONFileReader<Ocean> {

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

    private void defineAdjTerritories(Set<Ocean> oceans) {
        TerritoryFactory territoryFactory = new TerritoryFactoryImpl();
        var territories = territoryFactory.createTerritories();

        oceans.forEach(o -> {
            Set<Territory> adjTerritories = o.getAdjTerritoryNames().stream()
                    .map(tn -> getBoardAreaByName(tn, territories))
                    .collect(Collectors.toSet());
            o.setAdjTerritories(adjTerritories);
        });
    }

}
