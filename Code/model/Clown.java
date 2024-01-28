package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.*;
import java.util.List;
import java.util.Stack;

public class Clown extends ImageItem
{
    private Stack<Shape> leftStack, rightStack;  //added to hold the collected shapes
    private Point leftStackPos, rightStackPos;
    private int numOfLeftPlates = 0, numOfRightPlates = 0;
    ImageItem imageObject;
    private  int score = 0;

    public Clown(int x, int y, String imagePath)
    {
        super(x, y, imagePath, new Horrizontal());
        leftStack = new Stack<Shape>();
        rightStack = new Stack<Shape>();
        score = 0;
        numOfLeftPlates = 0;
        numOfRightPlates = 0;
        this.leftStackPos = new Point();
        this.rightStackPos = new Point();
    }

    public int getScore()
    {
        return score;
    }

    //added to implement collision detection
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(getLeftStackPoint().x, getLeftStackPoint().y - numOfLeftPlates*imageObject.getHeight(), 32, 2);
    }

    public void setLeftStackPos()
    {
        this.leftStackPos = new Point(getX()+32, getY()+ 50);
    }

    public void setRightStackPos()
    {
        this.rightStackPos = new Point(getX() + 155,getY() + 25);
    }

    public Point getLeftStackPoint()
    {
        return new Point(getX()+18, getY() + 50);
    }

    public Point getRightStackPoint()
    {
        return new Point(getX() + 145,getY() + 28);
    }

    public Rectangle getBounds2()
    {
        return new Rectangle(getRightStackPoint().x, getRightStackPoint().y - numOfRightPlates*imageObject.getHeight(), 36, 2);
    }

    public int getNumOfLeftPlates()
    {
        return numOfLeftPlates;
    }

    public int getNumOfRightPlates()
    {
        return numOfRightPlates;
    }

    public boolean withinRightStack(GameObject o1)
    {
        //o1 is the shape
        imageObject = (ImageItem) o1;
        if(getBounds2().intersects(imageObject.getBounds()))
        {
            numOfRightPlates++;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean withinLeftStack(GameObject o1)
    { 
        //o1 is the shape
        imageObject = (ImageItem) o1;
        if(getBounds().intersects(imageObject.getBounds()))
        {
            numOfLeftPlates++;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void updateLeftStack(Shape s , List<GameObject> control)
    {
        leftStack.push(s);
        int size = leftStack.size();
        if (size >= 3)
        {
            if (leftStack.peek().getColor() == leftStack.get(size - 2).getColor() && leftStack.peek().getColor() == leftStack.get(size - 3).getColor())
            {
                for (int i = 0; i < 3; i++)
                {
                    control.remove(leftStack.pop());
                    numOfLeftPlates--;
                }
                this.score+=10;
            }
        }
    }

    public void updateRightStack(Shape s, List<GameObject> control)
    {
        rightStack.push(s);
        int size = rightStack.size();
        if (size >= 3)
        {
            if (rightStack.peek().getColor() == rightStack.get(size - 2).getColor() && rightStack.peek().getColor() == rightStack.get(size - 3).getColor())
            {
                for (int i = 0; i < 3; i++)
                {
                    control.remove(rightStack.pop());
                    numOfRightPlates--;
                }
                this.score+=10;
            }
        }
    }

    //added to handle re-initializing the clown data on begining new game for the same player
    public void clearData()
    {
        this.score = this.numOfLeftPlates = this.numOfRightPlates = 0;
        this.leftStack.clear();
        this.rightStack.clear();
        setLeftStackPos();
        setRightStackPos();
    }

}
