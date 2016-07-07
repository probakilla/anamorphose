package Boutons;

import Affichage.Image;
import Outils.TableauPixels3D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class AnamorphoseCylindrique implements ActionListener
{
    Image _image = null;
    
    public AnamorphoseCylindrique (Image image)
    {
        _image = image;
    }
    
    public void actionPerformed(ActionEvent e)
    { 
        // Recupere l'image.
        BufferedImage anamorphose = _image.getImage();
        
        // Recupere la taille de l'image.
        int height = anamorphose.getHeight();
        int width = anamorphose.getWidth();
        int diametre = (int) ((width * 2) / Math.PI);
        int rayon = (int) (diametre / 2);

        /*
        * Creation du tableau celon les dimensions de l'image et du cylindre (fait en fonction de l'image).
        * Pour la hauteur du tableau 3D rien ne change.
        * Pour la largeur il faut prendre le diametre du cylindre plus deux fois la hauteur de l'image.
        * Pour la profondeur, il suffit juste de prendre la moitie du diametre et de rajouter la hauteur de l'image.
        * La hauteur de l'image d'arrivee correspond a la profondeur du tableau et sa largeur est la meme.
        */
        TableauPixels3D tableau3D = new TableauPixels3D(diametre  + 2 * width, height, height + (int) (diametre / 2));
        BufferedImage imgArrive = new BufferedImage (diametre + 2 * width, height + (int) (diametre / 2), TYPE_INT_RGB); 
                   
        // Variable temporaire.
        int RGBtmp;
        int centre = tableau3D.getWidth() / 2;
        
        
        for (int y = 0; y < height; y ++) {
            for (int x = 0; x < width; x ++) {
                RGBtmp = imgArrive.getRGB(x, y);
                tableau3D.setPixel(x, y, 0, x);
            }
        }
    }
}
