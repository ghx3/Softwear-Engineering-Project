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
            sun, pond, cliff, rock, brokenTrap, possum, cat, rat, kiore, tool;

    private static HashMap<String, BufferedImage> images;

    public static void Init() {
        images = new HashMap();

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/terrain.png"));
        player = ImageLoader.loadImage("/textures/player1.png");
        sand = sheet.crop(5, 5, width - 8, height - 10);
        water = sheet.crop(2 * width, 5, width - 2, height - 10);
        scrub = sheet.crop(width + 2, 5, width - 5, height - 12);
        wetland = sheet.crop((3 * width), 5, width - 2, height - 10);

        SpriteSheet occupants = new SpriteSheet(ImageLoader.loadImage("/textures/Pictures.png"));
    

        apple = occupants.crop(0, 0, width, height);
   

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
        images.put("OrangeJuice", orangeJuice);
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
        images.put("Tool", tool);

    }

    public static BufferedImage bindImage(String name) {
        return images.get(name);

    }
}


