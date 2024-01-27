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

/**
 * Abstract class providing a common implementation to read data from a JSON
 * file with different types of objects and producing and Set of elements.
 * 
 * @param <T> The type of object to be read
 */
public abstract class AbstractJSONFileReader<T> implements JSONFileReader<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper mapper;
    private final Class<T> targetClass;

    /**
     * Creates a JSONFileReader for the given class type.
     * 
     * @param targetClass The class of the objects to read used by Jackson parser
     */
    public AbstractJSONFileReader(final Class<T> targetClass) {
        this.mapper = new ObjectMapper();
        this.targetClass = targetClass;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * Adds the additional attributes to the data to complete the configurations of
     * the objects.
     * 
     * @param data the Set containing the elements read by the parser
     */
    protected abstract void buildAttributes(Set<T> data);

}
