package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;

class TestOcean {

    private static final int NUM_OCEANS = 3;

    private Set<Ocean> oceans;

    @BeforeEach
    void initFactory() {
        this.oceans = new OceanFactoryImpl().createOceans();
    }

    @Test
    void testOceanReader() {
        assertNotNull(oceans);
        assertFalse(oceans.isEmpty());

        assertEquals(NUM_OCEANS, oceans.size());
    }

    @Test
    void testOceanAttributes() {
        final String name = "Oceano Tetide";
        final var neighbours = Set.of("Oceano Pacifico", "Oceano Atlantico");
        // TODO: fix adjTerritories if changes are made to the JSON file
        final var adjTerritoryNames = Set.of("Groenlandia", "Arabia", "Baltica", "Asia Centrale", "Cina");

        // TODO: uncomment if adjTerritory instance will be needed
        /*
         * final var adjTerritories = new
         * TerritoryFactoryImpl().createTerritories().stream()
         * .filter(t -> adjTerritoryNames.contains(t.getName()))
         * .collect(Collectors.toSet());
         */

        assertTrue(oceans.stream().anyMatch(o -> o.getName().equals(name)));

        final Ocean sampleOcean = oceans.stream().filter(t -> t.getName().equals(name)).findAny().get();
        assertEquals(neighbours, sampleOcean.getNeighbourNames());
        assertEquals(adjTerritoryNames, sampleOcean.getAdjTerritoryNames());
    }

    // TODO: add missing tests

}
