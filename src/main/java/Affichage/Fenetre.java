package Affichage;


import Boutons.ChargerImage;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Fenetre {
	
	public static void main (String args[]) {
		
		
		JFrame fenetre = new JFrame ("Anamorphose");
		JMenuBar menuBar = new JMenuBar ();
                JMenu menu = new JMenu ("Fichiers");
                JMenuItem menuItem = new JMenuItem ("Test menu item", KeyEvent.VK_T);
                
                
                // Creer la fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setPreferredSize(new Dimension(1024, 768));
                
                
                // Affiche le menu dans le menuBar
                menu.setMnemonic(KeyEvent.VK_A);
                menu.getAccessibleContext().setAccessibleDescription("Test de menu");
                menuBar.add(menu);
                
                
                // Affiche le menuItem
                menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
                menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
                menu.add(menuItem);
                

		// Affichage le bouton charger une image
		menuItem = new JMenuItem("Charger image", new ImageIcon("src/main/resources/folder.png"));
                menuItem.setMnemonic(KeyEvent.VK_B);
                menuItem.addActionListener(new ChargerImage(fenetre));
                menu.add(menuItem);
                
                
		// Afficher
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setJMenuBar(menuBar);
	}
}
