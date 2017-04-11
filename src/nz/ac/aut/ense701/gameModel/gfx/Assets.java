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
    
    public static BufferedImage player,sand,scrub,wetland,water,hazard,predator,Sandwich,tool;
    private static HashMap <String,BufferedImage> images;
    
    public static void Init(){
        images = new HashMap();
        
        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage("/textures/terrain.png"));
        player =  ImageLoader.loadImage("/textures/player1.png");
        sand = sheet.crop(5, 5, width-8, height-10);
        water = sheet.crop(2*width, 5, width-2, height-10);
        scrub = sheet.crop(width+2, 5, width-5, height-12);
        wetland = sheet.crop((3*width), 5, width-2, height-10);
        
        SpriteSheet occupants = new SpriteSheet(ImageLoader.loadImage("/textures/Occupants.png"));
        hazard = occupants.crop(0, 0, width, height);
        predator = occupants.crop(width, 0, width, height);
        Sandwich = occupants.crop(width*2, 0, width, height);
        tool = occupants.crop(width*3, 0, width, height);
        
        images.put("player",player );
        images.put("sand",sand );
        images.put("water", water);
        images.put("scrub",scrub );
        images.put("wetland",wetland );
        images.put("hazard",hazard );
        images.put("predator",predator );
        images.put("Sandwich", Sandwich);
        images.put("tool",tool );
//      images.put("", );
//      images.put("", );
       
    }
    
    public static BufferedImage bindImage(String name){
        return images.get("hazard");
        
    }
}
