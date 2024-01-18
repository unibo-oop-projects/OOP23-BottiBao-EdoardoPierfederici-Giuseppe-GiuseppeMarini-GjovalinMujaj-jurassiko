package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.reader.api.JSONFileReader;

public class ObjectiveReader implements JSONFileReader<Objective> {

    private final ObjectMapper mapper;

    public ObjectiveReader() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Set<Objective> readFileData(String path) {
        Set<Objective> objectives = new HashSet<>();

        try (final InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(path))) {
            objectives = mapper.readValue(in,
                    new TypeReference<Set<Objective>>() {
                    });
            writeDescriptions(objectives);
        } catch (final IOException e) {
            System.out.println("Impossibile leggere il file\n"); // TODO: logger?
        }

        return objectives;
    }

    private void writeDescriptions(Set<Objective> objectives) {
        for (var objective : objectives) {
            objective.writeDescription();
        }
    }

}
