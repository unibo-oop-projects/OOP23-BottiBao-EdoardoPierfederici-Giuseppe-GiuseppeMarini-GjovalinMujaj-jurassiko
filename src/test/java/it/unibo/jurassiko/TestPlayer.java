package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Test class to test Player
 * */public class TestPlayer {

    private Player player;
    private final Set<Territory> territory = new TerritoryFactoryImpl().createTerritories();
    private final Set<Ocean> ocean = new OceanFactoryImpl().createOceans();

    @Test
    void testTerritoriesAndOceans() {
        player = new PlayerImpl(Player.Color.BLUE, new ConquerContinentsObjective(),
                new HashSet<>(), new HashSet<>(), 0, 0);

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
        player = new PlayerImpl(Player.Color.RED, null, null, null, 0, 0);
        assertEquals(player.getColor(), Player.Color.RED);
        final Player temp = player.getPlayer();
        assertEquals(temp.getColor(), Player.Color.RED);
        assertNotEquals(temp, player);
        temp.setBonusGroundDino(1);
        assertEquals(player.getBonusGroundDino(), 0);
    }
}
