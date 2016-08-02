package Actions;

import Affichage.FenetreErreur;
import Outils.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

public class ChargerImage implements ActionListener 
{    
    /*
    * Doit accueillir la classe Image instanciée par la fenêtre principale pour
    * avoir accès à l'image que l'on veut modifier dans d'autres classes.
    */
    Image _image;

    public ChargerImage (Image image) 
    {    
        _image = image;
    }
    
    public void actionPerformed (ActionEvent e) 
    {        
        // recupere l'image l'image
        MBFImage source = null;
        JFileChooser fichier = new JFileChooser (); 
        fichier.showOpenDialog (null); // Affiche une fenêtre pour choisir une image.
        
        // Verrifie si le fichier selectione est une image. Affiche une fenetre d'erreur si ça n'est pas le cas.
        try 
        {
            source = ImageUtilities.readMBF (fichier.getSelectedFile ()); // Source prend l'image choisie par l'utilisateur.
        } 
        catch (IOException ex) 
        {
            // Si le fichier choisi n'et pas une image, une fenetre d'erreur apparait.
            FenetreErreur erreur = new FenetreErreur ("Ceci n'est pas une image.", "Erreur");
            erreur.afficherErreur ();
        }
        // Sauvegarde l'image que l'on veut modifier.
        _image.setImage (ImageUtilities.createBufferedImage (source));
        
        // Affiche l'image choisie.
        DisplayUtilities.display (source);
    }
}
