package model;

public class PlatesFactory
{
    public static Plates getPlate(int x, int y)
    {
        int imgNum = (int) (Math.random() * 5 + 1); //shape color
        return new Plates(x, y, "plates" + imgNum + ".png");
    }

}
