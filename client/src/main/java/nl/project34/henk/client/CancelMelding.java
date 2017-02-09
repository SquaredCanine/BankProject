package nl.project34.henk.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancelMelding
{
	private JPanel controlPanel;
	private JLabel melding;

	private Font myWelcomMessage = new Font("Helvetica", Font.PLAIN, 40);
    private Color myFontColor = new Color(0,0,0);
	private Color myBackgroundColor = new Color(204, 0,1);

	public CancelMelding()
	{
		FullScreenFrame f = new FullScreenFrame();

		f.add(controlPanel());

        f.setVisible(true);
	}


    public JPanel controlPanel()
    {
        controlPanel = new JPanel();
        BoxLayout layout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);

        controlPanel.setLayout(layout);
        controlPanel.setBackground(myBackgroundColor);

        melding = new JLabel("Uw transactie is afgesloten.");
		melding.setFont(new Font("Helvetica", Font.BOLD, 48));
        melding.setForeground(myFontColor);
        melding.setAlignmentX(Component.CENTER_ALIGNMENT);

        LogoPanel logo = new LogoPanel();

       
 
        controlPanel.add(leeg());
        controlPanel.add(melding);
        controlPanel.add(leeg());
        controlPanel.add(logo);
        
        return  controlPanel;
    }

//    public JPanel onderste()
//    {
//        JPanel onderste = new JPanel();
//        onderste.setBackground(myBackgroundColor);
//
//        JLabel bevestigen = new JLabel("'#' - bevestigen");
//        bevestigen.setFont(myWelcomMessage);
//        bevestigen.setForeground(myFontColor);
//
//
//        onderste.add(bevestigen);
//
//        return onderste;
//    }

    public JPanel leeg()
    {
        JPanel leeg = new JPanel();
        leeg.setBackground(myBackgroundColor);
        return leeg;
    }


    public class OkKnop implements ActionListener
    {
        public void actionPerformed(ActionEvent ok)
        {
            WelkomsScherm a = new WelkomsScherm();
        }
    }
}