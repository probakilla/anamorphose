package Actions;

import static org.junit.Assert.*;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

import org.junit.Test;

import Outils.Image;

public class ChangementPlanTest 
{
	// Dimensions de l'image
	private final int HAUTEUR_IMAGE = 200;
	private final int LARGEUR_IMAGE = 200;
	
	// Coordonnees des points qui serviront a tester.
	private final int X_PREMIER_PIXEL = LARGEUR_IMAGE - 50;
	private final int Y_PREMIER_PIXEL = HAUTEUR_IMAGE - 10;
	private final int X_DEUXIEME_PIXEL = LARGEUR_IMAGE - 1;
	private final int Y_DEUXIEME_PIXEL = HAUTEUR_IMAGE - 1;
	private final int HAUTEUR_LIGNE = HAUTEUR_IMAGE - 149;
	
	// Couleur des pixels et de la ligne.
	private final int COULEUR_PREMIER_PIXEL = 150;
	private final int COULEUR_DEUXIEME_PIXEL = 30;
	private final int COULEUR_LIGNE = 196;

	private BufferedImage initialiserImage () 
	{
		BufferedImage tmp = new BufferedImage (LARGEUR_IMAGE, HAUTEUR_IMAGE, TYPE_INT_RGB);
		// Placement des pixels.
		tmp.setRGB (X_PREMIER_PIXEL, Y_PREMIER_PIXEL, COULEUR_PREMIER_PIXEL);
		tmp.setRGB (X_DEUXIEME_PIXEL, Y_DEUXIEME_PIXEL, COULEUR_DEUXIEME_PIXEL);
		
		// Placement de la ligne.
		for (int x = 0; x < LARGEUR_IMAGE; x ++)
			tmp.setRGB (x, HAUTEUR_LIGNE, COULEUR_LIGNE);
		
		return tmp;
	}
	
	@Test
	public void test() 
	{
		// Initialisation de l'image de test avec la classe Image.
		BufferedImage test = initialiserImage ();
		Image imageClasse = new Image ();
		imageClasse.setImage (test);
		
		// Creation du bouton a tester.
		JButton changementPlan = new JButton ("Changement de plan");
		changementPlan.addActionListener (new ChangementPlan (imageClasse));
		changementPlan.doClick();
		
		// Verrification de l'image creee.
		BufferedImage imageArrivee = imageClasse.getImage (); // On recupere l'image creee.
		// Verrification des pixels.
		assertEquals (COULEUR_PREMIER_PIXEL, imageArrivee.getRGB (X_PREMIER_PIXEL, Y_PREMIER_PIXEL));
		assertEquals (COULEUR_DEUXIEME_PIXEL, imageArrivee.getRGB (X_DEUXIEME_PIXEL, Y_DEUXIEME_PIXEL));
		// Verrification de la ligne.
		for (int x = 0; x < LARGEUR_IMAGE; x ++) {
			assertEquals (COULEUR_LIGNE, imageArrivee.getRGB(x, HAUTEUR_LIGNE));
		}
	}
}