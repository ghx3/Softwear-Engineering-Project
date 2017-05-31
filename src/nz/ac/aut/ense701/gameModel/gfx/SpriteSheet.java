package nz.ac.aut.ense701.gameModel.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Everybody's
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }

    
}
