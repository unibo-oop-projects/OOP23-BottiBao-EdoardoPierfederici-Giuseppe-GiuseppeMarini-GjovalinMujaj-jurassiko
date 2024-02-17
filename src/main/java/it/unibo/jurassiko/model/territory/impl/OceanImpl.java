package it.unibo.jurassiko.model.territory.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.jurassiko.model.territory.api.BoardArea;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Implementation of the game Ocean.
 */
@SuppressWarnings("PMD") // This class is used by the Jackson deserializer for the factory
public final class OceanImpl extends AbstractBoardArea<Ocean> implements Ocean {

    @JsonProperty("territories")
    private Set<String> adjTerritoryNames;

    @JsonIgnore
    private Set<Territory> adjTerritories;

    // NOPMD using the class for Jackson deserializer for the factory
    private OceanImpl() {
        this.adjTerritories = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAdjTerritoryNames() {
        return Set.copyOf(adjTerritoryNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdjTerritories(final Set<Territory> adjTerritories) {
        Objects.requireNonNull(adjTerritories);
        this.adjTerritories = new HashSet<>(adjTerritories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getAdjTerritories() {
        return Set.copyOf(adjTerritories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjTerritory(final String territoryName) {
        Objects.requireNonNull(adjTerritories);
        return adjTerritories.stream()
                .map(BoardArea::getName)
                .anyMatch(territoryName::equals);
    }

}
