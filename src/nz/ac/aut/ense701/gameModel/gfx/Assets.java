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

    public static BufferedImage playerSingle, sand, scrub, wetland, water, forest;

    public static BufferedImage fernbird,
            heron, tui, robin, kiwi, apple, muesliBar, orangeJuice, sandwich, trap,
            sun, pond, cliff, rock, brokenTrap, possum, cat, rat, kiore, tool, oystercatcher,
            crab, fall, stoat;

    public static BufferedImage[] player_down, player_up, player_left, player_right;
    
    public static BufferedImage background;

    public static HashMap<String, BufferedImage> images;

    public static void Init() {
        
        background = ImageLoader.loadImage("/kiwi.jpg");
        images = new HashMap();

        SpriteSheet terrain = new SpriteSheet(ImageLoader.loadImage("/textures/terrain.png"));
        playerSingle = ImageLoader.loadImage("/textures/player1.png");
        sand = terrain.crop(5, 5, width - 8, height - 10);
        water = terrain.crop(2 * width, 5, width - 2, height - 10);
        scrub = terrain.crop(width + 2, 5, width - 5, height - 12);
        wetland = terrain.crop((3 * width), 5, width - 2, height - 10);

        width = 32;
        height = 32;
        
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        player_down[0] = player.crop(width * 4, 0, width, height);
        player_down[1] = player.crop(width * 5, 0, width, height);
        player_up[0] = player.crop(width * 6, 0, width, height);
        player_up[1] = player.crop(width * 7, 0, width, height);
        player_right[0] = player.crop(width * 4, height, width, height);
        player_right[1] = player.crop(width * 5, height, width, height);
        player_left[0] = player.crop(width * 6, height, width, height);
        player_left[1] = player.crop(width * 7, height, width, height);

        width = 80;
        height = 80;
        SpriteSheet occupants = new SpriteSheet(ImageLoader.loadImage("/textures/Pictures.png"));

        //1st row
        apple = occupants.crop(0, 0, width, height);
        cat = occupants.crop(width, 0, width, height);
        fernbird = occupants.crop(2 * width, 0, width, height);
        heron = occupants.crop(3 * width, 0, width, height);
        kiore = occupants.crop(4 * width, 0, width, height);

        //2nd row
        kiwi = occupants.crop(0, height, width, height);
        muesliBar = occupants.crop(width, height, width, height);
        orangeJuice = occupants.crop(2 * width, height, width, height);
        pond = occupants.crop(3 * width, height, width, height);
        possum = occupants.crop(4 * width, height, width, height);

        //3nd row
        rat = occupants.crop(0, 2 * height, width, height);
        robin = occupants.crop(width, 2 * height, width, height);
        rock = occupants.crop(2 * width, 2 * height, width, height);
        tool = occupants.crop(3 * width, 2 * height, width, height);
        sun = occupants.crop(4 * width, 2 * height, width, height);

        //4th row
        trap = occupants.crop(0, 3 * height, width, height);
        tui = occupants.crop(width, 3 * height, width, height);
        cliff = occupants.crop(2 * width, 3 * height, width, height);
        sandwich = occupants.crop(3 * width, 3 * height, width, height);
        oystercatcher = occupants.crop(4 * width, 3 * height, width, height);

        //5th row
        brokenTrap = occupants.crop(0, 4 * height, width, height);
        crab = occupants.crop(width, 4 * height, width, height);
        fall = occupants.crop(2 * width, 4 * height, width, height);
        stoat = occupants.crop(3 * width, 4 * height, width, height);
//         = occupants.crop(4*width, 4*height, width, height);

        //Add to hashmap
        images.put(
                "Player", playerSingle);
        images.put(
                "Sand", sand);
        images.put(
                "Water", water);
        images.put(
                "Scrub", scrub);
        images.put(
                "Wetland", wetland);
        images.put(
                "Forest", forest);
        images.put(
                "Fernbird", fernbird);
        images.put(
                "Heron", heron);
        images.put(
                "Tui", tui);
        images.put(
                "Robin", robin);
        images.put(
                "Kiwi", kiwi);
        images.put(
                "Apple", apple);
        images.put(
                "MuesliBar", muesliBar);
        images.put(
                "Orange Juice", orangeJuice);
        images.put(
                "Sandwich", sandwich);
        images.put(
                "Trap", trap);
        images.put(
                "Sun", sun);
        images.put(
                "Pond", pond);
        images.put(
                "Cliff", cliff);
        images.put(
                "Rock", rock);
        images.put(
                "BrokenTrap", brokenTrap);
        images.put(
                "Possum", possum);
        images.put(
                "Cat", cat);
        images.put(
                "Rat", rat);
        images.put(
                "Kiore", kiore);
        images.put(
                "Screwdriver", tool);
        images.put(
                "Oystercatcher", oystercatcher);
        images.put(
                "Crab", crab);
        images.put(
                "Fall", fall);
        images.put(
                "Stoat", stoat);
    }

    public static BufferedImage bindImage(String name) {
        return images.get(name);

    }
}
