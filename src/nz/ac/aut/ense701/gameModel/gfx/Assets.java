package nz.ac.aut.ense701.gameModel.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Everybody's
 */
/*public class Assets {
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
}*/
public class Assets {
    public static int width = 110;
    public static int height = 126;
    
    public static BufferedImage player,sand,scrub,wetland,water,forest,fernbird,
            heron,tui,robin,kiwi,apple,muesliBar,orangeJuice,sandwich,trap,
            sun,pond,cliff,rock,brokenTrap,possum,cat,rat,kiore,tool;
    private static HashMap <String,BufferedImage> images;
    
    public static void Init(){
        images = new HashMap();

        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage("/textures/ALLPics.jpg"));
	
	//Player
        player =  ImageLoader.loadImage("/textures/player1.png");

        //Terrain
        water = sheet.crop(0, 4*height, width, height);
        wetland = sheet.crop(width, 4*height, width, height);
        scrub = sheet.crop(2*width, 4*height, width, height);
        wetland = sheet.crop(3*width, 4*height, width, height);
        forest = sheet.crop(4*width, 4*height, width, height);

        //Occupants fauna & kiwi
        fernbird = sheet.crop(0, 3*height, width, height);
        heron = sheet.crop(width, 3*height, width, height);
        tui = sheet.crop(2*width, 3*height, width, height);
        robin = sheet.crop(3*width, 3*height, width, height);
        kiwi = sheet.crop(4*width, 3*height, width, height);

        //Items
        apple = sheet.crop(0, 2*height, width, height);
        muesliBar = sheet.crop(width, 2*height, width, height);
        orangeJuice = sheet.crop(2*width, 2*height, width, height);
        sandwich = sheet.crop(3*width, 2*height, width, height);
        trap = sheet.crop(4*width, 2*height, width, height);

        //Hazards
        sun = sheet.crop(0, height, width, height);
        pond = sheet.crop(width, height, width, height);
        cliff = sheet.crop(2*width, height, width, height);
        rock = sheet.crop(3*width, height, width, height);
        brokenTrap = sheet.crop(4*width, height, width, height);

        //Predators and screwdriver
        possum = sheet.crop(0, 0, width, height);
        cat = sheet.crop(width, 0, width, height);
        rat = sheet.crop(2*width, 0, width, height);
        kiore = sheet.crop(3*width, 0, width, height);
        tool = sheet.crop(4*width, 0, width, height);

        //Add to hashmap
        images.put("player",player );
        images.put("sand",sand );
        images.put("water", water);
        images.put("scrub",scrub );
        images.put("wetland",wetland );
        images.put("forest",forest );
        images.put("fernbird",fernbird );
        images.put("heron", heron);
        images.put("tui",tui );
        images.put("robin",robin );
        images.put("kiwi",kiwi );
        images.put("apple",apple );
        images.put("muesliBar", muesliBar);
        images.put("orangeJuice",orangeJuice );
        images.put("sandwich",sandwich );
        images.put("trap",trap );
        images.put("sun",sun );
        images.put("pond", pond);
        images.put("cliff",cliff );
        images.put("rock",rock );
        images.put("brokenTrap",brokenTrap );
        images.put("possum",possum );
        images.put("cat", cat);
        images.put("rat",rat );
        images.put("kiore",kiore );
        images.put("tool",tool );
        
    }
    
    public static BufferedImage bindImage(String name){
        return images.get(name);
        
    }
}

