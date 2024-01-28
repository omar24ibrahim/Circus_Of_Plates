package model;

import control.MusicCtrl;
import view.Circus;

public class Level2 implements LevelStrategy
{
    private Circus circus;
    private MusicCtrl musicCtrl;

    public Level2(Circus circus)
    {
        this.circus = circus;
        musicCtrl = circus.getMusicCtrl();
        musicCtrl.runMusic("level2.wav");
    }

    @Override
    public void setLevel()
    {
        circus.setEndTime(70); //1 min
        circus.setNumberOfFallingShapes(20);
        circus.setSpeed(3);
        circus.getMovableObjects().add(new Bomb(500,0));
        //1 bomb is added to level as difficulty
    }

}
