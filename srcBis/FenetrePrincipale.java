import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class FenetrePrincipale {

	
	public static void main (String args[]) {
		
		
		JFrame fenetre = new JFrame ("Anamorphose");
		JMenuBar menuBar = new JMenuBar ();
		
		// Creer la fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setPreferredSize(new Dimension(1, 600));
		
		
		
		// Afficher
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setJMenuBar(menuBar);
	}
}
