package model;

public class RectanglesFactory
{
    public static Rectangles getRectangle(int x, int y)
    {
        int imgNum = (int) (Math.random() * 5 + 1); //shape color
        return new Rectangles(x, y, "rectangles" + imgNum + ".png");
    }

}
