package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

import it.unibo.jurassiko.model.borders.api.Border;
import it.unibo.jurassiko.model.borders.impl.BorderImpl;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

class TestBorder {
    
    private Border border;
    private Set<Territory> territories;
    private Set<Ocean> oceans;

    private static final String territoryNameString = "Madagascar";
    private static final String oceanNameString = "Oceano Tetide";
    private static final String oceanNameString2 = "Oceano Atlantico";
    private static final Set<String> neighbourNames = Set.of("India","Antartica");
    private static final Set<String> neighbourNames2 = Set.of("Tibet","Indonesia","Nord Africa",
        "Congo","Sud Africa","Australia","India","Madagascar","Antartica");


    @BeforeEach
    void initBorder(){
        this.border = new BorderImpl();
        this.territories = new TerritoryFactoryImpl().createTerritories();
        this.oceans = new OceanFactoryImpl().createOceans();
    }

    @Test
    void testBorder() {
        final Territory terr1 = this.territories.stream()
            .filter(s -> s.getName().equals(territoryNameString))
            .findFirst()
            .get();
        final Ocean ocean1 = this.oceans.stream()
            .filter(o -> o.getName().equals(oceanNameString))
            .findFirst()
            .get();
        assertEquals(neighbourNames, this.border.getTerritoriesBorder(terr1, ocean1));
        final Ocean ocean2 = this.oceans.stream()
            .filter(o -> o.getName().equals(oceanNameString2))
            .findFirst()
            .get();
        assertNotEquals(neighbourNames, this.border.getTerritoriesBorder(terr1, ocean2));
        assertEquals(neighbourNames2, this.border.getTerritoriesBorder(terr1, ocean2));
    }
}
