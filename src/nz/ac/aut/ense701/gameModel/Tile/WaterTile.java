package nz.ac.aut.ense701.gameModel.Tile;
import java.awt.image.BufferedImage;
import nz.ac.aut.ense701.gameModel.gfx.Assets;

/**
 *
 * @author Everybody's
 */
public class WaterTile extends Tile{
    
    public WaterTile( int id) {
        super(Assets.water, id);
    }
    
     public boolean isSolid(){
        return true;
    }
    
}
