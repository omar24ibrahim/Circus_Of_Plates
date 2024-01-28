package model;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageItem implements GameObject {
    private int x;
    private int y;
    private BufferedImage[] spriteImages;
    private boolean visible;
    private String path;
    private State state;


    public ImageItem(int x, int y, String path, State state)
    {
        this.x = x;
        this.y = y;
        this.visible = true;
        this.path = path;
        this.state = state;
        state.setGameObject(this);
        this.spriteImages = new BufferedImage[1];
        try
        {
            spriteImages[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getX()
    {
        return this.x;
    }

    @Override
    public void setX(int x)
    {
        //sending co-ordinates to function move() to check its ability to move according to its state
        move(x, this.getY());
    }

    @Override
    public int getY()
    {
        return this.y;
    }

    @Override
    public void setY(int y)
    {
        //sending co-ordinates to function move() to check its ability to move according to its state
        move(this.getX(), y);
    }

    @Override
    public int getWidth()
    {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight()
    {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible()
    {
        return this.visible;
    }

    @Override
    public BufferedImage[] getSpriteImages()
    {
        return this.spriteImages;
    }

    public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path = path;
        try
        {
            spriteImages[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public State getState()
    {
        return this.state;
    }

    public void setState(State state)
    {
        this.state = state;
        state.setGameObject(this);
    }

    public void move(int x, int y)
    {
        //function that handle the movement of image in the frame according to its state
        state.moveHorrizontal(x);
        state.moveVertical(y);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    //added to allow checking the co-ordinates of image when being controlable
    public void finalSetX(int x)
    {
        this.x = x;
    }

    //added to allow checking the co-ordinates of image when being controlable
    public void finalSetY(int y)
    {
        this.y = y;
    }

}
