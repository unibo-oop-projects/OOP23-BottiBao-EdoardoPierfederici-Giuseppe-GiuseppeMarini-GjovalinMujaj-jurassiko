package it.unibo.jurassiko.model.objective.api;

public interface Objective {

    /**
     * @return true if the objective has been accomplished successfully, false
     *         otherwise
     */
    boolean isAchieved();

    void writeDescription();

    /**
     * @return the description of the objective
     */
    String getDescription();

}
