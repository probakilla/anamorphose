package Actions;

import Affichage.Image;
import Outils.TableauPixels3D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.time.Clock;
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
        * On trace un cercle grace a l'algorithme de Bersenham.
        * Cet algorithme trace un octant de cercle,
        * pour tracer un cercle complet (ou ici un demi cercle),
        * il nous suffit juste de tracer les autres octants grace a une symetrie.
        * Pour faire correspondre l'image au cercle dessine nous divisions notre
        * image en quatre ce qui nous donne quatre variables (quart1, quart2 etc...)
        * qui nous servent a placer les pixels de chaque quart de l'image.
        */
        
        // Variable temporaire.
        int RGBtmp;
        double angle = 180 / (largeur - 1);
        int centreImage = largeur / 2;
        int centre = tableau3D.getWidth() / 2;
        
        // On place d'abord placer les premiers points de chaque octant de cercle.
        //for (int y = 0; y < hauteur; y ++) {
        int x, z, d, quart1, quart2, quart3, quart4;
        double a, b;
        // Variables utilisees pour garder en memoire la valeur des coordonnes des points de l'ancien tour de boucle.
        int x1Pre, y1Pre, x2Pre, y2Pre, x3Pre, y3Pre, x4Pre, y4Pre;
        
        x = 0;
        z = rayon;
        d = 1 - rayon;
        quart1 = 0;
        quart2 = centreImage;
        quart3 = centreImage + 1;
        quart4 = largeur - 1;
        // Place le premier point du premier quart de l'image.
        RGBtmp = anamorphose.getRGB(quart1, 0);
        tableau3D.setPixel(centre - rayon, 0, 0, RGBtmp);
        // Trace la premi�re ligne droite.
        for (int xTmp = (centre - rayon); xTmp >= ((centre - rayon) - hauteur); xTmp --) {
            int tmp = 1; // Variable necessaire pour la couleur d'une colonne.
            RGBtmp = anamorphose.getRGB(quart1, tmp++);
            tableau3D.setPixel(xTmp, 0, 0, RGBtmp);
        }
        x1Pre = ((centre - rayon) - hauteur);
        y1Pre = 0;
        quart1++;
        
        // Place le premier point du deuxieme quart de l'image.
        RGBtmp = anamorphose.getRGB(quart2, 0);
        tableau3D.setPixel(centre, 0, rayon, RGBtmp);
        // Trace la deuxieme ligne droite.
        for (int yTmp = rayon; yTmp < (rayon + hauteur); yTmp ++) {
            int tmp = 1;
            RGBtmp = anamorphose.getRGB(quart2, tmp++);
            tableau3D.setPixel(centre, 0, yTmp, RGBtmp);
        }
        x2Pre = centre;
        y2Pre = (rayon + hauteur);
        quart2--;
        
        // Place le premier point du troisieme quart de l'image.
        RGBtmp = anamorphose.getRGB(quart3, 0);
        tableau3D.setPixel(centre + 1, 0, rayon, RGBtmp);
        // Trace la troiseme ligne droite
        for (int yTmp = rayon; yTmp < (rayon + hauteur); yTmp ++) {
            int tmp = 1;
            RGBtmp = anamorphose.getRGB(quart3, tmp++);
            tableau3D.setPixel(centre + 1, 0, yTmp, RGBtmp);
        }
        x3Pre = centre + 1;
        y3Pre = (rayon + hauteur);
        quart3++;
        
        // Place le premier point du quatrieme quart de l'image.
        RGBtmp = anamorphose.getRGB(quart4, 0);
        tableau3D.setPixel(centre + rayon, 0, 0, RGBtmp);
        // Trace la quatrieme ligne droite.
        for (int xTmp = (centre + rayon); xTmp < ((centre + rayon) + hauteur); xTmp ++) {
            int tmp = 1; // Variable necessaire pour la couleur d'une colonne.
            RGBtmp = anamorphose.getRGB(quart4, tmp++);
            tableau3D.setPixel(xTmp, 0, 0, RGBtmp);
        }
        x4Pre = ((centre + rayon) + hauteur);
        y4Pre = 0;
        quart4++;
        
        // On dessine ensuite le reste des onctants.
        while (z > x) {
            if (d < 0) {
                d += 2 * x + 3;
            }
            else {
                d += 2 * (x - z) + 5;
                z --;
            }
            x ++;
            
            // Place le pixel du premier quart.
            tableau3D.setPixel((centre - x), 0, z, RGBtmp);
            int nouveauX = nouveauX (angle, x1Pre, y1Pre, centre, 0);
            int nouveauY = nouveauY (angle, x1Pre, y1Pre, centre, 0);
            a = (double) ((nouveauY - z) / (nouveauX - (centre - x)));
            b = z - (a * (centre - x));
            for (int xTmp = (centre - x); xTmp < nouveauX; x --) {
                int tmp = 1;
                int yTmp = (int) ((a * xTmp) + b);
                RGBtmp = 200;
                tableau3D.setPixel(xTmp, 0, yTmp, RGBtmp);
            }
            x1Pre = nouveauX;
            y1Pre = nouveauY;
            
            // Place le pixel du deuxieme quart.
            tableau3D.setPixel((centre - z), 0, x, RGBtmp);
            
            // Place le pixel du troisieme quart.
            tableau3D.setPixel((centre + x), 0, z, RGBtmp);
            nouveauX = nouveauX (angle, x3Pre, y3Pre, centre, 0);
            nouveauY = nouveauY (angle, x3Pre, y3Pre, centre, 0);
            //a = (double) ((nouveauY - z) / (nouveauX - (centre + x)));
            b = z - (a * (centre + x));
            for (int xTmp = x; xTmp < nouveauX; xTmp++) {
                int yTmp = (int) ((a * xTmp) + b);
                tableau3D.setPixel(xTmp, 0, yTmp, 200);
            }
            x3Pre = nouveauX;
            y3Pre = nouveauY;
            
            // Place le pixel du quatrieme quart.
            tableau3D.setPixel((centre + z), 0, x, RGBtmp);
            
        }
        
        // Remplie la nouvelle image avec celle transforee.
        for (int zt = 0; zt < hauteurArrivee; zt ++) {
            for (int xt = 0; xt < largeurArrivee; xt ++) {
                RGBtmp = tableau3D.getPixel(xt, 0, zt);
                imgArrive.setRGB(xt, zt, RGBtmp);
            }
        }
        
        // Affiche l'image choisie.
        DisplayUtilities.display(imgArrive);
    }
}