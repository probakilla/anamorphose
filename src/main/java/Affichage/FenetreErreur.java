package Affichage;

import Actions.QuitterFenetre;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreErreur
{
    private static final int HAUTEUR_FENETRE = 150;
    private static final int LARGEUR_FENETRE = 250;
    private JFrame _erreur;
    private String _message;
    
    // Permet de choisir le message d'erreur ainsi que le nom de la fenetre lors de son instanciation.
    public FenetreErreur (String message, String nomErreur)
    {
        _message = message;
        _erreur = new JFrame (nomErreur);
    }
    
    public void afficherErreur ()
    {
        // Creer la fenetre d'erreur et lui donne ses dimensions et lui attribue un Layout.
        _erreur.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _erreur.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
        _erreur.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        // Ajoute le message d'erreur dans la fenetre.
        JLabel messageErreur = new JLabel (_message);
        _erreur.add(messageErreur);
        
        // Ajoute un bouton pour quitter la fenetre.
        JButton quitter = new JButton ("Quitter");
        quitter.addActionListener(new QuitterFenetre(_erreur));
        _erreur.add(quitter);
        
        // Affiche la fenetre d'erreur.
        _erreur.pack();
        _erreur.setLocationRelativeTo(null); // Affiche au milieu de l'ecran.
        _erreur.setVisible(true);
    }
}
