package test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jelle on 15/03/16.
 */
    public class FullScreenFrame extends JFrame
    {

        public FullScreenFrame()
        {

            GraphicsDevice gd =
                    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            if (gd.isFullScreenSupported())
            {
                setUndecorated(true);
                gd.setFullScreenWindow(this);
            }
            else
            {
                System.err.println("Full screen not supported");
                setSize(100, 100);
                setVisible(true);
            }
        }
    }


