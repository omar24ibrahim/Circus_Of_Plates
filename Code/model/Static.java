package model;


public class Static implements State
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
        //setting y of the image item to the same value
        this.g.setY(this.g.getY());
    }

    @Override
    public void setGameObject(ImageItem g)
    {
        this.g = g;
    }

}
