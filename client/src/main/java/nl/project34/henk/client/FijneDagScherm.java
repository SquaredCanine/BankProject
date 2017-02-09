package nl.project34.henk.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martijn on 29-03-16.
 */
public class FijneDagScherm
{
    private LogoPanel   logo;
    private JPanel      leegPanel;
    private JPanel      welkomPanel;

    private JPanel      controlPanel;


    private Color myFontColor       = new Color(0,0,0);
    private Color myBackgroundColor = new Color(204, 0,1);

    private Font myWelcomMessage = new Font("Helvetica", Font.PLAIN, 48);

    public  FijneDagScherm()
    {
        FullScreenFrame f = new FullScreenFrame();

        controlPanel = new JPanel();
        BoxLayout layout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(layout);

        logo = new LogoPanel();

        f.add(controlPanel);

        controlPanel.add(leegPanel());
        controlPanel.add(welkomPanel());
        controlPanel.add(leegPanel());
        controlPanel.add(logo);


        f.setVisible(true);
    }


    public JPanel welkomPanel()
    {
        welkomPanel = new JPanel();
        BoxLayout layout = new BoxLayout(welkomPanel, BoxLayout.Y_AXIS);

        welkomPanel.setLayout(layout);

        JLabel welkomBericht = new JLabel("Bedankt voor uw transactie");
        welkomBericht.setAlignmentX(Component.CENTER_ALIGNMENT);
        welkomBericht.setFont(myWelcomMessage);
        welkomBericht.setForeground(myFontColor);

        JLabel pasScan = new JLabel("Wij wensen u een prettige dag!");
        pasScan.setAlignmentX(Component.CENTER_ALIGNMENT);
        pasScan.setFont(new Font("Helvetica", Font.PLAIN, 40));
        pasScan.setForeground(myFontColor);



        welkomPanel.add(welkomBericht);
        welkomPanel.add(pasScan);

        return welkomPanel;
    }



    public JPanel leegPanel()
    {
        leegPanel = new JPanel();
        leegPanel.setBackground(myBackgroundColor);

        return leegPanel;

    }


}
