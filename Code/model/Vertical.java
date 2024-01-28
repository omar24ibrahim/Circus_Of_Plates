package model;


public class Vertical implements State
{
    private ImageItem g;

    @Override
    public void moveHorrizontal(int t)
    {
        //setting x of the image item to the same value
        this.g.finalSetX(this.g.getX());
    }

    @Override
    public void moveVertical(int t)
    {
        //setting y of the image item to the recieved value
        this.g.finalSetY(t);
    }

    @Override
    public void setGameObject(ImageItem g)
    {
        this.g = g;
    }

}

