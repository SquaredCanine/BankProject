package test;

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

public class KeuzeMenuBackUp
{
    ClientDatabase db;

    private JPanel controlPanel;
    private JPanel tekstPanel;
    private LogoPanel logo;
    private JPanel rechtsBoven;
    private JPanel rechtsMidden;
    private JPanel rechtsOnder;

    private JLabel tekst;

    private Font myWelcomMessage = new Font("Helvetica", Font.PLAIN, 48);
    private Color myFontColor = new Color(98,88,82);
    private Color myBackgroundColor = new Color(98,88,82);

    public KeuzeMenuBackUp(ClientDatabase db)

    {
        this.db = db;

        FullScreenFrame f = new FullScreenFrame();

        f.add(controlPanel());

        f.setVisible(true);
    }

    public JPanel controlPanel()
    {
        controlPanel = new JPanel();
        GridLayout layout = new GridLayout(2,5);


        controlPanel.setLayout(layout);

        logo = new LogoPanel();
        logo.setBackground(new Color(241,241,241));


        controlPanel.add(logo);
        controlPanel.add(rechtsBoven());
        controlPanel.add(tekstPanel());
        controlPanel.add(rechtsMidden());
        controlPanel.add(cancel());
        controlPanel.add(rechtsOnder());


        return  controlPanel;


    }

    public  JPanel  tekstPanel()
    {

        tekstPanel = new JPanel();
        JLabel a = new JLabel("Welkom " + db.getNaam());
        a.setFont(myWelcomMessage);
        a.setForeground(myFontColor);
        tekst = new JLabel("Hoeveel wilt u opnemen?");
        tekst.setFont(myWelcomMessage);
        tekst.setForeground(myFontColor);
        tekstPanel.setBackground(new Color(241,241,241));


        tekstPanel.add(a);
        tekstPanel.add(tekst);


        return tekstPanel;
    }

    public JButton cancel()
    {
        CancelKnop cancelKnop = new CancelKnop();
        JButton cancel = new JButton("* - cancel");
        cancel.setBackground(myFontColor);
        cancel.setBorderPainted(false);
        cancel.setFont(myWelcomMessage);
        cancel.setForeground(myFontColor);
        cancel.addActionListener(cancelKnop);

        return cancel;
    }

    public JPanel rechtsBoven()
    {
        rechtsBoven = new JPanel();
        JLabel a = new JLabel("Welkom");
        JLabel klantNaam = new JLabel();
        klantNaam.setText(db.getNaam());
        rechtsBoven.setBackground(new Color(241,241,241));


        return rechtsBoven;

    }

    public  JPanel rechtsMidden()
    {
        rechtsMidden = new JPanel();
        GridLayout layout = new GridLayout(2,1);
        rechtsMidden.setLayout(layout);
        rechtsMidden.setBackground(new Color(241,241,241));

        TwintigKnop twintigKnop = new TwintigKnop();
        JButton twintigKlik = new JButton("€20 - A");
        twintigKlik.setBackground(myBackgroundColor);
        twintigKlik.setBorderPainted(false);
        twintigKlik.setFont(myWelcomMessage);
        twintigKlik.setForeground(myFontColor);
        twintigKlik.addActionListener(twintigKnop);


        VijftigKnop vijftigKnop = new VijftigKnop();
        JButton vijftigKlik = new JButton("€50 - B");
        vijftigKlik.setBackground(myBackgroundColor);
        vijftigKlik.setBorderPainted(false);
        vijftigKlik.setFont(myWelcomMessage);
        vijftigKlik.setForeground(myFontColor);
        vijftigKlik.addActionListener(vijftigKnop);

        rechtsMidden.add(twintigKlik);
        rechtsMidden.add(vijftigKlik);

        return rechtsMidden;
    }

    public JPanel rechtsOnder()
    {
        rechtsOnder = new JPanel();
        GridLayout layout = new GridLayout(2,1);
        rechtsOnder.setLayout(layout);
        rechtsOnder.setBackground(new Color(241,241,241));



        HonderdKnop honderdKnop = new HonderdKnop();
        JButton honderdKlik = new JButton("€100 - C");
        honderdKlik.setBackground(myBackgroundColor);
        honderdKlik.setBorderPainted(false);
        honderdKlik.setFont(myWelcomMessage);
        honderdKlik.setForeground(myFontColor);
        honderdKlik.addActionListener(honderdKnop);

        XKnop xKnop = new XKnop();
        JButton xKlik = new JButton("Ander Bedrag - D");
        xKlik.setBackground(myBackgroundColor);
        xKlik.setBorderPainted(false);
        xKlik.setFont(myWelcomMessage);
        xKlik.setForeground(myFontColor);
        xKlik.setAlignmentX(Component.RIGHT_ALIGNMENT);
        xKlik.addActionListener(xKnop);

        rechtsOnder.add(honderdKlik);
        rechtsOnder.add(xKlik);

        return rechtsOnder;

    }

    public class TwintigKnop implements ActionListener
    {
        public void actionPerformed(ActionEvent print)
        {


            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();


            request.setIBAN(db.getRekeningNummer());
            request.setAmount(2000);

            client.withdraw(request);

            BonPrintScherm a = new BonPrintScherm(db, request);
        }
    }
    public class VijftigKnop implements ActionListener
    {
        public void actionPerformed(ActionEvent print)
        {

            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();


            request.setIBAN(db.getRekeningNummer());
            request.setAmount(5000);

            client.withdraw(request);
            BonPrintScherm a = new BonPrintScherm(db, request);
        }
    }

    public class HonderdKnop implements ActionListener
    {
        public void actionPerformed(ActionEvent print)
        {

            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();
            System.out.println("test");

            request.setIBAN(db.getRekeningNummer());
            request.setAmount(10000);

            client.withdraw(request);
            BonPrintScherm a = new BonPrintScherm(db, request);
        }
    }

    public class XKnop implements ActionListener
    {
        public void actionPerformed(ActionEvent print)
        {
//            PrintGui a = new PrintGui();

        }
    }

    public class CancelKnop implements ActionListener
    {
        public void actionPerformed(ActionEvent cancel)
        {
            CancelMelding a = new CancelMelding();
        }
    }


}
