package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.reader.api.JSONFileReader;

/**
 * Implementation of JSONFileReader to parse the JSON file containing the
 * coordinates of the sprites for the oceans, calculated as a
 * percentage of the board panel dimension.
 */
public class OceanSpritePositionReader implements JSONFileReader<Map<String, Pair<Double, Double>>> {

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
    public Map<String, Pair<Double, Double>> readFileData(final String filePath) {
        final Map<String, Pair<Double, Double>> data = new HashMap<>();

        try (InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath))) {
            final JsonNode jsonNode = this.mapper.readTree(in);
            jsonNode.forEach(t -> {
                final String oceanName = t.get("ocean").asText();
                final double x = t.get("x").asDouble();
                final double y = t.get("y").asDouble();
                data.put(oceanName, new Pair<>(x, y));
            });
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read " + filePath + " file", e);
        }

        return Map.copyOf(data);
    }

}
