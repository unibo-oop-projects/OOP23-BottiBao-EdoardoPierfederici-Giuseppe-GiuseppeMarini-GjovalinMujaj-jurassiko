package it.unibo.jurassiko.reader.api;

import java.util.Set;

/**
 * Interface to parse data from the JSON file of a generic type.
 * 
 * @param <T> The type of object to be read
 */
public interface JSONFileReader<T> {

    /**
     * Deserializes data from the given JSON file.
     * 
     * @param filePath path of the file to read
     * @return the Set of deserialized objects
     */
    Set<T> readFileData(String filePath);

}
