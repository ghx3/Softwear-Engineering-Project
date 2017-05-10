package nz.ac.aut.ense701.gameModel.Entity;

import java.awt.Graphics;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_HEIGHT;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_WIDTH;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.Tile.Tile;
import nz.ac.aut.ense701.gameModel.gfx.Assets;
import nz.ac.aut.ense701.main.Handler;

/**
 * This class represents a tool that can be found on the island
 * and gives the player any sort of advantage. 
 * 
 * @author AS
 * @version July 2011
 */

public class Tool extends Item 
{
    private boolean broken;
    
    /**
     * Construct a tool with known attributes.
     * @param pos the position of the tool
     * @param name the name of the tool
     * @param description a longer description of the tool
     * @param weight the weight of the tool
     * @param size the size of the tool
     */
    public Tool(Handler handler,Position pos, String name, String description, double weight, double size) 
    {
        super(handler,pos, name, description, weight, size);
        this.broken = false;
    }
    
    public Tool(Position pos, String name, String description, double weight, double size) 
    {
        super(pos, name, description, weight, size);
        this.broken = false;
    }
    /**
     * Break the tool
     */
    public void setBroken()
    {
        broken = true;
    }
    
    /**
     * Fix the tool
     */
    public void fix()
    {
        broken = false;
    }
    
    /**
     * Is tool broken
     * @return true if broken
     */
    public boolean isBroken()
    {
        return this.broken;
    }
    
    /**
    * Check if this tool is a predator trap
    * @return true if trap
     */
    public boolean isTrap()
    {
      String name = this.getName();
      return name.equalsIgnoreCase("Trap");
    }
 
    /**
    * Check if this tool is a screwdriver
    * @return true if screwdriver
     */    
    public boolean isScrewdriver() {
       String name = this.getName();
      return name.equalsIgnoreCase("Screwdriver"); 
    }
    
    
    @Override
    public String getStringRepresentation() 
    {
        return "T";
    }

    
}
