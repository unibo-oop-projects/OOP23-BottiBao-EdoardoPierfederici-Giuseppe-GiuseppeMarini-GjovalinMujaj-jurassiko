package it.unibo.jurassiko.controller.game.impl;

import it.unibo.jurassiko.controller.game.api.GameStarter;
import it.unibo.jurassiko.controller.game.api.MainController;
import it.unibo.jurassiko.view.panel.MapPanel;

public class GameStarterImpl implements GameStarter{
    
    private final MainController main;
    //TODO: mapPanel?
    private MapPanel mapPanel;
    
    GameStarterImpl(){
        this.main = new MainControllerImpl();

    }


}
