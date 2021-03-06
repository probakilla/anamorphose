package Actions;

import Outils.Image;
import Outils.TableauPixels3D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import org.openimaj.image.DisplayUtilities;

public class ChangementPlan implements ActionListener 
{    
    Image _image = null;
    
    public ChangementPlan (Image image) 
    {        
        _image = image;
    }

    public void actionPerformed(ActionEvent e) 
    {       
        // Creer l'image 3D et recupere l'image de depart.
        BufferedImage anamorphose = _image.getImage();
        
        // recupere la taille de l'image.
        int hauteur = anamorphose.getHeight();
        int longueur = anamorphose.getWidth();
        
        // Variable temporaire.
        int RGBtmp;
        
        // Creation du tableau 3D et l'image d'arrivee de la transformation.
        TableauPixels3D tableau3D = new TableauPixels3D(longueur, hauteur, hauteur);
        BufferedImage imgArrive = new BufferedImage (longueur, hauteur, TYPE_INT_RGB);
        
        // Place l'iamge dans un tableau 3D.
        for (int y = 0; y < hauteur; y ++) {
            for (int x = 0; x < longueur; x ++) {
                RGBtmp = anamorphose.getRGB(x, y);
                tableau3D.setPixel(x, y, 0, RGBtmp);
            }
        }
        // Change l'image de x,y en x,z.
        for (int y = 0; y < hauteur; y ++) {
            for (int x = 0; x < longueur; x ++) {
                RGBtmp = tableau3D.getPixel(x, y, 0);
                tableau3D.setPixel(x, 0, y, RGBtmp);
            }
        }
        // Remplie la nouvelle image avec celle transforee.
        for (int z = 0; z < hauteur; z ++) {
            for (int x = 0; x < longueur; x ++) {
                RGBtmp = tableau3D.getPixel(x, 0, z);
                imgArrive.setRGB(x, z, RGBtmp);
            }
        }
        // Affiche l'image choisie.
        _image.setImage(imgArrive);
        DisplayUtilities.display(imgArrive);
    }
}
