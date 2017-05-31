package nz.ac.aut.ense701.gameModel.Entity;

import java.awt.Graphics;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.gfx.Assets;
import nz.ac.aut.ense701.main.Game;
import nz.ac.aut.ense701.main.Handler;

/**
 * Abstract base class for occupants that inhabit Kiwi Island.
 * 
 * @author AS
 * @version 1.0 - July 2011
 * @version 2.0 - October 2011 - AS - added toString
 */
public abstract class Occupant 
{
    public static final int DEFAULT_OCCUPANT_WIDTH = 50;
    public static final int DEFAULT_OCCUPANT_HEIGHT = 50;
    protected Position position;
    protected final String   name;
    protected final String   description;
    public Handler handler;

    /**
     * Construct an occupant for a known position & name.
     * @param position the position of the occupant
     * @param name the name of the occupant
     * @param description a longer description
     */
    public Occupant(Handler handler,Position position, String name, String description) 
    {
        this.handler = handler;
        this.position    = position;
        this.name        = name;
        this.description = description;        
    }
    
    //for testing purpose
    public Occupant(Position position, String name, String description) 
    {
        this.handler = handler;
        this.position    = position;
        this.name        = name;
        this.description = description;        
    }
    
    /**
     * Returns the position of the occupant.
     * 
     * @return the position of the occupant
     */    
    public Position getPosition() 
    {
        return this.position;
    }
    
    /**
     * Changes the position of the occupant.
     * 
     * @param newPosition the new position
     */
    public void setPosition(Position newPosition) 
    {
        this.position = newPosition;
    }
    
    /**
     * Gets the occupant's name.
     * 
     * @return the name of the occupant
     */
    public String getName()
    {
        return this.name;
    } 

   /**
    * Gets the description for the item.
    * 
    * @return the description
    */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Returns the occupant's name for display.
     * 
     * @return the occupant's name
     */
    @Override
    public String toString()
    {
        return getName();
    }
    
    
    /**
     * Gets a string representation of the occupant.
     * Used for interpretation of file content
     * 
     * @return the string representation of the occupant
     */
    public abstract String getStringRepresentation();

    public void render(Graphics g){
        int xOffset= (Game.TILE_WIDTH - DEFAULT_OCCUPANT_WIDTH)/2;
        int yOffset = (Game.TILE_HEIGTH - DEFAULT_OCCUPANT_HEIGHT)/2; 
        
        g.drawImage(Assets.bindImage(name), (int)getPosition().getRow()* Game.TILE_WIDTH + xOffset
               , (int)getPosition().getColumn()* Game.TILE_HEIGTH + yOffset, 
               DEFAULT_OCCUPANT_WIDTH,DEFAULT_OCCUPANT_HEIGHT, null);
    }
}
