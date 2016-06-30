package Affichage;


import Boutons.ChargerImage;
import Boutons.Exit;
import Boutons.SauvegarderImage;
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
	
    private static final int hauteurFenetre = 768;
    private static final int largeurFenetre = 1024;
    
	public static void main (String args[]) {
		
		
		JFrame fenetre = new JFrame ("Anamorphose");
		JMenuBar menuBar = new JMenuBar ();
                JMenu menuFichier = new JMenu ("Fichiers");
                JMenu menuImage = new JMenu ("Traitement Image");
                
                // Menus Fichier
                JMenuItem menuItemChargerImage = new JMenuItem ("Charger une image");
                JMenuItem menuItemSauvegarderImage = new JMenuItem ("Sauvegarder une image");
                JMenuItem menuItemExit = new JMenuItem ("Quitter le programme");
                
                // Menus Image
                JMenuItem menuItemAnamorphoseCylindrique = new JMenuItem ("Anamorphose cylindrique");
                
                // Creer la fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setPreferredSize(new Dimension(largeurFenetre, hauteurFenetre));
                
                
                // Ajoute les menus à la barre de menu.
                menuBar.add(menuFichier);
                menuBar.add(menuImage);

		// Afficher le boute pour charger une image.
		menuItemChargerImage = new JMenuItem("Charger image", new ImageIcon("src/main/resources/folder.png"));
                menuItemChargerImage.addActionListener(new ChargerImage(fenetre));
                menuFichier.add(menuItemChargerImage);
                
                // Afficher le bouton pour sauvegarder l'image.
                menuItemSauvegarderImage = new JMenuItem("Sauvegarder image", new ImageIcon("src/main/resources/disquette.png"));
                menuItemSauvegarderImage.addActionListener(new SauvegarderImage(fenetre));
                menuFichier.add(menuItemSauvegarderImage);
                
                // Affiche le bouton pour sortir du programme.
                menuItemExit = new JMenuItem ("Exit", new ImageIcon("src/main/resources/croix.png"));
                menuItemExit.addActionListener(new Exit());
                menuFichier.add(menuItemExit);
                
                // Afficher le bouton pour procéder à l'anamorphose cylindrique.
                menuItemAnamorphoseCylindrique = new JMenuItem ("Anamorphose cylindrique", new ImageIcon("src/main/resources/cylindre.png"));
                menuItemAnamorphoseCylindrique.addActionListener(new Exit()); // CHANGER L'ACTION.
                menuImage.add(menuItemAnamorphoseCylindrique);
                
		// Afficher
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setJMenuBar(menuBar);
	}
}
