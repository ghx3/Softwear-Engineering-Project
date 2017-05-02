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

    public static BufferedImage player, sand, scrub, wetland, water, forest;

    public static BufferedImage fernbird,
            heron, tui, robin, kiwi, apple, muesliBar, orangeJuice, sandwich, trap,
            sun, pond, cliff, rock, brokenTrap, possum, cat, rat, kiore, tool,oystercatcher,
            crab,fall,stoat;

    private static HashMap<String, BufferedImage> images;

    public static void Init() {
        images = new HashMap();

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/terrain.png"));
        player = ImageLoader.loadImage("/textures/player1.png");
        sand = sheet.crop(5, 5, width - 8, height - 10);
        water = sheet.crop(2 * width, 5, width - 2, height - 10);
        scrub = sheet.crop(width + 2, 5, width - 5, height - 12);
        wetland = sheet.crop((3 * width), 5, width - 2, height - 10);

        SpriteSheet occupants = new SpriteSheet(ImageLoader.loadImage("/textures/cleanPics.png"));
    
        //1st row
        apple = occupants.crop(0, 0, width, height);
        cat = occupants.crop(width, 0, width, height);
        fernbird = occupants.crop(2*width, 0, width, height);
        heron = occupants.crop(3*width, 0, width, height);
        kiore = occupants.crop(4*width, 0, width, height);
        
        //2nd row
        kiwi = occupants.crop(0, height, width, height);
        muesliBar = occupants.crop(width, height, width, height);
        orangeJuice = occupants.crop(2*width, height, width, height);
        pond = occupants.crop(3*width, height, width, height);
        possum = occupants.crop(4*width, height, width, height);
        
           //3nd row
        rat = occupants.crop(0, 2*height, width, height);
        robin = occupants.crop(width,2* height, width, height);
        rock = occupants.crop(2*width,2* height, width, height);
        tool = occupants.crop(3*width, 2*height, width, height);
        sun = occupants.crop(4*width, 2* height, width, height);
        
          //4th row
        trap = occupants.crop(0, 3*height, width, height);
        tui = occupants.crop(width,3* height, width, height);
         cliff = occupants.crop(2*width, 3*height, width, height);
        sandwich = occupants.crop(3*width,3* height, width, height);
         oystercatcher = occupants.crop(4*width, 3*height, width, height);
       
      //5th row
        brokenTrap = occupants.crop(0, 4*height, width, height);
         crab = occupants.crop(width,4* height, width, height);
         fall = occupants.crop(2*width, 4*height, width, height);
         stoat = occupants.crop(3*width,4* height, width, height);
//         = occupants.crop(4*width, 4*height, width, height);

        //Add to hashmap
        images.put("Player", player);
        images.put("Sand", sand);
        images.put("Water", water);
        images.put("Scrub", scrub);
        images.put("Wetland", wetland);
        images.put("Forest", forest);
        images.put("Fernbird", fernbird);
        images.put("Heron", heron);
        images.put("Tui", tui);
        images.put("Robin", robin);
        images.put("Kiwi", kiwi);
        images.put("Apple", apple);
        images.put("MuesliBar", muesliBar);
        images.put("Orange Juice", orangeJuice);
        images.put("Sandwich", sandwich);
        images.put("Trap", trap);
        images.put("Sun", sun);
        images.put("Pond", pond);
        images.put("Cliff", cliff);
        images.put("Rock", rock);
        images.put("BrokenTrap", brokenTrap);
        images.put("Possum", possum);
        images.put("Cat", cat);
        images.put("Rat", rat);
        images.put("Kiore", kiore);
        images.put("Screwdriver", tool);
        images.put("Oystercatcher", oystercatcher);
        images.put("Crab", crab);
        images.put("Fall", fall);
        images.put("Stoat", stoat);
    }

    public static BufferedImage bindImage(String name) {
        return images.get(name);

    }
}


