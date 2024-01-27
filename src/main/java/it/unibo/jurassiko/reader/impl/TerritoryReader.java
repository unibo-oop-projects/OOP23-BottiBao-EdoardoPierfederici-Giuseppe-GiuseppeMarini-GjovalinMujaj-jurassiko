package it.unibo.jurassiko.reader.impl;

import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Processes and sets the bordering territories.
     * 
     * @param territories the set containing the territories read by the parser
     */
    private void defineNeighbours(final Set<Territory> territories) {
        territories.forEach(t -> {
            final Set<Territory> neighbours = t.getNeighbourNames().stream()
                    .map(tn -> getTerritoryByName(tn, territories))
                    .collect(Collectors.toSet());
            t.setNeighbours(neighbours);
        });
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
