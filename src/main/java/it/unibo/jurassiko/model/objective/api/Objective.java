package it.unibo.jurassiko.model.objective.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.objective.impl.ConquerTerritoriesObjective;
import it.unibo.jurassiko.model.objective.impl.DestroyArmyObjective;

/**
 * Interface for game objectives.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = ConquerTerritoriesObjective.class, name = "conquerTerritories"),
        @Type(value = DestroyArmyObjective.class, name = "destroyArmy"),
        @Type(value = ConquerContinentsObjective.class, name = "conquerContinents")
})
public interface Objective {

    /**
     * @return the default string for the objective type
     */
    String getType();

    /**
     * Checks if the objective has been completed successfully.
     * 
     * @return true if the objective has been achieved, false otherwise
     */
    boolean isAchieved();

    /**
     * Processes the description of the objective using data parsed from the
     * configuration file.
     */
    void writeDescription();

    /**
     * @return the description of the objective
     */
    String getDescription();

}
