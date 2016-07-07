package Affichage;


import Boutons.ChargerImage;
import Boutons.Exit;
import Boutons.SauvegarderImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Fenetre {
    
    private static final int HAUTEUR_FENETRE = 768;
    private static final int LARGEUR_FENETRE = 1024; 
    
    public static void main (String args[]) 
    {
        
        // Creation d'une instance d'image pour sauvegarder l'image chargee.
        Image image = new Image ();
        
        // Creation des differents elements.
        JFrame fenetre = new JFrame ("Anamorphose");
        JMenuBar menuBar = new JMenuBar ();
        JMenu menuFichier = new JMenu ("Fichiers");
        JMenu menuImage = new JMenu ("Traitement Image");
        JButton charger = new JButton ("Charger");
        JButton sauvegarder = new JButton ("Sauvegarder");
        JButton anamorphose = new JButton ("Anamorphose");
        
        // Menus Fichier.
        JMenuItem menuItemChargerImage = new JMenuItem ("Charger une image");
        JMenuItem menuItemSauvegarderImage = new JMenuItem ("Sauvegarder une image");
        JMenuItem menuItemExit = new JMenuItem ("Quitter le programme");
        
        // Menus Image.
        JMenuItem menuItemAnamorphoseCylindrique = new JMenuItem ("Anamorphose cylindrique");
        
        // Creer la fenetre.
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
        fenetre.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        
        // Ajoute les boutons.
        fenetre.add(charger);
        charger.addActionListener(new ChargerImage(image));
        fenetre.add(sauvegarder);
        
        // Ajoute les menus a la barre de menu.
        menuBar.add(menuFichier);
        menuBar.add(menuImage);
        
        // Afficher le bouton pour charger une image.
        menuItemChargerImage = new JMenuItem("Charger image", new ImageIcon("src/main/resources/folder.png"));
        menuItemChargerImage.addActionListener(new ChargerImage(image));
        menuFichier.add(menuItemChargerImage);
        
        // Afficher le bouton pour sauvegarder l'image.
        menuItemSauvegarderImage = new JMenuItem("Sauvegarder image", new ImageIcon("src/main/resources/disquette.png"));
        menuItemSauvegarderImage.addActionListener(new SauvegarderImage(fenetre));
        menuFichier.add(menuItemSauvegarderImage);
        
        // Affiche le bouton pour sortir du programme.
        menuItemExit = new JMenuItem ("Exit", new ImageIcon("src/main/resources/croix.png"));
        menuItemExit.addActionListener(new Exit());
        menuFichier.add(menuItemExit);
        
        // Afficher le bouton pour proceder a l'anamorphose cylindrique.
        menuItemAnamorphoseCylindrique = new JMenuItem ("Anamorphose cylindrique", new ImageIcon("src/main/resources/cylindre.png"));
        menuItemAnamorphoseCylindrique.addActionListener(new Exit()); // CHANGER L'ACTION.
        menuImage.add(menuItemAnamorphoseCylindrique);
        
        // Afficher
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setJMenuBar(menuBar);
    }
}
