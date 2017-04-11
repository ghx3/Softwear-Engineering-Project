/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gameModel.Map.Island;
import nz.ac.aut.ense701.gameModel.Tile.Tile;

/**
 *
 * @author Everybody's
 */
public class Handler {

    private Game game;
    private Island island;

    public Handler(Game game) {
        this.game = game;
    }

    public int getWorldWidthSize() {
        return island.getNumRows() * getGameController().getTileSizeX();
    }

    public int getWorldHeightSize() {
        return island.getNumColumns() * getGameController().getTileSizeY();
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

//    public KeyManager getKeyManager() {
//        if(this.game.getKeyManager() == null){
//            return null;
//        }
//        return this.game.getKeyManager();
//    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

}
