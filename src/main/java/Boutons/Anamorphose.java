package Boutons;

import Affichage.Image;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Anamorphose implements ActionListener 
{    
    Image _image = null;
    
    public Anamorphose (Image image) 
    {        
        _image = image;
    }

    public void actionPerformed(ActionEvent e) 
    {       
        // Creer l'image 3D et recupere l'image de depart.
        BufferedImage anamorphose = _image.getImage();        
    }
}
