package nl.project34.henk.client;

/**
 * Created by Martijn on 29-03-16.
 */

import nl.project34.henk.api.WithdrawRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Martijn on 14-03-16.
 */

public class KeuzeMenu
{
    ClientDatabase db;

    private JPanel      controlPanel;
    private JPanel      tekstPanel;
    private LogoPanel   logo            = new LogoPanel();
    private JPanel      rechtsBoven;
    private JPanel      rechtsMidden;
    private JPanel      rechtsOnder;
    private JPanel      boven;
    private JPanel      onder;

    private JLabel      tekst;

    private Font myWelcomMessage    = new Font("Helvetica", Font.PLAIN, 48);
    private Font myBoldWelcomMessage    = new Font("Helvetica", Font.BOLD, 48);
    private Color myFontColor       = new Color(0,0,0);

    private Color myBackgroundColor = new Color(204, 0,1);

    public KeuzeMenu(ClientDatabase db)

    {
        this.db = db;

        FullScreenFrame f = new FullScreenFrame();

        f.add(controlPanel());

        f.setVisible(true);
    }

    public JPanel controlPanel()
    {
        controlPanel = new JPanel();
        BoxLayout layout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(layout);


        logo.setBackground(myBackgroundColor);

        controlPanel.add(leeg());
        controlPanel.add(tekstPanel());
        controlPanel.add(leeg());
        controlPanel.add(boven());
        controlPanel.add(onder());



        return  controlPanel;


    }

    public JPanel boven()
    {
        boven = new JPanel();
        GridLayout layout = new GridLayout(1,3);
        boven.setLayout(layout);
        boven.setBackground(myBackgroundColor);

        boven.add(links());
        boven.add(rechtsMidden());
        boven.add(rechtsOnder());

        return boven;
    }



    public JPanel onder()
    {
        onder = new JPanel();
        GridLayout layout = new GridLayout(1,3);
        onder.setLayout(layout);
        onder.setBackground(myBackgroundColor);

        onder.add(leeg());
        onder.add(logo);
        onder.add(leeg());
        return onder;
    }

    public  JPanel  tekstPanel()
    {

        tekstPanel = new JPanel();
        tekstPanel.setBackground(myBackgroundColor);
        BoxLayout layout = new BoxLayout(tekstPanel, BoxLayout.Y_AXIS);
        tekstPanel.setLayout(layout);

        JLabel a = new JLabel("Welkom " + db.getNaam());
        a.setAlignmentX(Component.CENTER_ALIGNMENT);
        a.setFont(myBoldWelcomMessage);
        a.setForeground(myFontColor);
        a.setBackground(myBackgroundColor);

        JLabel b = new JLabel("");
        b.setBackground(myBackgroundColor);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(myWelcomMessage);
        b.setForeground(myFontColor);

        tekst = new JLabel("Hoeveel wilt u opnemen?");
        tekst.setAlignmentX(Component.CENTER_ALIGNMENT);
        tekst.setFont(myWelcomMessage);
        tekst.setForeground(myFontColor);
        tekst.setBackground(myBackgroundColor);
        tekstPanel.setBackground(new Color(204, 0,1));


        tekstPanel.add(b);
        tekstPanel.add(a);
        tekstPanel.add(tekst);


        return tekstPanel;
    }

    public JPanel links()
    {
        JPanel links = new JPanel();
        GridLayout layout = new GridLayout(2,1);
        links.setLayout(layout);
        links.setBackground(myBackgroundColor);

        JLabel cancel = new JLabel("* - Cancel");
        cancel.setBackground(myBackgroundColor);
        cancel.setFont(myWelcomMessage);
        cancel.setForeground(myFontColor);
        cancel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel terug = new JLabel("0 - Terug");
        terug.setBackground(myBackgroundColor);
        terug.setFont(myWelcomMessage);
        terug.setForeground(myFontColor);
        terug.setAlignmentX(Component.RIGHT_ALIGNMENT);


        links.add(cancel);
        links.add(terug);
        return links;
    }

    public  JPanel rechtsMidden()
    {
        rechtsMidden = new JPanel();
        GridLayout layout = new GridLayout(2,1);
        rechtsMidden.setLayout(layout);
        rechtsMidden.setBackground(myBackgroundColor);


        JLabel twintigKlik = new JLabel("A - €20");
        twintigKlik.setBackground(myBackgroundColor);
        twintigKlik.setFont(myWelcomMessage);
        twintigKlik.setForeground(myFontColor);




        JLabel vijftigKlik = new JLabel("B - €50");
        vijftigKlik.setBackground(myBackgroundColor);

        vijftigKlik.setFont(myWelcomMessage);
        vijftigKlik.setForeground(myFontColor);


        rechtsMidden.add(twintigKlik);
        rechtsMidden.add(vijftigKlik);

        return rechtsMidden;
    }

    public JPanel rechtsOnder()
    {
        rechtsOnder = new JPanel();
        GridLayout layout = new GridLayout(2,1);
        rechtsOnder.setLayout(layout);
        rechtsOnder.setBackground(myBackgroundColor);


        JLabel honderdKlik = new JLabel("C - €100");
        honderdKlik.setAlignmentX(Component.LEFT_ALIGNMENT);
        honderdKlik.setBackground(myBackgroundColor);
        honderdKlik.setFont(myWelcomMessage);
        honderdKlik.setForeground(myFontColor);



        JLabel xKlik = new JLabel("D - Ander Bedrag");
        xKlik.setAlignmentX(Component.LEFT_ALIGNMENT);
        xKlik.setBackground(myBackgroundColor);
        xKlik.setFont(myWelcomMessage);
        xKlik.setForeground(myFontColor);
        xKlik.setAlignmentX(Component.RIGHT_ALIGNMENT);


        rechtsOnder.add(honderdKlik);
        rechtsOnder.add(xKlik);

        return rechtsOnder;

    }

    public JPanel leeg()
    {
        JPanel leeg = new JPanel();
        leeg.setBackground(myBackgroundColor);
        return leeg;
    }


}
