/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gameModel.Map.Island;
import nz.ac.aut.ense701.gameModel.Tile.Tile;
import nz.ac.aut.ense701.gui.Display;

/**
 *
 * @author Everybody's
 */
public class Handler {

    private Game game;
    private Island island;
    private Display display;

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
    
    

    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return this.game.getWidth();
    }

    public int getHeight() {
        return this.game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public GameController getGameController() {
        return game.getGameController();
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

}
