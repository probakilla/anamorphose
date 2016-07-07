package Outils;

public class TableauPixels3D 
{
    int _pixels [][][] = null;
    int _width;
    int _height;
    int _depth;
    
    public TableauPixels3D (int xSize, int ySize, int zSize) 
    {
        _width = xSize;
        _height = ySize;
        _depth = zSize;
        
        _pixels = new int [xSize][ySize][zSize];
    }
    
    public int getPixel (int x, int y, int z) 
    {
        return _pixels [x][y][z];
    }
    
    public void setPixel (int x, int y, int z, int pixel)
    {
        _pixels [x][y][z] = pixel;
    }
    
    public int getWidth ()
    {
        return _width;
    }
    
    public int getHeight ()
    {
        return _height;
    }
    
    public int getDepth ()
    {
        return _depth;
    }
}
