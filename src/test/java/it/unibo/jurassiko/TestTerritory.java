package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

class TestTerritory {

    private static final int NUM_TOTAL_TERRITORIES = 21;
    private static final int NUM_CONTINENTS = 4;
    private static final int NUM_TERRITORIES_NORDAMERICA = 4;
    private static final int NUM_TERRITORIES_GONDWANAOCCIDENTALE = 6;
    private static final int NUM_TERRITORIES_GONDWANAORIENTALE = 4;
    private static final int NUM_TERRITORIES_EURASIA = 7;

    private Set<Territory> territories;

    @BeforeEach
    void init() {
        this.territories = new TerritoryFactoryImpl().createTerritories();
    }

    @Test
    void testTerritoryReader() {
        assertNotNull(territories);
        assertFalse(territories.isEmpty());

        assertEquals(NUM_TOTAL_TERRITORIES, territories.size());
        assertEquals(NUM_TOTAL_TERRITORIES, territories.stream().map(Territory::getName).distinct().count());
        assertEquals(NUM_CONTINENTS,
                territories.stream().map(Territory::getContinent).distinct().count());
        assertEquals(NUM_TERRITORIES_NORDAMERICA,
                territories.stream().filter(t -> "Nord America".equals(t.getContinent())).count());
        assertEquals(NUM_TERRITORIES_GONDWANAOCCIDENTALE,
                territories.stream().filter(t -> "Gondwana Occidentale".equals(t.getContinent())).count());
        assertEquals(NUM_TERRITORIES_GONDWANAORIENTALE,
                territories.stream().filter(t -> "Gondwana Orientale".equals(t.getContinent())).count());
        assertEquals(NUM_TERRITORIES_EURASIA,
                territories.stream().filter(t -> "Eurasia".equals(t.getContinent())).count());
    }

    @Test
    void testTerritoryAttributes() {
        final String name = "Nord Africa";
        final String continent = "Gondwana Occidentale";
        final var neighbourNames = Set.of("Arabia", "Amazzonia", "Congo", "Sud America");
        final var neighbours = territories.stream()
                .filter(t -> neighbourNames.contains(t.getName()))
                .collect(Collectors.toSet());

        assertTrue(territories.stream().anyMatch(t -> t.getName().equals(name)));

        final Territory sampleTerritory = territories.stream().filter(t -> t.getName().equals(name)).findAny().get();
        assertEquals(continent, sampleTerritory.getContinent());
        assertEquals(neighbourNames, sampleTerritory.getNeighbourNames());
        assertEquals(neighbours, sampleTerritory.getNeighbours());
    }

}
