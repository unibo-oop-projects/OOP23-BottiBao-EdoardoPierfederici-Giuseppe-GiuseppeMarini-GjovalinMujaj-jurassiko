package it.unibo.jurassiko.reader.api;

import java.util.Set;

public interface JSONFileReader<T> {

    Set<T> readFileData(String filePath);

}
