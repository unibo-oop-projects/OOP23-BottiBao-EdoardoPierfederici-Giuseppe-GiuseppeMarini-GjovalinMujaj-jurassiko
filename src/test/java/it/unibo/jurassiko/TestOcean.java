package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.OceanFactory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;

class TestOcean {

    private static final int NUM_OCEANS = 3;

    private OceanFactory oceanFactory;
    private Set<Ocean> oceans;

    @BeforeEach
    public void initFactory() {
        this.oceanFactory = new OceanFactoryImpl();
        this.oceans = this.oceanFactory.createOceans();
    }

    @Test
    public void testOceanReader() {
        assertNotNull(oceans);
        assertFalse(oceans.isEmpty());

        assertEquals(NUM_OCEANS, oceans.size());
    }

    @Test
    public void testOceanAttributes() {
        final String name = "Oceano Tetide";
        final var neighbours = Set.of("Oceano Pacifico", "Oceano Atlantico");

        assertTrue(oceans.stream().anyMatch(o -> o.getName().equals(name)));

        final Ocean sampleOcean = oceans.stream().filter(t -> t.getName().equals(name)).findAny().get();
        assertEquals(neighbours, sampleOcean.getNeighbourNames());
    }

    // TODO: add missing tests

}
