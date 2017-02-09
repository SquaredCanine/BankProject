package nl.project34.henk.client;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


public class ClipTest
{


    public ClipTest()
    {

        try
        {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("AchtergrondMuziek.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        try
        {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("AchtergrondMuziek.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.stop();
        } catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


}