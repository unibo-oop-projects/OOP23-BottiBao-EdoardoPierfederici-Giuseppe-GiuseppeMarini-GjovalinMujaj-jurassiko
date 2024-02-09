package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Test class to test Player.
 */
public class TestPlayer {

    private Player player;
    private final Set<Territory> territory = new TerritoryFactoryImpl().createTerritories();
    private final Set<Ocean> ocean = new OceanFactoryImpl().createOceans();
    private final Set<Objective> objective = new ObjectiveFactoryImpl().createObjectives();

    @BeforeEach
    void setup() {
        player = new PlayerImpl(Player.GameColor.RED, objective.stream().findFirst().get(),
                new HashSet<>(), new HashSet<>());
    }

    @Test
    void testTerritoriesAndOceans() {

        final var iterator = territory.iterator();
        while (iterator.hasNext()) {
            final var temp = iterator.next();
            player.removePlayerTerritory(temp);
            if (temp.getName().equals("Cina")) {
                player.addPlayerTerritory(temp);
                assertEquals(player.getOwnedTerritories(), Set.of(temp));
                player.removePlayerTerritory(temp);
                assertEquals(player.getOwnedTerritories(), Set.of());
            }
        }

        final var iteratorOcean = ocean.iterator();
        while (iterator.hasNext()) {
            final var temp = iteratorOcean.next();
            player.removePlayerOcean(temp);
            if (temp.getName().equals("Oceano Atlantico")) {
                player.addPlayerOcean(temp);
                assertEquals(player.getOwnedTerritories(), Set.of(temp));
                player.removePlayerOcean(temp);
                assertEquals(player.getOwnedTerritories(), Set.of());
            }
        }
    }

    @Test
    void testGetPlayer() throws CloneNotSupportedException {
        assertEquals(player.getColor(), Player.GameColor.RED);
        final Player temp = player.getPlayer();
        assertEquals(temp.getColor(), Player.GameColor.RED);
        assertNotEquals(temp, player);
    }

    @Test
    void testGetObjective() {
        final var temp = player.getObjective();
        assertNotEquals(temp, objective.stream().findFirst().get());
        assertNotEquals(temp, player.getObjective());
    }

    @Test
    void testGetBonus() {
        player.addPlayerTerritory(getTerritory("Groenlandia"));
        player.addPlayerTerritory(getTerritory("Canada"));
        assertEquals(1, player.getBonusGroundDino());
        player.addPlayerTerritory(getTerritory("Messico"));
        assertEquals(1, player.getBonusGroundDino());
        player.addPlayerTerritory(getTerritory("Appalachia"));
        // CHECKSTYLE: MagicNumber OFF
        // Test purpuse
        assertEquals(5, player.getBonusGroundDino());
        // CHECKSTYLE: MagicNumber ON
    }

    private Territory getTerritory(final String name) {
        final var result = territory.stream()
                .filter(e -> e.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase()))
                .findFirst();
        return result.get();
    }
}
