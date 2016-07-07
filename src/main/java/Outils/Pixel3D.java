package Outils;

public class Pixel3D 
{
    
    private int _red;
    private int _green;
    private int _blue;
    
    public Pixel3D (int red, int green, int blue)
    {        
        _red = red;
        _green = green;
        _blue = blue;
    }
    
    public int getRed ()
    {
        return _red;
    }
    
    public int getGreen () 
    {
        return _green;
    }
    
    public int getBlue ()
    {
        return _blue;
    }
    
    @Override
    public Pixel3D clone () 
    {
        return new Pixel3D (_red, _green, _blue);
    }
}
