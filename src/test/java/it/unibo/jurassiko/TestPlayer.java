package it.unibo.jurassiko;

import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

public class TestPlayer {

    private Player player;

    @Test
    void testTerritoriesAndOceans() {
        player = new PlayerImpl(Player.Color.BLUE, new ConquerContinentsObjective(),
                new TerritoryFactoryImpl().createTerritories(),
                new OceanFactoryImpl().createOceans(), 0, 0);
        
    }
}