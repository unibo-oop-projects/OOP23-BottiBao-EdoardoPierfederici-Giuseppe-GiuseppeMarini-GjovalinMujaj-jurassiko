package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.ocean.api.Ocean;
import it.unibo.jurassiko.model.ocean.api.OceanFactory;
import it.unibo.jurassiko.model.ocean.impl.OceanFactoryImpl;

public class TestOcean {

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

    public void testOceanAttributes() {

    }

    // TODO: add missing tests

}
