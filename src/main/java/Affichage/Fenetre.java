package Affichage;


import Actions.AnamorphoseCylindrique;
import Actions.ChargerImage;
import Actions.QuitterProgramme;
import Actions.SauvegarderImage;
import Actions.ChangementPlan;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Fenetre 
{    
    private static final int HAUTEUR_FENETRE = 110;
    private static final int LARGEUR_FENETRE = 690;
    
    public static void main (String args[])
    {        
        // Creation d'une instance d'image pour sauvegarder l'image chargee.
        Image image = new Image ();
        
        // Creation de la fenetre et du layout.
        JFrame fenetre = new JFrame ("Anamorphose");
        fenetre.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        fenetre.setPreferredSize (new Dimension (LARGEUR_FENETRE, HAUTEUR_FENETRE));
        fenetre.setLayout (new FlowLayout (FlowLayout.CENTER, 15, 10));  

        
        // ======= Ajout des menus =======
        
        JMenuBar menuBar = new JMenuBar ();
        JMenu menuFichier = new JMenu ("Fichiers");
        JMenu menuImage = new JMenu ("Traitement Image");
        
        // Menus Fichier.
        JMenuItem menuItemChargerImage = new JMenuItem ("Charger une image");
        JMenuItem menuItemSauvegarderImage = new JMenuItem ("Sauvegarder une image");
        JMenuItem menuItemExit = new JMenuItem ("Quitter le programme");
        
        // Menu Image.
        JMenuItem menuItemAnamorphoseCylindrique = new JMenuItem ("Anamorphose cylindrique");
        JMenuItem menuItemChangementPlan = new JMenuItem ("Changement de Plan");
        
        // Ajout des menus a la barre de menu.
        menuBar.add (menuFichier);
        menuBar.add (menuImage);
        
        // ======== Gestion des boutons des menus =========
        
        // Ajout des boutons dans le menu fichier.
        // Charger.
        menuItemChargerImage = new JMenuItem ("Charger image", new ImageIcon ("src/main/resources/folder.png"));
        menuFichier.add (menuItemChargerImage);
        menuItemChargerImage.addActionListener (new ChargerImage (image));
        
        // Sauvegarder.
        menuItemSauvegarderImage = new JMenuItem ("Sauvegarder image", new ImageIcon ("src/main/resources/disquette.png"));
        menuFichier.add (menuItemSauvegarderImage);
        menuItemSauvegarderImage.addActionListener (new SauvegarderImage (fenetre));
        
        // Sortir du programme.
        menuItemExit = new JMenuItem ("Quitter le programme", new ImageIcon ("src/main/resources/croix.png"));
        menuFichier.add (menuItemExit);
        menuItemExit.addActionListener (new QuitterProgramme());
        
        // Ajout des boutons dans le menu Traitement d'image.
        // Changement de plan.
        menuItemChangementPlan = new JMenuItem ("Changement de plan", new ImageIcon ("src/main/resources/cube.png"));
        menuImage.add (menuItemChangementPlan);
        menuItemChangementPlan.addActionListener (new ChangementPlan (image));
        
        // Anamorhose cylindrique.
        menuItemAnamorphoseCylindrique = new JMenuItem ("Anamorphose cylindrique", new ImageIcon ("src/main/resources/cylindre.png"));
        menuImage.add (menuItemAnamorphoseCylindrique);
        menuItemAnamorphoseCylindrique.addActionListener (new AnamorphoseCylindrique (image));
        
        // ======== Gestion des boutons ========
                
        // Charger.
        JButton charger = new JButton ("Charger", new ImageIcon ("src/main/resources/folder.png"));
        fenetre.add (charger);
        charger.addActionListener (new ChargerImage (image));
        
        // Sauvegarder.
        JButton sauvegarder = new JButton ("Sauvegarder", new ImageIcon ("src/main/resources/disquette.png"));
        fenetre.add (sauvegarder);
        
        // Changement de plan.
        JButton changementPlan = new JButton ("Changement de plan", new ImageIcon ("src/main/resources/cube.png"));
        fenetre.add (changementPlan);
        changementPlan.addActionListener (new ChangementPlan (image));
                
        // Anamorphose Cylindrique.
        JButton anamorphoseCylindrique = new JButton ("Anamorphose Cylindrique", new ImageIcon ("src/main/resources/cylindre.png"));
        fenetre.add (anamorphoseCylindrique);
        anamorphoseCylindrique.addActionListener (new AnamorphoseCylindrique (image));
        
        // Afficher la fenetre.
        fenetre.pack ();
        fenetre.setVisible (true);
        fenetre.setJMenuBar (menuBar);
    }
}
