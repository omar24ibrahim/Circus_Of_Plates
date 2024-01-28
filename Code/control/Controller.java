package control;

import eg.edu.alexu.csd.oop.game.GameObject;
import model.*;
import view.Circus;
import java.util.Iterator;
import java.util.List;

public class Controller
{
    private static Controller instance; //Singleton
    private Clown clown ;
    private Background background;
    private  Circus circus;
    private ShapePool pool;
    private Controller(String path)
    {
        background = new Background(0, 0, path);
        clown = new Clown(395, 440, "clown.png");
    }

    public Clown getClown()
    {
        return this.clown;
    }

    public Background getBackground()
    {
        return this.background;
    }

    public static Controller getInstance(String path)
    {
        if(instance == null)
        {
            instance = new Controller(path);
        }
        return instance;
    }

    public void setCircus(Circus circus)
    {
        this.circus = circus;
    }

    public void setPool()
    {
        pool = ShapePool.getInstance(circus);
    }

    public ShapePool getPool()
    {
        return pool;
    }

    public boolean checkIntersection ()
    {
        int height = circus.getHeight();
        int width = circus.getWidth();
        List<GameObject> controlled = circus.getControlableObjects();
        List<GameObject> moving = circus.getMovableObjects();
        List<GameObject> constant = circus.getConstantObjects();
        Iterator<GameObject> itrMoving = moving.iterator();
        while(itrMoving.hasNext())
        {
            GameObject o = itrMoving.next();
            if (clown.withinLeftStack(o))
            {
                o.setY(height - 85 - clown.getNumOfLeftPlates()*o.getHeight());
                Shape g = ((Shape)o).clone();
                g.setType(-1);
                g.setX(clown.getX()+14);
                controlled.add(g);
                if(o instanceof Bomb) //bomb
                {
                    constant.add(new Background(300, 100, "bombCollision.png"));
                    MusicCtrl m = new MusicCtrl();
                    m.runMusic("bomb.au");
                    return false;
                }
                pool.shufflePosition((Shape)o);
                pool.shuffleShape((Shape)o);
                clown.updateLeftStack((Shape) g, controlled);
            }
            else if (clown.withinRightStack(o))
            {
                o.setY(height - 100 - clown.getNumOfRightPlates()*o.getHeight());
                Shape g = ((Shape)o).clone();
                g.setType(1);
                g.setX(clown.getX()+145);
                controlled.add(g);
                if(o instanceof Bomb) //bomb
                {
                    constant.add(new Background(300, 100, "bombCollision.png"));
                    MusicCtrl m = new MusicCtrl();
                    m.runMusic("bomb.au");
                    return false;
                }
                pool.shufflePosition((Shape)o);
                pool.shuffleShape((Shape)o);
                clown.updateRightStack((Shape) g,controlled);
            }
            else
            {
                if (o.getY() == height) //reusability
                {
                    pool.shufflePosition((Shape) o);
                }
            }
            update((ImageItem)o);
        }
        return true;
    }

    public void update(ImageItem g)
    {
        //continous movement of falling shapes
        int x = g.getX();
        int y = g.getY();
        g.move(x + 1, y + 1);
    }

    public int getScore()
    {
        return clown.getScore();
    }

}
