package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import view.Circus;
import java.util.LinkedList;
import java.util.List;

public class ShapePool
{
    private static ShapePool instance; //Singleton
    private static ShapeFactory factory;
    private static Circus circus;
    private static List<GameObject> shapes;

    private ShapePool(Circus circus)
    {
        this.circus = circus;
        this.factory = ShapeFactory.getInstance(circus);
        this.shapes = new LinkedList<>();
        setPool();
    }

    public static ShapePool getInstance(Circus circus)
    {
        if(instance == null)
        {
            instance = new ShapePool(circus);
        }
        else
        {   
            ShapePool.circus = circus;
            setPool();
        }
        return instance;
    }

    public static void setPool()
    {
        shapes.clear();
        for(int i = 0; i < circus.getNumberOfFallingShapes(); i ++)
        {
            shapes.add(factory.getShape());
        }
    }

    public List<GameObject> getShapes()
    {
        return shapes;
    }

    public void shuffleShape(Shape s)
    {
        s = factory.getShape();
    }

    public void shufflePosition(Shape s)
    {
        //reusability
        s.setY(-1 * (int) (Math.random() * circus.getHeight()));
        s.finalSetX((int) (Math.random() * circus.getWidth()));  //used to randomise the x co-ordinate of the falling shape surpasing being of vertical state
    }

}
