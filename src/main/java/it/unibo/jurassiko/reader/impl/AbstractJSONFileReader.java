package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.model.territory.api.BoardArea;
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
        // Generic type contained by the Set to deserialize
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

    // TODO: these following methods can be deleted if only neighbour names (and not
    // instances) are needed

    /**
     * Processes and sets the bordering areas.
     * 
     * @param <A>   The type of area (Territory or Ocean) to process
     * @param areas the set containing the territories or the oceans read by the
     *              parser
     */
    protected <A extends BoardArea<A>> void defineNeighbours(final Set<A> areas) {
        areas.forEach(a -> {
            final Set<A> neighbours = a.getNeighbourNames().stream()
                    .map(n -> getBoardAreaByName(n, areas))
                    .collect(Collectors.toSet());
            a.setNeighbours(neighbours);
        });
    }

    /**
     * Returns the board area instance with the specified name.
     * 
     * @param <A>        The type of area (Territory or Ocean) to process
     * @param name       the name of the board area
     * @param boardAreas the set containing the territories or the oceans read by
     *                   the parser
     * @return the corresponding board area instance
     * @throws IllegalArgumentException if no board area with the given name is
     *                                  found
     */
    protected <A extends BoardArea<A>> A getBoardAreaByName(final String name, final Set<A> boardAreas) {
        for (final var area : boardAreas) {
            if (area.getName().equals(name)) {
                return area;
            }
        }
        throw new IllegalArgumentException("Board area \"" + name + "\" not found");
    }

}
