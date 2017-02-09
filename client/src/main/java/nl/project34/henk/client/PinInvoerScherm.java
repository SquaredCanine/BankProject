package nl.project34.henk.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Toolkit;

/**
 * Created by Martijn on 29-03-16.
 */
public class PinInvoerScherm
{
    private             JPanel          controlPanel;
    private             JPanel          pinInvoer;
    private             LogoPanel       logo;
    private             JPanel          leeg;
    protected static    FullScreenFrame f;
    protected static    JLabel          foutePincode;
    protected static    JPasswordField  pincodefield;


    private Color myBrown = new Color(0,0,0);
    private Color myBackgroundColor = new Color (204, 0,1);
    private Font myWelcomMessage = new Font("Helvetica", Font.PLAIN, 40);
    private Font myBoldWelcomMessage = new Font("Helvetica", Font.BOLD, 48);



    public PinInvoerScherm()
    {
        f = new FullScreenFrame();
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
        controlPanel.add(pinInvoer());
        controlPanel.add(knoppenPaneel());
        controlPanel.add(logo);

        return controlPanel;
        }

    public  JPanel pinInvoer()
    {
        pinInvoer = new JPanel();
        BoxLayout layout = new BoxLayout(pinInvoer, BoxLayout.Y_AXIS);

        pinInvoer.setBackground(myBackgroundColor);
        pinInvoer.setLayout(layout);

        JPanel a = new JPanel();
        a.setBackground(myBackgroundColor);
        a.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel b = new JPanel();
        BoxLayout layoutB = new BoxLayout(b, BoxLayout.Y_AXIS);
        b.setLayout(layoutB);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBackground(myBackgroundColor);


        pincodefield = new JPasswordField(6);
        pincodefield.setFont(myWelcomMessage);
        pincodefield.setBackground(myBackgroundColor);

        JLabel pinInstructie = new JLabel("Voer nu uw pincode in");
        foutePincode = new JLabel("");
        pinInstructie.setBackground(myBackgroundColor);
        pinInstructie.setFont(myBoldWelcomMessage);
        pinInstructie.setForeground(myBrown);





        b.add(pinInstructie);
        b.add(foutePincode);

        a.add(pincodefield);

        pinInvoer.add(b);
        pinInvoer.add(a);



        return pinInvoer;
    }

    public JPanel knoppenPaneel()
    {
        JPanel knoppenPanneel = new JPanel();
        BoxLayout layout = new BoxLayout(knoppenPanneel, BoxLayout.X_AXIS);
        knoppenPanneel.setLayout(layout);

        JButton bevestigen = new JButton("# - bevestigen");
        bevestigen.setBorderPainted(false);
        bevestigen .setBackground(myBackgroundColor);
        bevestigen.setFont(myWelcomMessage);
        bevestigen.setForeground(myBrown);
        bevestigen.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton corrigeren = new JButton("C - corrigeren");
        corrigeren.setBorderPainted(false);
        corrigeren .setBackground(myBackgroundColor);
        corrigeren.setFont(myWelcomMessage);
        corrigeren.setForeground(myBrown);
        corrigeren.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton cancel = new JButton("* - cancel");
        cancel.setBorderPainted(false);
        cancel.setBackground(myBackgroundColor);
        cancel.setFont(myWelcomMessage);
        cancel.setForeground(myBrown);
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        knoppenPanneel.add(cancel);
        knoppenPanneel.add(corrigeren);
        knoppenPanneel.add(bevestigen);

        return knoppenPanneel;
    }



    public  JPanel leeg()
    {

        leeg = new JPanel();
        leeg.setBackground(myBackgroundColor);
        return leeg;
    }

    public void setFoutePincode(String s, int i)
    {
        foutePincode.setText(s + i);
        foutePincode.setFont(myWelcomMessage);
        foutePincode.setForeground(Color.red);
    }

    public void setFoutePincodeGeblokkeerd()
    {
        foutePincode.setText("uw pas is geblokkeerd, neem contact op met uw bank");
        foutePincode.setFont(myWelcomMessage);
        foutePincode.setForeground(Color.red);
    }



}

