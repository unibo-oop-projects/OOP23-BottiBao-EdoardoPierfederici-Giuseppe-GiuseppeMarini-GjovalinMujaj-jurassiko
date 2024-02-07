package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.objective.impl.ConquerTerritoriesObjective;
import it.unibo.jurassiko.model.objective.impl.DestroyArmyObjective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;

// TODO: Some tests are not implemented yet
class TestObjective {

    private static final int NUM_TOTAL_OBJECTIVES = 8;
    private static final int NUM_CONQTERRITORIES = 2;
    private static final int NUM_CONQCONTINENTS = 3;
    private static final int NUM_DESTROYARMY = 3;

    private Set<Objective> objectives;

    @BeforeEach
    void initFactory() {
        this.objectives = new ObjectiveFactoryImpl().createObjectives();
    }

    @Test
    void testObjectiveReader() {
        assertNotNull(objectives);
        assertFalse(objectives.isEmpty());

        assertEquals(NUM_TOTAL_OBJECTIVES, objectives.size());
        assertEquals(NUM_CONQCONTINENTS,
                objectives.stream().filter(ConquerContinentsObjective.class::isInstance).count());
        assertEquals(NUM_CONQTERRITORIES,
                objectives.stream().filter(ConquerTerritoriesObjective.class::isInstance).count());
        assertEquals(NUM_DESTROYARMY,
                objectives.stream().filter(DestroyArmyObjective.class::isInstance).count());
    }

    @Test
    void testConquerContinents() {
        final Set<String> continents = Set.of("Gondwana Occidentale", "Nord America");
        final String description1 = "Conquista la totalità dei seguenti continenti: Gondwana Occidentale, Nord America.";
        final String description2 = "Conquista la totalità dei seguenti continenti: Nord America, Gondwana Occidentale.";

        final var conquerContinentsObjectives = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .map(ConquerContinentsObjective.class::cast)
                .collect(Collectors.toSet());

        assertTrue(conquerContinentsObjectives.stream()
                .anyMatch(o -> o.getContinents().equals(continents)));

        final var actualDescription = conquerContinentsObjectives.stream()
                .filter(s -> s.getContinents().equals(continents))
                .findAny()
                .get()
                .getDescription();
        assertTrue(actualDescription.equals(description1) || actualDescription.equals(description2));
    }

    @Test
    void testConquerTerritories() {

    }

    @Test
    void testDestroyArmy() {
        final Set<String> armyColors = Set.of("ROSSO", "GIALLO", "BLU");

        final var destroyArmyObjectives = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .map(DestroyArmyObjective.class::cast)
                .collect(Collectors.toSet());

        assertEquals(armyColors, destroyArmyObjectives.stream()
                .map(DestroyArmyObjective::getArmyColor)
                .collect(Collectors.toSet()));
    }

    @Test
    void testAchieved() {

    }

    @Test
    void testClone() {
        final var contObjective = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .findAny()
                .map(ConquerContinentsObjective.class::cast)
                .get();

        final var contObjectiveClone = (ConquerContinentsObjective) contObjective.getClone();
        assertEquals(contObjective.getType(), contObjectiveClone.getType());
        assertEquals(contObjective.getContinents(), contObjectiveClone.getContinents());
        assertEquals(contObjective.getDescription(), contObjectiveClone.getDescription());
        assertEquals(contObjective.isAchieved(), contObjectiveClone.isAchieved());

        final var terrObjective = objectives.stream()
                .filter(ConquerTerritoriesObjective.class::isInstance)
                .findAny()
                .map(ConquerTerritoriesObjective.class::cast)
                .get();

        final var terrObjectiveClone = (ConquerTerritoriesObjective) terrObjective.getClone();
        assertEquals(terrObjective.getType(), terrObjectiveClone.getType());
        assertEquals(terrObjective.getNumTerritories(), terrObjectiveClone.getNumTerritories());
        assertEquals(terrObjective.getMinDinos(), terrObjectiveClone.getMinDinos());
        assertEquals(terrObjective.getDescription(), terrObjectiveClone.getDescription());
        assertEquals(terrObjective.isAchieved(), terrObjectiveClone.isAchieved());

        final var armyObjective = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .findAny()
                .map(DestroyArmyObjective.class::cast)
                .get();

        final var armyObjectiveClone = (DestroyArmyObjective) armyObjective.getClone();
        assertEquals(armyObjective.getType(), armyObjectiveClone.getType());
        assertEquals(armyObjective.getArmyColor(), armyObjectiveClone.getArmyColor());
        assertEquals(armyObjective.getDescription(), armyObjectiveClone.getDescription());
        assertEquals(armyObjective.isAchieved(), armyObjectiveClone.isAchieved());
    }

}
