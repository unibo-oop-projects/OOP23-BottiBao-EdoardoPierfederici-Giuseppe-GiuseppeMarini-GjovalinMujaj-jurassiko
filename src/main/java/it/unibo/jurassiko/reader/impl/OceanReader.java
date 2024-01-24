package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.model.ocean.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.reader.api.JSONFileReader;

// TODO: complete defineAdjTerritories if needed
public class OceanReader implements JSONFileReader<Ocean> {

    final Logger logger = LoggerFactory.getLogger(OceanReader.class);
    private final ObjectMapper mapper;

    public OceanReader() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Set<Ocean> readFileData(String path) {
        final InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(path));
        Set<Ocean> oceans = new HashSet<>();

        try {
            oceans = mapper.readValue(in,
                    new TypeReference<Set<Ocean>>() {
                    });
            defineNeighbours(oceans);
            // defineAdjTerritories(oceans);
        } catch (final IOException e) {
            this.logger.error("Failed to read territories file", e);
        }

        return oceans;
    }

    private void defineNeighbours(Set<Ocean> oceans) {
        oceans.forEach(o -> {
            Set<Ocean> neighbours = o.getNeighbourNames().stream()
                    .map(on -> getOceanByName(on, oceans))
                    .collect(Collectors.toSet());
            o.setNeighbours(neighbours);
        });
    }

    /*
     * private void defineAdjTerritories(Set<Ocean> oceans) {
     * oceans.forEach(o -> {
     * Set<Territory> adjTerritories = o.getAdjTerritoryNames().stream()
     * .map(tn -> getTerritoryByName(tn, territories))
     * .collect(Collectors.toSet());
     * o.setAdjTerritories(adjTerritories);
     * });
     * }
     */

    private Ocean getOceanByName(final String oceanName, Set<Ocean> oceans) {
        for (final var ocean : oceans) {
            if (ocean.getName().equals(oceanName)) {
                return ocean;
            }
        }
        throw new IllegalArgumentException("Ocean \"" + oceanName + "\" not found");
    }

    private Territory getTerritoryByName(final String territoryName, Set<Territory> territories) {
        for (final var territory : territories) {
            if (territory.getName().equals(territoryName)) {
                return territory;
            }
        }
        throw new IllegalArgumentException("Territory \"" + territoryName + "\" not found");
    }

}
