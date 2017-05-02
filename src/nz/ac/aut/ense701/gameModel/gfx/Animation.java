
package nz.ac.aut.ense701.gameModel.gfx;

import java.awt.image.BufferedImage;
import javax.sound.midi.SysexMessage;

/**
 *
 * @author Everybody's
 */
public class Animation {
    
    private int speed ,index;
    private long lastTime,timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index= 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer>speed){
            index++;
            timer =0;
            if(index >= frames.length){
                index =0;
            }
        }
    }
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    
}
