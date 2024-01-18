package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.territory.api.Territory;

public class TestTerritory {

    private static final int NUM_TOTAL_TERRITORIES = 0;
    // num territories per continent

    // private TerritoryFactory territoryFactory;
    private Set<Territory> territories;

    @BeforeEach
    public void setUp() {
        // this.territoryFactory = new TerritoryFactoryImpl();
        // this.territories = this.territoryFactory.createTerritories();
    }

    @Test
    public void testTerritoryReader() {
        assertNotNull(territories);
        assertFalse(territories.isEmpty());
        assertEquals(NUM_TOTAL_TERRITORIES, territories.size());
    }

}
