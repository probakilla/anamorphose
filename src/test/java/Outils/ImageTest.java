package Outils;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import org.junit.Test;

public class ImageTest 
{

	// Dimensions de l'image
	private final int HAUTEUR_IMAGE = 200;
	private final int LARGEUR_IMAGE = 200;
	
	private final int COULEUR_PIXEL = 150;
	
	private final int X_PIXEL = 50;
	private final int Y_PIXEL = 10;
	
	@Test
	public void test() 
	{
		BufferedImage image = new BufferedImage (LARGEUR_IMAGE, HAUTEUR_IMAGE, TYPE_INT_RGB);
		image.setRGB(X_PIXEL, Y_PIXEL, COULEUR_PIXEL);
		Image classeImage = new Image ();
		classeImage.setImage(image);
		
		BufferedImage imageTest = classeImage.getImage();
		
		assertEquals(COULEUR_PIXEL, imageTest.getRGB(X_PIXEL, Y_PIXEL));
	}

}
