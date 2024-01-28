package model;

public class TrianglesFactory
{
    public static Triangles getTriangle(int x, int y)
    {
        int imgNum = (int) (Math.random() * 5 + 1); //shape color
        return new Triangles(x, y, "triangles" + imgNum + ".png");
    }

}
