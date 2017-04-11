package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gui.KeyManager;
import nz.ac.aut.ense701.gui.Display;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import nz.ac.aut.ense701.gameModel.gfx.Assets;

public class Game implements Runnable {
    
    private Display display;
    private int width,height;
    public String title;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
  
    private KeyManager keyManager;
    private GameController gameController;
    
    private Handler handler;
       
    
    public Game(String title,int width,int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        
    }    
    
    public void init(){
        handler = new Handler (this);
        gameController = new GameController( handler);
        display = new Display(title,width,height,handler);
        //display.getFrame().addKeyListener(keyManager);
        Assets.Init();
            
    }
    
    private void tick(){
        //keyManager.tick();
        gameController.tick();
    }
    
    private void render(){
//        
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clean screen
        g.clearRect(0, 0, width, height);
        
        gameController.render(g);
      
        bs.show();
        g.dispose();
    }
    
    @Override
    public void run() {
        
        init();
        
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime)/ timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >=1){
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000){
               // System.out.println("ticks and Frames: "+ ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running){
            return;
        }
        
        running = false;
        
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameController getGameController() {
        return gameController;
    }
    
    
}
