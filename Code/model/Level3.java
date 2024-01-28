package model;

import control.MusicCtrl;
import view.Circus;

public class Level3 implements LevelStrategy
{
    private Circus circus;
    private MusicCtrl musicCtrl;

    public Level3(Circus circus)
    {
        this.circus = circus;
        musicCtrl = circus.getMusicCtrl();
        musicCtrl.runMusic("level3.wav");
    }

    @Override
    public void setLevel()
    {
        circus.setEndTime(80); //1 min
        circus.setNumberOfFallingShapes(30);
        circus.setSpeed(1);
        circus.getMovableObjects().add(new Bomb(750,0));
        circus.getMovableObjects().add(new Bomb(250,0));
        //2 bombs are added to level as more difficulty
    }

}
