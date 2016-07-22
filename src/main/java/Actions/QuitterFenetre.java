package Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class QuitterFenetre implements ActionListener
{    
    JFrame _nomFenetre;
    
    // Prend en parametre la fenetre que l'on veut fermer.
    public QuitterFenetre (JFrame nomFenetre) 
    {
        _nomFenetre = nomFenetre;
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        _nomFenetre.dispose(); // Permet de fermer la fenetre voulue.
    }
}
