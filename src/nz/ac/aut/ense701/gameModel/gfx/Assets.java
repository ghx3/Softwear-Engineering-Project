package nz.ac.aut.ense701.gameModel.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Everybody's
 */
public class Assets {
    public static int width = 110;
    public static int height = 126;
    
    public static BufferedImage player,sand,scrub,wetland,water,hazard,predator,food,tool;
    
    
    public static void Init(){
        
        
        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage("/textures/terrain.png"));
        player =  ImageLoader.loadImage("/textures/player1.png");
        sand = sheet.crop(5, 5, width-8, height-10);
        water = sheet.crop(2*width, 5, width-2, height-10);
        scrub = sheet.crop(width+2, 5, width-5, height-12);
        wetland = sheet.crop((3*width), 5, width-2, height-10);
        
        SpriteSheet occupants = new SpriteSheet(ImageLoader.loadImage("/textures/Occupants.png"));
        hazard = occupants.crop(0, 0, width, height);
        predator = occupants.crop(width, 0, width, height);
        food = occupants.crop(width*2, 0, width, height);
        tool = occupants.crop(width*3, 0, width, height);
        
    }
}
