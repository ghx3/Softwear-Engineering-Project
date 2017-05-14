package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gui.Display;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import nz.ac.aut.ense701.gameModel.Utils.GameDifficulty;
import nz.ac.aut.ense701.gameModel.Utils.GameState;
import nz.ac.aut.ense701.gameModel.gfx.Assets;

public class Game implements Runnable {

    public static int TILE_HEIGTH ;// this set the tiles width size to be print within the canvas
    public static int TILE_WIDTH ;// this set the tiles height size to be print within the canvas
    private int width, height;
    public String title;

    private Thread thread;
    private Display display;
    private GameController gameController;
    private Handler handler;
    private BufferStrategy bs;
    private Graphics g;
    private boolean running = false;
    public static GameState state;
    public static GameDifficulty gameDifficulty;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

    }

    public void init() {
        Assets.Init();
        handler = new Handler(this);
        gameController = new GameController(handler);
        display = new Display(title, width, height, handler);
        setTilesSize();
        gameController.getPlayer().setXY();
        state = GameState.MENU;

    }

    private void tick() {

        if (state == GameState.PLAYING) {
            gameController.tick();
        }
    }

    private void render() {
//      
        if (state == GameState.PLAYING) {
            bs = display.getCanvas().getBufferStrategy();
            if (bs == null) {
                display.getCanvas().createBufferStrategy(3);
                return;
            }
            g = bs.getDrawGraphics();
            //Clean screen
            g.clearRect(0, 0, width, height);
            
            switch (gameDifficulty) {
                case EASY:
                    gameController.setDifficulty(0);
                    break;
                case MEDIUM:
                    gameController.setDifficulty(1);
                    break;
                case HARD:
                    gameController.setDifficulty(2);
                    break;
            }
            
            gameController.render(g);

            bs.show();
            g.dispose();
        }
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                // System.out.println("ticks and Frames: "+ ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
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

    public void setTilesSize() {
        TILE_WIDTH = display.getCanvasWidth() / gameController.getIsland().getNumRows();
        TILE_HEIGTH = display.getCanvasHeight() / gameController.getIsland().getNumColumns();
        //System.out.println(TILE_WIDTH+"  :  "+TILE_HEIGTH);
    }

    public GameController getGameController() {
        return gameController;
    }

}
