package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.core.impl.GamePhaseImpl;

class TestGamePhase {

    private GamePhase gPhase;

    @BeforeEach
    void initPhase() {
        gPhase = new GamePhaseImpl();
    }

    @Test
    void testPhases() {
        assertEquals(gPhase.getPhase(), GamePhase.Phase.PLACEMENT);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.ATTACK);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.MOVEMENT);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.PLACEMENT);
    }
}
