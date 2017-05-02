package nz.ac.aut.ense701.gameModel.Entity;

import java.awt.Graphics;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_HEIGHT;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_WIDTH;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.Tile.Tile;
import nz.ac.aut.ense701.gameModel.gfx.Assets;
import nz.ac.aut.ense701.main.Handler;

/**
 * Fauna at this point represents any species that is not a kiwi or a predator
 * on the island. If we need additional endangered species this class should
 * have descendant classes created.
 *
 * @author AS
 * @version July 2011
 */
public class Fauna extends Occupant {

    /**
     * Constructor for objects of class Endangered
     *
     * @param pos the position of the kiwi
     * @param name the name of the kiwi
     * @param description a longer description of the kiwi
     */
    public Fauna(Handler handler, Position pos, String name, String description) {
        super(handler, pos, name, description);
    }
    
    //For testing purpose
    public Fauna( Position pos, String name, String description) {
        super( pos, name, description);
    }
    @Override
    public String getStringRepresentation() {
        return "F";
    }

}
