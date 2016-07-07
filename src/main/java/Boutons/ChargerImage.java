package Boutons;

import Affichage.Fenetre;
import Affichage.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

public class ChargerImage implements ActionListener 
{    
    Image _image;

    public ChargerImage (Image image) 
    {    
        _image = image;
    }
    
    public void actionPerformed(ActionEvent e) 
    {        
        // recupere l'image l'image
        MBFImage source = null;
        JFileChooser fichier = new JFileChooser();
        fichier.showOpenDialog(null);
        
        // Verrifie si le fichier selectione est une image.
        try 
        {
            source = ImageUtilities.readMBF(fichier.getSelectedFile());
        } 
        
        catch (IOException ex) 
        {
            System.err.printf("Veuillez selectionner une image.");
        }
        
        _image.setImage(ImageUtilities.createBufferedImage(source));
        
        // Affiche l'image choisie.
        DisplayUtilities.display(source);
    }
}
