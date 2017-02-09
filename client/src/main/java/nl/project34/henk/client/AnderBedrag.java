package nl.project34.henk.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Toolkit;

/**
 * Created by Martijn on 29-03-16.
 */
public class AnderBedrag
{


    private JPanel              controlPanel;
    private JPanel              bedragInvoer;
    private LogoPanel           logo;
    private JPanel              leeg;
    protected static JLabel     verkeerdBedrag;
    protected static JTextField bedragInput;


    private Color myBrown = new Color(0,0,0);
    private Color myBackgroundColor = new Color (204, 0,1);
    private Font myWelcomMessage = new Font("Helvetica", Font.PLAIN, 48);

    // public static void main(String[] args)
    // {
    // 	keyFrame();

    // }

    public AnderBedrag()
    {

        FullScreenFrame f = new FullScreenFrame();
        f.add(controlPanel());
        f.setBackground(myBackgroundColor);

        f.setVisible(true);
    }


    public  JPanel controlPanel()
    {
        controlPanel = new JPanel();
        BoxLayout layout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(layout);
        controlPanel.setBackground(myBackgroundColor);

        logo = new LogoPanel();

        controlPanel.add(leeg());
        controlPanel.add(bedragInvoer());
        controlPanel.add(logo);

        return controlPanel;
    }

    public  JPanel bedragInvoer()
    {
        bedragInvoer = new JPanel();
        BoxLayout layout = new BoxLayout(bedragInvoer, BoxLayout.Y_AXIS);


        bedragInvoer.setLayout(layout);

        JPanel a = new JPanel();
        a.setBackground(myBackgroundColor);
        JPanel b = new JPanel();
        b.setBackground(myBackgroundColor);

        verkeerdBedrag = new JLabel();
        verkeerdBedrag.setFont(new Font("Helvetica", Font.PLAIN, 40));
        verkeerdBedrag.setForeground(Color.red);


        bedragInput = new JTextField(3);
        bedragInput.setFont(myWelcomMessage);
        bedragInput.setBackground(myBackgroundColor);

        JLabel bedragInstructie = new JLabel("Voer het gewenste bedrag in en druk op '#'");
        bedragInstructie.setFont(myWelcomMessage);
        bedragInstructie.setForeground(myBrown);

        a.add(bedragInput);
        b.add(bedragInstructie);
        b.add(verkeerdBedrag);

        bedragInvoer.add(b);

        bedragInvoer.add(a);


        return bedragInvoer;
    }

//        public static JPanel logo()
//        {
//            logo = new JPanel();
//
//            ImageIcon afbeelding = new ImageIcon("BankLogo.png");
//            JLabel label = new JLabel(afbeelding);
//
//            logo.add(label);
//
//            return logo;
//
//        }

    public  JPanel leeg()
    {
        leeg = new JPanel();
        leeg.setBackground(myBackgroundColor);
        return leeg;
    }


}

