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

// TODO: edit and adapt the tests to the JSON files once completed
public class TestObjective {

    private static final int NUM_TOTAL_OBJECTIVES = 7;
    private static final int NUM_CONQTERRITORIES = 2;
    private static final int NUM_CONQCONTINENTS = 2;
    private static final int NUM_DESTROYARMY = 3;

    // private ObjectiveFactory objectiveFactory;
    private Set<Objective> objectives;

    @BeforeEach
    public void setUp() {
        // this.objectiveFactory = new ObjectiveFactoryImpl();
        // this.objectives = this.objectiveFactory.createObjectives();
    }

    @Test
    public void testObjectiveReader() {
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
    public void testConquerContinents() {
        final Set<String> continents = Set.of("Europa", "Nord America");
        final String description1 = "Conquista i seguenti continenti: Europa, Nord America.";
        final String description2 = "Conquista i seguenti continenti: Nord America, Europa.";

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
    public void testConquerTerritories() {

    }

    @Test
    public void testDestroyArmy() {
        final Set<String> armyColors = Set.of("ROSSO", "GIALLO", "BLU");

        final var destroyArmyObjectives = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .map(DestroyArmyObjective.class::cast)
                .collect(Collectors.toSet());

        assertTrue(destroyArmyObjectives.stream()
                .map(DestroyArmyObjective::getArmyColor)
                .collect(Collectors.toSet())
                .equals(armyColors));
    }

    @Test
    public void testAchieved() {

    }

}
