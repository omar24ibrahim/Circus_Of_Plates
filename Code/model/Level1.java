package model;

import control.MusicCtrl;
import view.Circus;

public class Level1 implements LevelStrategy
{
    private Circus circus;
    private MusicCtrl musicCtrl;

    public Level1(Circus circus)
    {
        this.circus = circus;
        musicCtrl = circus.getMusicCtrl();
        musicCtrl.runMusic("level1.wav");
    }

    @Override
    public void setLevel()
    {
        circus.setEndTime(60); //1 min
        circus.setNumberOfFallingShapes(15);
        circus.setSpeed(6);
        //no bombs added to level
    }

}
