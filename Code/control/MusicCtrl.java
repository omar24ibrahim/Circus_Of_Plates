package control;

import javax.sound.sampled.*;
import java.io.IOException;

public class MusicCtrl
{
    Clip clip;
    public void runMusic(String path)
    {
        AudioInputStream inputStream;
        try
        {
            inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start(); //check
        }
        catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        //when to close
        clip.close();
    }

    public void pause()
    {
        clip.stop();
    }

    public void resume()
    {
        clip.start();
    }

}

