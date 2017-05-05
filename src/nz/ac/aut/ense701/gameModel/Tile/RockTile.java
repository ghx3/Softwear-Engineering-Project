package nz.ac.aut.ense701.gameModel.Tile;

import nz.ac.aut.ense701.gameModel.gfx.Assets;


public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
