package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.reader.api.JSONFileReader;

/**
 * Implementation of JSONFileReader to parse the JSON file containing the
 * coordinates of the sprites for the oceans, calculated as a
 * percentage of the board panel dimension.
 */
public class OceanSpritePositionReader implements JSONFileReader<Map<String, Map<GameColor, Pair<Double, Double>>>> {

    private final ObjectMapper mapper;

    /**
     * Constructor to initialize the mapper.
     */
    public OceanSpritePositionReader() {
        this.mapper = new ObjectMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<GameColor, Pair<Double, Double>>> readFileData(final String filePath) {
        final Map<String, Map<GameColor, Pair<Double, Double>>> data = new HashMap<>();

        try (InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath))) {
            final JsonNode jsonNode = this.mapper.readTree(in);
            jsonNode.forEach(t -> {
                final String oceanName = t.get("ocean").asText();
                final double redX = t.get("RED").get("x").asDouble();
                final double redY = t.get("RED").get("y").asDouble();
                final double blueX = t.get("BLUE").get("x").asDouble();
                final double blueY = t.get("BLUE").get("y").asDouble();
                final double greenX = t.get("GREEN").get("x").asDouble();
                final double greenY = t.get("GREEN").get("y").asDouble();

                final var redPosition = new Pair<>(redX, redY);
                final var bluePosition = new Pair<>(blueX, blueY);
                final var greenPosition = new Pair<>(greenX, greenY);

                final Map<GameColor, Pair<Double, Double>> colorPositions = new HashMap<>();
                colorPositions.put(GameColor.RED, redPosition);
                colorPositions.put(GameColor.BLUE, bluePosition);
                colorPositions.put(GameColor.GREEN, greenPosition);

                data.put(oceanName, colorPositions);
            });
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read " + filePath + " file", e);
        }

        return Map.copyOf(data);
    }

}
