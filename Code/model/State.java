package model;


public interface State
{
    public void moveHorrizontal(int t);
    public void moveVertical(int t);
    public void setGameObject(ImageItem g);

}