package nl.project34.henk.client;

/**
 * Created by Martijn on 29-03-16.
 */
import nl.project34.henk.api.WithdrawRequest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PinnenOfChecken
{
    ClientDatabase      db;
    WithdrawRequest     request;
    private FullScreenFrame     f;
    public  JLabel              bonJaNee;
    public  JLabel              regelTwee;

    private LogoPanel           logo;

    private Color               myFontColor         = new Color(0,0,0);
    private Color               myBackgroundColor   = new Color(204, 0,1);
    private Font                myHeader            = new Font("Helvetica", Font.PLAIN, 40);

    public PinnenOfChecken()
    {



        f = new FullScreenFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel controlPanel = new JPanel();
        BoxLayout layout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(layout);

        controlPanel.setBackground(myBackgroundColor);

        logo = new LogoPanel();





        controlPanel.add(leeg());
        controlPanel.add(tekst());
        controlPanel.add(knojes());
        controlPanel.add(logo);


        f.add(controlPanel);


        f.setVisible(true);
    }

    public  JPanel tekst()
    {

        JPanel tekst = new JPanel();
        tekst.setBackground(myBackgroundColor);
        // GridLayout layout = new GridLayout(2,1);
        // tekst.setLayout(layout);



        bonJaNee = new JLabel("Maak uw keuze");
        bonJaNee.setAlignmentX(Component.CENTER_ALIGNMENT);
        bonJaNee.setBackground(myBackgroundColor);
        bonJaNee.setFont(new Font("Helvetica", Font.BOLD, 48));
        bonJaNee.setForeground(myFontColor);

        regelTwee = new JLabel();
        regelTwee.setBackground(myBackgroundColor);
        regelTwee.setAlignmentX(Component.CENTER_ALIGNMENT);
        regelTwee.setFont(new Font("Helvetica", Font.PLAIN, 32));
        regelTwee.setForeground(myFontColor);



        // tekst.add(leeg);
        tekst.add(bonJaNee);
        tekst.add(regelTwee);


        return tekst;

    }

    public JPanel knojes()
    {
        JPanel knopjes = new JPanel();
        knopjes.setBackground(myBackgroundColor);

        knopjes.add(cancel());
        knopjes.add(yes());
        knopjes.add(no());
        knopjes.add(snelPinnen());
        return knopjes;
    }

    public JButton yes()
    {
        JButton yes = new JButton("1 - Saldo bekijken");

        yes.setFont(new Font("Helvetica", Font.PLAIN, 40));
        yes.setForeground(myFontColor);


        yes.setBackground(myBackgroundColor);
        yes.setBorderPainted(false);
        yes.setFont(myHeader);
        yes.setForeground(myFontColor);



        return yes;


    }

    public JButton no()
    {
        JButton no = new JButton("2 - Geld opnemen");
        no.setFont(new Font("Helvetica", Font.PLAIN, 40));
        no.setForeground(myFontColor);




        no.setBackground(myBackgroundColor);
        no.setBorderPainted(false);
        no.setFont(myHeader);
        no.setForeground(myFontColor);




        return no;


    }

    public JButton snelPinnen()
    {
        JButton snelPinnen = new JButton("3 - Snel pinnen: €70");
        snelPinnen.setFont(new Font("Helvetica", Font.PLAIN, 40));
        snelPinnen.setForeground(myFontColor);




        snelPinnen.setBackground(myBackgroundColor);
        snelPinnen.setBorderPainted(false);
        snelPinnen.setFont(myHeader);
        snelPinnen.setForeground(myFontColor);




        return snelPinnen;


    }

    public JButton cancel()
    {
        JButton cancel = new JButton("* - cancel");
        cancel.setFont(new Font("Helvetica", Font.PLAIN, 40));
        cancel.setForeground(myFontColor);




        cancel.setBackground(myBackgroundColor);
        cancel.setBorderPainted(false);
        cancel.setFont(myHeader);
        cancel.setForeground(myFontColor);




        return cancel;


    }

    public JPanel leeg()
    {
        JPanel leeg = new JPanel();
        leeg.setBackground(myBackgroundColor);
        return leeg;
    }






}
