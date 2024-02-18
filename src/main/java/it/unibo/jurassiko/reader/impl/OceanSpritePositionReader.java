package it.unibo.jurassiko.reader.impl;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Implementation of {@link AbstractSpritePositionReader} to read sprite
 * positions of oceanic Dinos.
 */
public class OceanSpritePositionReader extends AbstractSpritePositionReader {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSpriteName(final JsonNode jsonNode) {
        return jsonNode.get("ocean").asText();
    }

}
