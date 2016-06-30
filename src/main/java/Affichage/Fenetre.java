package Affichage;


import Boutons.ChargerImage;
import Boutons.Exit;
import Boutons.SauvegarderImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
    private static final int HAUTEUR_CASE = 56;
    private static final int LARGEUR_CASE = 128;
    private static final int NB_CASES = 3;
    
    public static void main (String args[]) {
        
        // Creation des différents elements.
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
        
        // ===================== Creation du Layout ========================
        // Creation des différents panels.
        JPanel cellCharge = new JPanel ();
        cellCharge.setBackground(Color.WHITE);
        cellCharge.setPreferredSize(new Dimension (LARGEUR_CASE, HAUTEUR_CASE));
        JPanel cellSauvegarde = new JPanel ();
        cellSauvegarde.setBackground(Color.BLACK);
        cellSauvegarde.setPreferredSize(new Dimension (LARGEUR_CASE, HAUTEUR_CASE));
        JPanel cellAnamorphose = new JPanel ();
        cellAnamorphose.setBackground(Color.LIGHT_GRAY);
        cellAnamorphose.setPreferredSize(new Dimension (LARGEUR_CASE, HAUTEUR_CASE));
        JPanel cellFill = new JPanel ();
        cellFill.setBackground(Color.LIGHT_GRAY);
        cellFill.setPreferredSize(new Dimension (LARGEUR_FENETRE - (LARGEUR_CASE * NB_CASES), HAUTEUR_CASE));
        JPanel cellInterface = new JPanel ();
        cellInterface.setBackground(Color.WHITE);
        cellInterface.setPreferredSize(new Dimension (LARGEUR_FENETRE,HAUTEUR_FENETRE - HAUTEUR_CASE));
        
        JPanel fond = new JPanel ();
        fond.setBackground(Color.WHITE);
        fond.setPreferredSize(new Dimension (LARGEUR_FENETRE, HAUTEUR_FENETRE));
        // Creation de la grille.
        fond.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Positionnement par défaut.
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        fond.add(charger, gbc);
        charger.addActionListener(new ChargerImage(fenetre));
        
        gbc.weightx = 0.01;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        fond.add(sauvegarder, gbc);

        gbc.weightx = 0.1;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        fond.add(anamorphose, gbc);
        
        gbc.weightx = 30;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 0;
        fond.add(cellCharge, gbc);
        
        gbc.weightx = 3;
        gbc.weighty = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        fond.add(cellInterface, gbc);
        






        
        // =================================================================
        
        // Ajoute les menus à la barre de menu.
        menuBar.add(menuFichier);
        menuBar.add(menuImage);
        
        // Afficher le bouton pour charger une image.
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
        fenetre.setContentPane(fond);
        fenetre.setVisible(true);
        fenetre.setJMenuBar(menuBar);
    }
}
