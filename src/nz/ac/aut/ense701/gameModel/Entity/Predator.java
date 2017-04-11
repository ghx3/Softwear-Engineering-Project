package nz.ac.aut.ense701.gameModel.Entity;

import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.main.Handler;

/**
 * Predator represents a predator on the island.
 * If more specific behaviour is required for particular predators, descendants 
 * for this class should be created
 * @author AS
 * @version July 2011
 */
public class Predator extends Fauna
{

    /**
     * Constructor for objects of class Predator
     * @param pos the position of the predator object
     * @param name the name of the predator object
     * @param description a longer description of the predator object
     */
    public Predator(Handler handler,Position pos, String name, String description) 
    {
        super( handler,pos, name, description);
    } 
 
    


    @Override
    public String getStringRepresentation() 
    {
        return "P";
    }    
}
