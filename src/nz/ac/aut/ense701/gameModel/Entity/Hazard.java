package nz.ac.aut.ense701.gameModel.Entity;

import java.awt.Graphics;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_HEIGHT;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_WIDTH;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.Tile.Tile;
import nz.ac.aut.ense701.gameModel.gfx.Assets;
import nz.ac.aut.ense701.main.Handler;

/**
 * This class represents a hazard that can be found on the island
 *
 * @author AS
 * @version July 2011
 */
public class Hazard extends Occupant {

    private final double impact;
    private final double FATAL_IMPACT = 1.0;

    /**
     * Create a hazard on the island
     *
     * @param position where hazard is.
     * @param name of hazard.
     * @param description a more detailed description of hazard
     * @param impact on player
     */
    public Hazard(Handler handler, Position position, String name, String description, double impact) {

        super(handler, position, name, description);
        this.impact = impact;

    }

    public Hazard(Position position, String name, String description, double impact) {

        super( position, name, description);
        this.impact = impact;

    }
    /**
     * What is impact of hazard
     *
     * @return impact
     */
    public double getImpact() {
        return this.impact;
    }

    /**
     * Checks if fatal impact
     *
     * @return true if fatal
     */
    public boolean isFatal() {
        return impact == FATAL_IMPACT;
    }

    /**
     * Checks if this is broken trap hazard
     *
     * @return true if this is broken trap hazard
     */
    public boolean isBreakTrap() {
        String name = this.getName();
        return name.equalsIgnoreCase("Broken trap");
    }

    @Override
    public String getStringRepresentation() {
        return "H";
    }

}
