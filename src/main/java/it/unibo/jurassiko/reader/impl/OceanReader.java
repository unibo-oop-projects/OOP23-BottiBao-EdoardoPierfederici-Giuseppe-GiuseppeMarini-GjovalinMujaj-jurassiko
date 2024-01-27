package it.unibo.jurassiko.reader.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

// TODO: complete defineAdjTerritories if needed

/**
 * Implementation of JSONFileReader for oceans.
 */
public class OceanReader extends AbstractJSONFileReader<Ocean> {

    public OceanReader() {
        super(Ocean.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildAttributes(final Set<Ocean> oceans) {
        defineNeighbours(oceans);
        // defineAdjTerritories(oceans);
    }

    /**
     * Processes and sets the bordering oceans.
     * 
     * @param oceans the set containing the oceans read by the parser
     */
    private void defineNeighbours(final Set<Ocean> oceans) {
        oceans.forEach(o -> {
            final Set<Ocean> neighbours = o.getNeighbourNames().stream()
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

    /**
     * Returns the ocean instance with the specified name.
     * 
     * @param oceanName the name of the ocean
     * @param oceans    the set containing the oceans read by the parser
     * @return the ocean istance
     */
    private Ocean getOceanByName(final String oceanName, final Set<Ocean> oceans) {
        for (final var ocean : oceans) {
            if (ocean.getName().equals(oceanName)) {
                return ocean;
            }
        }
        throw new IllegalArgumentException("Ocean \"" + oceanName + "\" not found");
    }

    /**
     * Returns the territory instance with the specified name.
     * 
     * @param territoryName the name of the territory
     * @param territories   the set containing the territories read by the parser
     * @return the territory istance
     */
    private Territory getTerritoryByName(final String territoryName, final Set<Territory> territories) {
        for (final var territory : territories) {
            if (territory.getName().equals(territoryName)) {
                return territory;
            }
        }
        throw new IllegalArgumentException("Territory \"" + territoryName + "\" not found");
    }

}
