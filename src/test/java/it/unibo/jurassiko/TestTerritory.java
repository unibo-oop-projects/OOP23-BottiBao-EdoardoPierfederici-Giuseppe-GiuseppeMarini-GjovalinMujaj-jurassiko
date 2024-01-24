package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.api.TerritoryFactory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

public class TestTerritory {

    private static final int NUM_TOTAL_TERRITORIES = 21;
    private static final int NUM_CONTINENTS = 4;
    private static final int NUM_TERRITORIES_NORDAMERICA = 4;
    private static final int NUM_TERRITORIES_GONDWANAOCCIDENTALE = 6;
    private static final int NUM_TERRITORIES_GONDWANAORIENTALE = 4;
    private static final int NUM_TERRITORIES_EURASIA = 7;

    private TerritoryFactory territoryFactory;
    private Set<Territory> territories;

    @BeforeEach
    public void initFactory() {
        this.territoryFactory = new TerritoryFactoryImpl();
        this.territories = this.territoryFactory.createTerritories();
    }

    @Test
    public void testTerritoryReader() {
        assertNotNull(territories);
        assertFalse(territories.isEmpty());

        assertEquals(NUM_TOTAL_TERRITORIES, territories.size());
        assertEquals(NUM_TOTAL_TERRITORIES, territories.stream().map(Territory::getName).distinct().count());
        assertEquals(NUM_CONTINENTS,
                territories.stream().map(Territory::getContinent).distinct().count());
        assertEquals(NUM_TERRITORIES_NORDAMERICA,
                territories.stream().filter(t -> t.getContinent().equals("Nord America")).count());
        assertEquals(NUM_TERRITORIES_GONDWANAOCCIDENTALE,
                territories.stream().filter(t -> t.getContinent().equals("Gondwana Occidentale")).count());
        assertEquals(NUM_TERRITORIES_GONDWANAORIENTALE,
                territories.stream().filter(t -> t.getContinent().equals("Gondwana Orientale")).count());
        assertEquals(NUM_TERRITORIES_EURASIA,
                territories.stream().filter(t -> t.getContinent().equals("Eurasia")).count());
    }

    @Test
    public void testTerritoryAttributes() {
        final String name = "Nord Africa";
        final String continent = "Gondwana Occidentale";
        final var neighbours = Set.of("Arabia", "Amazzonia", "Congo", "Sud America");

        assertTrue(territories.stream().anyMatch(t -> t.getName().equals(name)));

        Territory sampleTerritory = territories.stream().filter(t -> t.getName().equals(name)).findAny().get();
        assertEquals(continent, sampleTerritory.getContinent());
        assertEquals(neighbours, sampleTerritory.getNeighbourNames());
    }

    // TODO: add missing tests

}
