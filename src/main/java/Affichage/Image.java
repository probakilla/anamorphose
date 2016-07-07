package Affichage;

import java.awt.image.BufferedImage;

public class Image {

    public BufferedImage _image;
    
    public Image () {
        _image = null;
    }
    
    public void setImage (BufferedImage image) {
        _image = image;
    }
    
    public BufferedImage getImage (){
        return _image;
    }
}
