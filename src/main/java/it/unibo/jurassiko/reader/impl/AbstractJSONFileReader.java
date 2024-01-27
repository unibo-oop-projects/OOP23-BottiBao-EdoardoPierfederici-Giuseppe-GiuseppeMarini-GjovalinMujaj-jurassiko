package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.reader.api.JSONFileReader;

public abstract class AbstractJSONFileReader<T> implements JSONFileReader<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper mapper;
    private final Class<T> targetClass;

    public AbstractJSONFileReader(final Class<T> targetClass) {
        this.mapper = new ObjectMapper();
        this.targetClass = targetClass;
    }

    @Override
    public Set<T> readFileData(final String filePath) {
        Set<T> data = new HashSet<>();
        // Represent the generic type contained by the Set to deserialize
        final JavaType type = mapper.getTypeFactory().constructCollectionType(Set.class, targetClass);

        try (InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath))) {
            data = mapper.readValue(in, type);
            buildAttributes(data);
        } catch (final IOException e) {
            this.logger.error("Failed to read " + filePath + " file");
        }

        return Set.copyOf(data);
    }

    protected abstract void buildAttributes(Set<T> data);

}
