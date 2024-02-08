package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.reader.impl.OceanSpritePositionReader;
import it.unibo.jurassiko.reader.impl.TerritorySpritePositionReader;

class TestSpritePositionReaders {

    private static final String TERRITORY_PATH = "spritepositions/territorypositions.json";
    private static final String OCEAN_PATH = "spritepositions/oceanpositions.json";

    private static final int NUM_TERRITORIES = 21;
    private static final int NUM_OCEANS = 3;
    private static final String SAMPLE_TERRITORY = "Sud America";
    private static final double TERRITORY_X = 27.0;
    private static final double TERRITORY_Y = 80.0;
    private static final String SAMPLE_OCEAN = "Oceano Tetide";
    private static final double OCEAN_RED_X = 40.0;
    private static final double OCEAN_RED_Y = 35.0;
    private static final double OCEAN_BLUE_X = 50.0;
    private static final double OCEAN_BLUE_Y = 20.0;
    private static final double OCEAN_GREEN_X = 60.0;
    private static final double OCEAN_GREEN_Y = 35.0;

    private Map<String, Pair<Double, Double>> territoryPositions;
    private Map<String, Map<GameColor, Pair<Double, Double>>> oceanPositions;
    // TODO: use territories/oceans actual sets to check files?

    @BeforeEach
    void init() {
        this.territoryPositions = new TerritorySpritePositionReader().readFileData(TERRITORY_PATH);
        this.oceanPositions = new OceanSpritePositionReader().readFileData(OCEAN_PATH);
    }

    @Test
    void testTerritoryPositionReader() {
        assertNotNull(territoryPositions);
        assertFalse(territoryPositions.isEmpty());
        assertEquals(NUM_TERRITORIES, territoryPositions.size());

        // Verify all coordinates are a valid percentage value
        assertTrue(territoryPositions.values().stream().allMatch(t -> isValid(t.x()) && isValid(t.y())));

        // Checks the coordinates of a sample territory
        assertTrue(territoryPositions.keySet().contains(SAMPLE_TERRITORY));
        final var coordinates = new Pair<>(TERRITORY_X, TERRITORY_Y);
        assertEquals(coordinates, territoryPositions.get(SAMPLE_TERRITORY));
    }

    @Test
    void testOceanPositionReader() {
        assertNotNull(oceanPositions);
        assertFalse(oceanPositions.isEmpty());
        assertEquals(NUM_OCEANS, oceanPositions.size());

        oceanPositions.values().stream()
                .flatMap(t -> t.values().stream())
                .allMatch(t -> isValid(t.x()) && isValid(t.y()));

        assertTrue(oceanPositions.keySet().contains(SAMPLE_OCEAN));
        final var redCoordinates = new Pair<>(OCEAN_RED_X, OCEAN_RED_Y);
        final var blueCoordinates = new Pair<>(OCEAN_BLUE_X, OCEAN_BLUE_Y);
        final var greenCoordinates = new Pair<>(OCEAN_GREEN_X, OCEAN_GREEN_Y);
        assertEquals(redCoordinates, oceanPositions.get(SAMPLE_OCEAN).get(GameColor.RED));
        assertEquals(blueCoordinates, oceanPositions.get(SAMPLE_OCEAN).get(GameColor.BLUE));
        assertEquals(greenCoordinates, oceanPositions.get(SAMPLE_OCEAN).get(GameColor.GREEN));
    }

    /**
     * Checks if the value is a valid percentage.
     * 
     * @param value the number to check
     * @return true is the value is between 0.0 and 100.0, false otherwise
     */
    private boolean isValid(final double value) {
        return value >= 0.0 && value <= 100.0;
    }

}
