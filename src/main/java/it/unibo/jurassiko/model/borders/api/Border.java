package it.unibo.jurassiko.model.borders.api;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

public interface Border {
    
    /**
     * 
     * @param terr
     * @param ocean
     * @return
     */
    Set<String> getTerritoriesBorder(Territory terr, Ocean ocean);
}
