package nz.ac.aut.ense701.gameModel.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import nz.ac.aut.ense701.gameModel.Map.Terrain;
import nz.ac.aut.ense701.main.Game;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile sandTile = new SandTile(Terrain.SAND.getCode());
    public static Tile waterTile = new WaterTile(Terrain.FOREST.WATER.getCode());
    public static Tile scrubTile = new ScrubTile(Terrain.SCRUB.getCode());
    public static Tile wetLandTile = new WetLand(Terrain.WETLAND.getCode());
    public static Tile grassTile = new GrassTile(Terrain.GRASS.getCode());
    public static Tile dirtTile = new DirtTile(Terrain.DIRT .getCode());
    public static Tile rockTile = new RockTile(Terrain.ROCK.getCode());
    public static Tile treeTile = new TreeTile(Terrain.TREE.getCode());

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;

    }

    public int getId() {
        return id;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {

        g.drawImage(texture, x, y, Game.TILE_WIDTH, Game.TILE_HEIGTH, null);
    }

    public boolean isSolid() {
        return false;
    }

}
