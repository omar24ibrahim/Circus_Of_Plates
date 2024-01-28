package view;

import control.Controller;
import control.MusicCtrl;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import model.*;
import java.util.LinkedList;
import java.util.List;

public class Circus implements World {

    private List<GameObject> constant;
    private List<GameObject> moving;
    private List<GameObject> controlled;
    private int width;
    private int height;
    private int speed;
    private int controlSpeed = 20;
    private long startTime;
    private int endTime = 0;
    private int numberOfFallingShapes;
    private Controller player;
    private LevelStrategy level;
    private int levelNum;
    private MusicCtrl musicCtrl;
    public Circus(int width, int height, int level)
    {
        this.musicCtrl = new MusicCtrl();
        this.constant = new LinkedList<>();
        this.moving = new LinkedList<>();
        this.controlled = new LinkedList<>();
        this.width = width;
        this.height = height;
        this.levelNum = level;
        this.startTime = System.currentTimeMillis();
        constructCircus();
    }

    private void constructCircus()
    {
        switch (levelNum)
        {
            case 1:
                level = new Level1(this);
                break;
            case 2:
                level = new Level2(this);
                break;
            case 3:
                level = new Level3(this);
                break;
        }
        level.setLevel();
        player = Controller.getInstance("back" + levelNum + ".png");
        player.setCircus(this);
        player.getClown().clearData();
        player.getClown().setX(395);
        player.getBackground().setPath("back" + levelNum + ".png");
        controlled.add(player.getClown());
        constant.add(player.getBackground());
        player.setPool();
        moving.addAll(player.getPool().getShapes());
    }

    public MusicCtrl getMusicCtrl()
    {
        return musicCtrl;
    }

    @Override
    public List<GameObject> getConstantObjects()
    {
        return this.constant;
    }

    @Override
    public List<GameObject> getMovableObjects()
    {
        return this.moving;
    }

    @Override
    public List<GameObject> getControlableObjects()
    {
        return this.controlled;
    }

    @Override
    public int getWidth()
    {
        return this.width;
    }

    @Override
    public int getHeight()
    {
        return this.height;
    }

    @Override
    public boolean refresh()
    {
        boolean timing = ((Math.max(0, (this.endTime - (System.currentTimeMillis() - this.startTime) / 1000))) == 0);
        if(!player.checkIntersection())
        {
            timing = true;
            this.endTime = 0;
        }
        if((player.getClown().getNumOfLeftPlates() > 20) || (player.getClown().getNumOfRightPlates() > 20))
        {
            timing = true;
        }
        if(timing)
        {
            musicCtrl.close();
            moving.clear();  //remove falling objects when game ends
        }
        return !timing;
    }

    @Override
    public String getStatus()
    {
        return "SCORE: " + player.getScore() + "   |   TIME: " + (Math.max(0, (this.endTime - (System.currentTimeMillis() - this.startTime) / 1000)));
    }

    @Override
    public int getSpeed()
    {
        return this.speed;
    }

    @Override
    public int getControlSpeed()
    {
        return this.controlSpeed;
    }

    public int getNumberOfFallingShapes()
    {
        //added in pool
        return numberOfFallingShapes;
    }

    public void setNumberOfFallingShapes(int n)
    {
        this.numberOfFallingShapes = n;
    }

    public void setSpeed(int s)
    {
        this.speed = s;
    }

    public void setEndTime(int n)
    {
        this.endTime = n;
    }

    public int getLevelNumber()
    {
        //difficulty
        return this.levelNum;
    }

}
