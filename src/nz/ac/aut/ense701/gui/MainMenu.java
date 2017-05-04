/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nz.ac.aut.ense701.gameModel.Utils.GameState;
import nz.ac.aut.ense701.gameModel.gfx.Assets;
import nz.ac.aut.ense701.main.Game;

/**
 * This class draws the background image
 *
 * @author Harindu Tillekeratna
 * @author David Balzer
 * @author George Xu
 * @author Thong Teav
 */
public class MainMenu extends JPanel {

    private JButton newGameButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private int width;
    private int height;
    private JPanel menuPane;

    public MainMenu(int width, int height) {

        this.width = width;
        this.height = height;
        createDisplay();
    }

    public JPanel createDisplay() {

        menuPane = this;
        menuPane.setSize(width, height);
        menuPane.setPreferredSize(new Dimension(width, height));
        menuPane.setMaximumSize(new Dimension(width, height));
        menuPane.setMinimumSize(new Dimension(width, height));
        menuPane.setLayout(null);

        //create the buttons
        newGameButton = new JButton("New Game");
        highscoreButton = new JButton("High Score");
        exitButton = new JButton("Exit Game");
        titleLabel = new JLabel("Kiwi Island");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.GREEN);

        //set the size and position of the components
        titleLabel.setBounds(width / 2 - 100, 30, 200, 100);
        newGameButton.setBounds(width / 2 - 100, 250, 200, 70);
        highscoreButton.setBounds(width / 2 - 100, 350, 200, 70);
        exitButton.setBounds(width / 2 - 100, 450, 200, 70);

        //add the components to the panel
        menuPane.add(titleLabel);
        menuPane.add(newGameButton);
        menuPane.add(highscoreButton);
        menuPane.add(exitButton);

        //listen for button press for a new game
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //frame.getContentPane().remove(menuPane);
                Game.state = GameState.PLAYING;
                menuPane.setVisible(false);

            }
        }
        );

        //listen for exit button press
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//terminate the application
            }
        }
        );

        return menuPane;
    }

    @Override
    /**
     * Draw the background image onto the panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Assets.background, 0, 0, getWidth(), getHeight(), null);
        this.repaint();
        this.revalidate();
    }

}
