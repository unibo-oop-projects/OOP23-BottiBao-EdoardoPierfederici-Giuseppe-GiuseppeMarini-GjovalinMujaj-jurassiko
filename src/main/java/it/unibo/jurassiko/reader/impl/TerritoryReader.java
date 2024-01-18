package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.reader.api.JSONFileReader;

public class TerritoryReader implements JSONFileReader<Territory> {

    private final ObjectMapper mapper;

    public TerritoryReader() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Set<Territory> readFileData(String path) {
        final InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(path));
        Set<Territory> territories = new HashSet<>();

        try {
            territories = mapper.readValue(in,
                    new TypeReference<Set<Territory>>() {
                    });
            defineNeighbours(territories);
        } catch (final IOException e) {
            System.out.println("Failed to read the file\n");
        }

        return territories;
    }

    private void defineNeighbours(Set<Territory> territories) {
        territories.forEach(t -> {
            Set<Territory> neighbours = t.getNeighbourNames().stream()
                    .map(tn -> getTerritoryByName(tn, territories))
                    .collect(Collectors.toSet());
            t.setNeighbours(neighbours);
        });
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
