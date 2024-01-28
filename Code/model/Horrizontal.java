package model;


public class Horrizontal implements State
{
    private ImageItem g;

    @Override
    public void moveHorrizontal(int t)
    {
        //setting x of the image item to the recieved value
        this.g.finalSetX(t);
    }

    @Override
    public void moveVertical(int t)
    {
        //setting y of the image item to the same value
        this.g.finalSetY(this.g.getY());
    }

    @Override
    public void setGameObject(ImageItem g)
    {
        this.g = g;
    }

}