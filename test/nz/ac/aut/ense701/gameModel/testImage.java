/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import nz.ac.aut.ense701.gameModel.gfx.Assets;

/**
 *
 * @author Everybody's
 */
public class testImage {

    public static void main(String[] args) throws IOException {
        Assets.Init();
        Collection images = Assets.images.values();
        Iterator<BufferedImage> itr = images.iterator();

        while (itr.hasNext()) {
            BufferedImage img = itr.next();
            JFrame f = new JFrame();
            JLabel label = new JLabel(new ImageIcon(img));
            f.getContentPane().add(label);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.pack();
            f.setLocation(200, 200);
            f.setVisible(true);

        }

    }
}
