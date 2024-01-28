package model;

import view.Circus;

public class ShapeFactory
{
    private int random;
    private static ShapeFactory instance; //Singleton
    private Circus circus;

    private ShapeFactory(Circus circus)
    {
        this.circus = circus;
    }

    public static ShapeFactory getInstance(Circus circus)
    {
        if(instance == null)
        {
            instance = new ShapeFactory(circus);
        }
        return instance;
    }

    public Shape getShape()
    {
        random = (int) (Math.random() * 3 + 1);  //plates rect tri
        int x = (int) (Math.random() * circus.getWidth());   //width of circusWorld
        int y = (int) (Math.random() * (circus.getHeight()) );  //Height of circusWorld
        switch (random)
        {
            case 1 ->
            {
                return PlatesFactory.getPlate(x, y);
            }
            case 2 ->
            {
                return RectanglesFactory.getRectangle(x, y);
            }
            case 3 ->
            {
                return TrianglesFactory.getTriangle(x, y);
            }
        }
        return null;
    }

}
