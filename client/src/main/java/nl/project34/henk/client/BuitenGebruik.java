package nl.project34.henk.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martijn on 05-04-16.
 */
public class BuitenGebruik
{
    private Font myFont              = new Font("Helvetica", Font.PLAIN, 40);
    private Color myFontColor = new Color(0,0,0);
    private Color myBackgroundColor = new Color(204, 0,1);

    public BuitenGebruik()
    {
        FullScreenFrame f = new FullScreenFrame();
        f.setBackground(myBackgroundColor);

        GridLayout layout = new GridLayout(4,1);
        f.setLayout(layout);

        JLabel errorMelding = new JLabel("Deze pinautomaat is buiten gebruik, excuses voor het ongemak");
        errorMelding.setForeground(myFontColor);
        errorMelding.setFont(myFont);

        f.add(new JPanel());
        f.add(errorMelding);
        f.add(new LogoPanel());
        f.add(new JPanel());

        f.setVisible(true);




    }
}
