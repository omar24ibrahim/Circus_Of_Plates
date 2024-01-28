package model;

public class Shape extends ImageItem implements Cloneable
{
    private int color;
    private int type = 0;

    public Shape(int x, int y, String path)
    {
        super(x, y, path, new Vertical());
        this.setColor();
    }

    public void setColor()
    {
        switch(this.getPath())
        {
            case "plates1.png":
            case "rectangles1.png":
            case "triangles1.png":
            {
                this.color = 1;
                break;
            }
            case "plates2.png":
            case "rectangles2.png":
            case "triangles2.png":
            {
                this.color = 2;
                break;
            }
            case "plates3.png":
            case "rectangles3.png":
            case "triangles3.png":
            {
                this.color = 3;
                break;
            }
            case "plates4.png":
            case "rectangles4.png":
            case "triangles4.png":
            {
                this.color = 4;
                break;
            }
            case "plates5.png":
            case "rectangles5.png":
            case "triangles5.png":
            {
                this.color = 5;
                break;
            }
        }
    }
    public int getColor()
    {
        return this.color;
    }

    @Override
    public Shape clone()
    {
        //creates copy of the collected shape to be added to the clown stack
        int x = this.getX();
        int y = this.getY();
        String path = this.getPath();
        Shape m = new Shape(x, y, path);
        m.setState(new Horrizontal());
        return m;
    }

    public void setType(int type)
    {
        //added to clarify the shape is on which stack of the clown
        this.type = type;
    }

    @Override
    public void move(int x, int y)
    {
        //added to handle the shifting of both stacks of shapes when clown reaches the end of game frame
        if(this.getState() instanceof Horrizontal)
        {
            if(type == -1)      //on left stack
            {
                if(x <= 18)
                {
                    super.move(18, y);
                }
                else if(x >= 808)
                {
                    super.move(808, y);
                }
                else
                {
                    super.move(x, y);
                }
            }
            else if(type == 1)      //on right stack
            {
                if(x <= 145)
                {
                    super.move(145, y);
                }
                else if(x >= 935)
                {
                    super.move(935, y);
                }
                else
                {
                    super.move(x, y);
                }
            }
        }
        else
        {
            super.move(x, y);
        }
    }

}

