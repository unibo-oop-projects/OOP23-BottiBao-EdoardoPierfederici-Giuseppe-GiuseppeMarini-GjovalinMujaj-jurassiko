package it.unibo.jurassiko.reader.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

// TODO: complete defineAdjTerritories if needed
public class OceanReader extends AbstractJSONFileReader<Ocean> {

    public OceanReader() {
        super(Ocean.class);
    }

    @Override
    protected void buildAttributes(Set<Ocean> oceans) {
        defineNeighbours(oceans);
        // defineAdjTerritories(oceans);
    }

    private void defineNeighbours(Set<Ocean> oceans) {
        oceans.forEach(o -> {
            Set<Ocean> neighbours = o.getNeighbourNames().stream()
                    .map(on -> getOceanByName(on, oceans))
                    .collect(Collectors.toSet());
            o.setNeighbours(neighbours);
        });
    }

    /*
     * private void defineAdjTerritories(Set<Ocean> oceans) {
     * oceans.forEach(o -> {
     * Set<Territory> adjTerritories = o.getAdjTerritoryNames().stream()
     * .map(tn -> getTerritoryByName(tn, territories))
     * .collect(Collectors.toSet());
     * o.setAdjTerritories(adjTerritories);
     * });
     * }
     */

    private Ocean getOceanByName(final String oceanName, Set<Ocean> oceans) {
        for (final var ocean : oceans) {
            if (ocean.getName().equals(oceanName)) {
                return ocean;
            }
        }
        throw new IllegalArgumentException("Ocean \"" + oceanName + "\" not found");
    }

    private Territory getTerritoryByName(final String territoryName, Set<Territory> territories) {
        for (final var territory : territories) {
            if (territory.getName().equals(territoryName)) {
                return territory;
            }
        }
        throw new IllegalArgumentException("Territory \"" + territoryName + "\" not found");
    }

}
