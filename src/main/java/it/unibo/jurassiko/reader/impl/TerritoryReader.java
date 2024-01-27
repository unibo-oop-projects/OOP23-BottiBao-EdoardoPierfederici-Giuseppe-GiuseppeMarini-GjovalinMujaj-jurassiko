package it.unibo.jurassiko.reader.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jurassiko.model.territory.api.Territory;

public class TerritoryReader extends AbstractJSONFileReader<Territory> {

    public TerritoryReader() {
        super(Territory.class);
    }

    @Override
    protected void buildAttributes(final Set<Territory> data) {
        defineNeighbours(data);
    }

    private void defineNeighbours(final Set<Territory> territories) {
        territories.forEach(t -> {
            final Set<Territory> neighbours = t.getNeighbourNames().stream()
                    .map(tn -> getTerritoryByName(tn, territories))
                    .collect(Collectors.toSet());
            t.setNeighbours(neighbours);
        });
    }

    private Territory getTerritoryByName(final String territoryName, final Set<Territory> territories) {
        for (final var territory : territories) {
            if (territory.getName().equals(territoryName)) {
                return territory;
            }
        }
        throw new IllegalArgumentException("Territory \"" + territoryName + "\" not found");
    }

}
