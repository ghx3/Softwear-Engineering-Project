package nz.ac.aut.ense701.gameModel.Entity;

import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.main.Handler;

/**
 * Kiwi represents a kiwi living on the island
 *
 * @author AS
 * @version July 2011
 */
public class Kiwi extends Fauna {

    private boolean counted;

    /**
     * Constructor for objects of class Kiwi
     *
     * @param pos the position of the kiwi object
     * @param name the name of the kiwi object
     * @param description a longer description of the kiwi
     */
    public Kiwi(Handler handler, Position pos, String name, String description) {
        super(handler, pos, name, description);
        counted = false;
    }

      public Kiwi( Position pos, String name, String description) {
        super( pos, name, description);
        counted = false;
    }
    /**
     * Count this kiwi
     */
    public void count() {
        counted = true;
    }

    /**
     * Has this kiwi been counted
     *
     * @return true if counted.
     */
    public boolean counted() {
        return counted;
    }

    @Override
    public String getStringRepresentation() {
        return "K";
    }
}
