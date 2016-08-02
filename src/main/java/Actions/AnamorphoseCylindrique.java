package Actions;

import Outils.Image;
import Outils.TableauPixels3D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import org.openimaj.image.DisplayUtilities;

public class AnamorphoseCylindrique implements ActionListener
{
    Image _image = null;
    
    public AnamorphoseCylindrique (Image image)
    {
        _image = image;
    }
    
    private int nouveauX (double angle, int x, int y, int xc, int yc)
    {
        return (int) (((Math.cos(angle) * (x - xc)) - (Math.sin(angle) * (y - yc))) + xc);
    }
    
    private int nouveauY (double angle, int x, int y, int xc, int yc)
    {
        return (int) (((Math.sin(angle) * (x - xc)) - (Math.cos(angle) * (y - yc))) + yc);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        // Recupere l'image.
        BufferedImage anamorphose = _image.getImage();
        
        // Recupere la taille de l'image.
        int hauteur = anamorphose.getHeight();
        int largeur = anamorphose.getWidth();
        int diametre = (int) ((largeur * 2) / Math.PI);
        int rayon = (int) (diametre / 2);
        int hauteurArrivee = hauteur + rayon;
        int largeurArrivee = diametre + (2 * hauteur);
        
        /*
        * Creation du tableau celon les dimensions de l'image et du cylindre (fait en fonction de l'image).
        * Pour la hauteur du tableau 3D rien ne change.
        * Pour la largeur il faut prendre le diametre du cylindre plus deux fois la hauteur de l'image.
        * Pour la profondeur, il suffit juste de prendre la moitie du diametre et de rajouter la hauteur de l'image.
        * La hauteur de l'image d'arrivee correspond a la profondeur du tableau et sa largeur est la meme.
        */
        
        TableauPixels3D tableau3D = new TableauPixels3D(largeurArrivee, hauteur, hauteurArrivee) ;
        BufferedImage imgArrive = new BufferedImage (largeurArrivee, hauteurArrivee, TYPE_INT_RGB);
        
        /*
        * On trace un cercle grace a l'algorithme de Bresenham.
        * Cet algorithme trace un octant de cercle,
        * pour tracer un cercle complet (ou ici un demi cercle),
        * il nous suffit juste de tracer les autres octants grace a une symetrie.
        */
        
        // Variable temporaire.
        int RGBtmp = 200; // On place une valeur fixe dans la variable RGBtmp une valeure arbitraire pour voir si l'algorithme fonctionne.
        double angle = Math.PI * (180 / (largeur - 1)) / 180 ; // Transformation en radian.
        int centreImage = largeur / 2; // Correspond au centre de l'image chargée.
        int centre = tableau3D.getWidth() / 2; // Correspond au centre de l'image de sortie.
        
        // On place d'abord placer les premiers points de chaque octant de cercle.
        int x, z, d;
        double a, b;
        // Variables utilisees pour garder en memoire la valeur des coordonnes des points de l'ancien tour de boucle.
        int x1Pre, y1Pre, x2Pre, y2Pre, x3Pre, y3Pre, x4Pre, y4Pre;
        
        /*
        * On commence par initialiser les variables nécessaires pour dessiner le cercle.
        */
        x = 0;
        z = rayon;
        d = 1 - rayon;
        // Place le premier point du premier quart de l'image.
        tableau3D.setPixel(centre - rayon, 0, 0, RGBtmp);
        // Trace la première ligne droite.
        for (int xTmp = (centre - rayon); xTmp >= ((centre - rayon) - hauteur); xTmp --) 
            tableau3D.setPixel(xTmp, 0, 0, RGBtmp);
        // sauvegarde  les coordonnées du point pour le prochain passage de boucle
        x1Pre = ((centre - rayon) - hauteur);
        y1Pre = 0;
        
        // Place le premier point du deuxieme quart de l'image.
        tableau3D.setPixel(centre, 0, rayon, RGBtmp);
        // Trace la deuxieme ligne droite.
        for (int yTmp = rayon; yTmp < (rayon + hauteur); yTmp ++) 
            tableau3D.setPixel(centre, 0, yTmp, RGBtmp);
        x2Pre = centre;
        y2Pre = (rayon + hauteur);

        
        // Place le premier point du troisieme quart de l'image.
        tableau3D.setPixel(centre + 1, 0, rayon, RGBtmp);
        // Trace la troiseme ligne droite
        for (int yTmp = rayon; yTmp < (rayon + hauteur); yTmp ++) 
            tableau3D.setPixel(centre + 1, 0, yTmp, RGBtmp);
        x3Pre = centre + 1;
        y3Pre = (rayon + hauteur);
        
        // Place le premier point du quatrieme quart de l'image.
        tableau3D.setPixel(centre + rayon, 0, 0, RGBtmp);
        // Trace la quatrieme ligne droite.
        for (int xTmp = (centre + rayon); xTmp < ((centre + rayon) + hauteur); xTmp ++)
            tableau3D.setPixel(xTmp, 0, 0, RGBtmp);
        x4Pre = ((centre + rayon) + hauteur);
        y4Pre = 0;

        
        // On dessine ensuite le reste des onctants avec l'algorithme de Bresenham.
        while (z > x) 
        {
            if (d < 0) {
                d += 2 * x + 3;
            }
            else {
                d += 2 * (x - z) + 5;
                z --;
            }
            x ++;
            
            // Maintenant on place les droites de chaque octant.
            
            // Place le pixel du premier quart.
            tableau3D.setPixel((centre - x), 0, z, RGBtmp);
            int nouveauX = nouveauX (angle, x1Pre, y1Pre, centre, 0); // Décale les coordonnees du point le plus éloigné du cercle pour tracer la droite.
            int nouveauY = nouveauY (angle, x1Pre, y1Pre, centre, 0);
            // Calcule les variables necessaires pour tracer la droite.
            a = (double) ((nouveauY - z) / (nouveauX - (centre - x))); 
            b = z - (a * (centre - x));
            for (int xTmp = (centre - x); xTmp < nouveauX; x --) 
            {
                // Trace la droite grâce à l'algorithme de Bresenham qui utilise l'équation d'une droite affine.
                int yTmp = (int) ((a * xTmp) + b);
                tableau3D.setPixel(xTmp, 0, yTmp, RGBtmp);
            }
            x1Pre = nouveauX;
            y1Pre = nouveauY;
            
            // On répète l'action pour les autres octants.
            
            // Place le pixel du deuxieme quart.
            tableau3D.setPixel((centre - z), 0, x, RGBtmp);
    
            // Place le pixel du troisieme quart.
            tableau3D.setPixel((centre + x), 0, z, RGBtmp);
            nouveauX = nouveauX (angle, x3Pre, y3Pre, centre, 0);
            nouveauY = nouveauY (angle, x3Pre, y3Pre, centre, 0);
            //a = (double) ((nouveauY - z) / (nouveauX - (centre + x)));
            b = z - (a * (centre + x));
            for (int xTmp = x; xTmp < nouveauX; xTmp++) 
            {
                int yTmp = (int) ((a * xTmp) + b);
                tableau3D.setPixel(xTmp, 0, yTmp, 200);
            }
            x3Pre = nouveauX;
            y3Pre = nouveauY;
            
            // Place le pixel du quatrieme quart.
            tableau3D.setPixel((centre + z), 0, x, RGBtmp);
            
        }
        
        // Remplie la nouvelle image avec celle transforee.
        for (int zt = 0; zt < hauteurArrivee; zt ++)
        {
            for (int xt = 0; xt < largeurArrivee; xt ++) 
            {
                RGBtmp = tableau3D.getPixel(xt, 0, zt);
                imgArrive.setRGB(xt, zt, RGBtmp);
            }
        }
        
        // Affiche l'image choisie.
        DisplayUtilities.display(imgArrive);
    }
}