package nl.project34.henk.client;

import nl.project34.henk.api.WithdrawRequest;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;

/**
 * Created by Martijn on 05-04-16.
 */
public class Doneren
{

    private WithdrawRequest request;

    private JLabel  tekst;
    private Color   myBackgroundColor = new Color(204, 0,1);
    private Color   textColor		= new Color(0,0,0);
    private Font    myFont          = new Font("Helvetica", Font.PLAIN, 48);
    private Font    myBoldFont      = new Font("Helvetica", Font.BOLD, 48);

    public Doneren()
    {

//        this.request = request;
        frame();
    }

    public FullScreenFrame frame()
    {
        FullScreenFrame f = new FullScreenFrame();
        JPanel controlPanel = new JPanel();
        BoxLayout layout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(layout);

        LogoPanelGoedeDoel logo = new LogoPanelGoedeDoel();


        controlPanel.add(leeg());
        controlPanel.add(doneerTekst());
        controlPanel.add(knoppenPaneel());
        controlPanel.add(leeg());
        controlPanel.add(logo);

        f.add(controlPanel);

        f.setVisible(true);
        return f;




    }

    public JPanel doneerTekst()
    {
        JPanel doneerTekst = new JPanel();
        doneerTekst.setBackground(myBackgroundColor);
        tekst = new JLabel("Wilt u €0,50 aan Een Betere Wereld doneren?");
        tekst.setFont(myBoldFont);
        tekst.setForeground(textColor);

        doneerTekst.add(tekst);

        return doneerTekst;


    }


    public JPanel knoppenPaneel()
    {
        JPanel knoppenPaneel = new JPanel();
        BoxLayout layout = new BoxLayout(knoppenPaneel, BoxLayout.X_AXIS);
        knoppenPaneel.setLayout(layout);
        knoppenPaneel.setBackground(myBackgroundColor);


        knoppenPaneel.add(yes());
        knoppenPaneel.add(no());

        return knoppenPaneel;



    }

    public JButton yes()
    {
        JButton yes = new JButton("1 - Ja ");


        yes.setFont(new Font("Helvetica", Font.PLAIN, 40));
        yes.setForeground(textColor);


        yes.setBackground(myBackgroundColor);
        yes.setBorderPainted(false);
        yes.setFont(myFont);
        yes.setForeground(textColor);



        return yes;


    }

    public JButton no()
    {
        JButton no = new JButton("2 - Nee");
        no.setFont(new Font("Helvetica", Font.PLAIN, 40));
        no.setForeground(textColor);




        no.setBackground(myBackgroundColor);
        no.setBorderPainted(false);
        no.setFont(myFont);
        no.setForeground(textColor);




        return no;


    }

    public JPanel leeg()
    {
        JPanel leeg = new JPanel();
        leeg.setBackground(myBackgroundColor);
        return leeg;

    }





}
