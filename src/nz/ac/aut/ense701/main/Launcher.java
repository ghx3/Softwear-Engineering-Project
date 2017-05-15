package nz.ac.aut.ense701.main;

/**
 *
 * @author Everybody's
 */
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Everybody's
 */
public class Launcher {
	public static JOptionPane p1;
	private static JLabel userName;
	private static JTextField input;

	public static void main(String[] args) {
		// Username
		p1 = new JOptionPane();
		userName = new JLabel("Enter new username: ");
		input = new JTextField(12);
		p1.setMessage(null);
		p1.setLayout(new FlowLayout());
		p1.setPreferredSize(new Dimension(300, 270));
		p1.add(userName, 0);
		p1.add(input, 1);
		JDialog d = p1.createDialog(p1, "Enter new name!");
		d.setVisible(true);
		Game game = new Game("Title", 800, 640);

		game.start();
		
	}
	 public String getuserName(){
    	return input.getText();
    }
}
