package nz.ac.aut.ense701.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nz.ac.aut.ense701.gameModel.Utils.GameEventListener;
import nz.ac.aut.ense701.gameModel.Utils.GameState;
import nz.ac.aut.ense701.main.Handler;

public class Display implements GameEventListener, KeyListener {

    private Handler handler;
    private JFrame frame;//frfame instance

    private String title;
    private int width, height;//size of the frame, but applys to canvas + menu

    MainMenu mainmenu;
    GameDisplay gameDisplay;

    // End of variables declaration 
    public Display(String title, int width, int height, Handler handler) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.handler = handler;
        createDisplay();
    }

    private void createDisplay() {

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        setAsGameListener();

        mainmenu = new MainMenu(handler, width, height);
        gameDisplay = new GameDisplay(handler, width, height);
        handler.setDisplay(this);
        
        frame.add(mainmenu, BorderLayout.CENTER);
        frame.add(gameDisplay.initComponents(), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        

    }

    /**
     * This method is called by the game model every time something changes.
     * Trigger an update.
     */
    public void gameStateChanged() {
        gameDisplay.update();

        // check for "game over" or "game won"
        if (handler.getGameController().getState() == GameState.LOST) {
            JOptionPane.showMessageDialog(
                    frame,
                    handler.getGameController().getLoseMessage(), "Game over!",
                    JOptionPane.INFORMATION_MESSAGE);
            handler.getGameController().createNewGame();
            handler.getGameController().getPlayer().setXY();
        } else if (handler.getGameController().getState() == GameState.WON) {
            JOptionPane.showMessageDialog(
                    frame,
                    handler.getGameController().getWinMessage(), "Well Done!",
                    JOptionPane.INFORMATION_MESSAGE);
            handler.getGameController().createNewGame();
            handler.getGameController().getPlayer().setXY();
        } else if (handler.getGameController().messageForPlayer()) {
            JOptionPane.showMessageDialog(
                    frame,
                    handler.getGameController().getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setAsGameListener() {
        handler.getGameController().addGameEventListener(this);
    }

    public Canvas getCanvas() {
        return gameDisplay.getCanvas();
    }

    public int getCanvasWidth() {
        return gameDisplay.getCanvasWidth();
    }

    public int getCanvasHeight() {
        return gameDisplay.getCanvasHeight();
    }

    public JFrame getFrame() {
        return frame;
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }    
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        handler.getGameController().getPlayer().move(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
