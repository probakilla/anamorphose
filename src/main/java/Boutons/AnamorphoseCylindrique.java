package Boutons;

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
    
    public void actionPerformed(ActionEvent e)
    {
        // Recupere l'image.
        BufferedImage anamorphose = _image.getImage();
        
        // Recupere la taille de l'image.
        int height = anamorphose.getHeight();
        int width = anamorphose.getWidth();
        int diametre = (int) ((width * 2) / Math.PI);
        int rayon = (int) (diametre / 2);
        int heightArrivee = height + rayon;
        int widthArrivee = diametre + (2 * width);
        
        /*
        * Creation du tableau celon les dimensions de l'image et du cylindre (fait en fonction de l'image).
        * Pour la hauteur du tableau 3D rien ne change.
        * Pour la largeur il faut prendre le diametre du cylindre plus deux fois la hauteur de l'image.
        * Pour la profondeur, il suffit juste de prendre la moitie du diametre et de rajouter la hauteur de l'image.
        * La hauteur de l'image d'arrivee correspond a la profondeur du tableau et sa largeur est la meme.
        */
        
        TableauPixels3D tableau3D = new TableauPixels3D(widthArrivee, height, heightArrivee) ;
        BufferedImage imgArrive = new BufferedImage (widthArrivee, heightArrivee, TYPE_INT_RGB);
        
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
        int centreImage = width / 2;
        int centre = tableau3D.getWidth() / 2;
        
        // On place d'abord placer les premiers points de chaque octant de cercle.
        for (int y = 0; y < height; y ++) {
            int x, z, d, quart1, quart2, quart3, quart4;
            double a, b;
            x = 0;
            z = rayon;
            d = 1 - rayon;
            quart1 = 0;
            quart2 = centreImage;
            quart3 = centreImage + 1;
            quart4 = width - 1;
            // Place le premier point du premier quart de l'image.
            RGBtmp = anamorphose.getRGB(quart1++, y);
            tableau3D.setPixel(centre - rayon, y, 0, RGBtmp);
            tableau3D.setPixel((centre - rayon) - y, 0, 0, RGBtmp);
            // Place le premier point du deuxieme quart de l'image.
            RGBtmp = anamorphose.getRGB(quart2--, y);
            tableau3D.setPixel(centre, y, rayon, RGBtmp);
            tableau3D.setPixel(centre, 0, y + rayon, RGBtmp);
            // Place le premier point du troisieme quart de l'image.
            RGBtmp = anamorphose.getRGB(quart3++, y);
            tableau3D.setPixel(centre + 1, y, rayon, RGBtmp);
            tableau3D.setPixel(centre + 1, 0, y + rayon, RGBtmp);
            // Place le premier point du quatrieme quart de l'image.
            RGBtmp = anamorphose.getRGB(quart4--, y);
            tableau3D.setPixel(centre + rayon, y, 0, RGBtmp);
            tableau3D.setPixel(centre + rayon + y, 0, 0, RGBtmp);
            
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
                
                
                int zTmp;
                // Place le pixel du premier quart.
                RGBtmp = anamorphose.getRGB(quart1++, y);
                tableau3D.setPixel(centre - z, y, x, RGBtmp);
                a = (double) ((0 - x) / (centre - (centre - z)));
                b = x - (a * (centre - z));
                zTmp = (int) (a * (centre + x) + b);
                // tableau3D.setPixel(centre - z, 0, y + zTmp, RGBtmp);
                
                // Place le pixel du deuxieme quart.
                RGBtmp = anamorphose.getRGB(quart2--, y);
                tableau3D.setPixel(centre - x, y, z, RGBtmp);
                a = (double) ((0 - z) / (centre - (centre - x)));
                b = z - (a * (centre - x));
                zTmp = (int) (a * (centre + x) + b);
                tableau3D.setPixel(centre - x, 0, zTmp + rayon, RGBtmp);
                
                // Place le pixel du troisieme quart.
                RGBtmp = anamorphose.getRGB(quart3++, y);
                tableau3D.setPixel(centre + x, y, z, RGBtmp);
                a = (double) ((z - 0) / ((centre + x) - centre));
                b = 0 - (a * centre);
                zTmp = (int) (a * (centre + x) + b);
                // tableau3D.setPixel(centre + x, 0, zTmp + y, RGBtmp);
                
                // Place le pixel du quatrieme quart.
                RGBtmp = anamorphose.getRGB(quart4--, y);
                tableau3D.setPixel(centre + z, y, x, RGBtmp);
                a = (double) ((x - 0) / ((centre + z) - centre));
                b = 0 - (a * (centre));
                zTmp = (int) (a * (centre + x) + b);
                //tableau3D.setPixel(centre + z, 0, zTmp + x, RGBtmp);
            }
        }
        // Remplie la nouvelle image avec celle transforee.
        for (int z = 0; z < heightArrivee; z ++) {
            for (int x = 0; x < widthArrivee; x ++) {
                RGBtmp = tableau3D.getPixel(x, 0, z);
                imgArrive.setRGB(x, z, RGBtmp);
            }
        }
        
        // Affiche l'image choisie.
        DisplayUtilities.display(imgArrive);
    }
}
