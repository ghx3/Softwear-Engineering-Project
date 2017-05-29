package nz.ac.aut.ense701.gameModel.Tile;

import nz.ac.aut.ense701.gameModel.gfx.Assets;


public class TreeTile extends Tile {

	public TreeTile(int id) {
		super(Assets.tree, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
