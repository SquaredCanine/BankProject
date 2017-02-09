package nl.project34.henk.client;

import javax.swing.*;
import java.awt.*;


public class GegevensGui
{
    private ClientDatabase db;

    private FullScreenFrame f;

    private JLabel uwGegevens                   = new JLabel("Uw Gegevens:");

    private String naam                         = new String("");
    private String naamDisplay                  = new String("Naam: ");
    private JLabel naamOpScherm;

    private String pasID                        = new String("");
    private String pasIDDisplay                 = new String("PasID: ");
    private JLabel pasIDOpScherm;

    private String rekeningNummer               = new String("");
    private String rekeningNummerDisplay        = new String("RekeningNummer: ");
    private JLabel rekeningOpScherm;


    private String balance                      = new String("");
    private String balanceDisplay               = new String("Saldo: ");
    private JLabel balanceOpScherm;

    private JLabel laatsteTransactie            = new JLabel("Laatste Transactie:");

    private String datum                        = new String("");
    private String datumDisplay                 = new String("Datum: ");
    private JLabel datumOpScherm;


    private String  bedrag                       = new String("");
    private String  bedragDisplay                = new String("Bedrag: ");
    private JLabel bedragOpScherm;

    private String transactieNummer             = new String("");
    private String transactieNummerDisplay      = new String("Transactienummer: ");
    private JLabel transactieOpScherm;




    private JLabel optieA                       = new JLabel("#: Geld opnemen");
    private JLabel optieB                       = new JLabel("*: Terug");

    private Font fontEen 			            = new Font("Helvetica", Font.PLAIN, 100);
    private Font fontTwee 			            = new Font("Helvetica", Font.PLAIN, 30);
    private Font fontDrie                       = new Font("Helvetica", Font.BOLD, 32);

    private Color backgroundColor 	            = new Color(204, 0,1);
    private Color textColor			            = new Color(0,0,0);

    LogoPanel logo = new LogoPanel();

    public GegevensGui(ClientDatabase db)
    {
        this.db = db;

        naam = db.getNaam();
        naamOpScherm = new JLabel(naamDisplay + naam);

        pasID = db.getPasID();
        pasIDOpScherm = new JLabel(pasIDDisplay + pasID);

        rekeningNummer = db.getRekeningNummer();
        rekeningOpScherm = new JLabel(rekeningNummerDisplay + rekeningNummer);

        balance = "€"+(db.getSaldo())+",-";
        balanceOpScherm = new JLabel(balanceDisplay +  balance);

        datum = db.getDatum();
        datumOpScherm = new JLabel(datumDisplay + datum);

//        bedrag = "€"+Long.parseLong(db.getBedrag())/100+",-";
//        bedragOpScherm = new JLabel(bedragDisplay + bedrag);

        transactieNummer = db.getTransactieNummer();
        transactieOpScherm = new JLabel(transactieNummerDisplay + transactieNummer);



        labelSetup(naamOpScherm);
        labelSetup(rekeningOpScherm);
        labelSetup(balanceOpScherm);
        labelSetup(pasIDOpScherm);

        labelSetup(datumOpScherm);
//        labelSetup(bedragOpScherm);
        labelSetup(transactieOpScherm);



        headerLabelSetup(optieA);
        headerLabelSetup(optieB);



        headerLabelSetup(uwGegevens);
        headerLabelSetup(laatsteTransactie);


//        updateInfo();

        frameSetup();
    }

    public JPanel controlPannel()
    {

        JPanel controlpannel = new JPanel();
        controlpannel.setBackground(backgroundColor);
        BoxLayout layout = new BoxLayout(controlpannel, BoxLayout.Y_AXIS);
        controlpannel.setLayout(layout);
        controlpannel.setBackground(backgroundColor);

        LogoPanel logo = new LogoPanel();

        controlpannel.add(new JPanel());
        controlpannel.add(boven());
        controlpannel.add(midden());
        controlpannel.add(knoppenPannel());
        controlpannel.add(logo);

        return controlpannel;



    }

    public JPanel knoppenPannel()
    {
        JPanel knoppen = new JPanel();
        BoxLayout layout = new BoxLayout(knoppen, BoxLayout.X_AXIS);
        knoppen.setLayout(layout);
        knoppen.setBackground(backgroundColor);

        JLabel leeg = new JLabel("     ");

        knoppen.add(optieB);
        knoppen.add(leeg);
        knoppen.add(optieA);

        return knoppen;
    }



    public JPanel boven()
    {
        JPanel boven = new JPanel();
        BoxLayout layout = new BoxLayout(boven, BoxLayout.Y_AXIS);
        boven.setLayout(layout);
        boven.setBackground(backgroundColor);



        boven.add(uwGegevens);
        boven.add(naamOpScherm);
        boven.add(rekeningOpScherm);
        boven.add(balanceOpScherm);
        boven.add(new JLabel());




        return boven;
    }

    public JPanel midden()
    {
        JPanel midden = new JPanel();
        BoxLayout layout = new BoxLayout(midden, BoxLayout.Y_AXIS);
        midden.setLayout(layout);
        midden.setBackground(backgroundColor);


        midden.add(laatsteTransactie);
        midden.add(datumOpScherm);
//        midden.add(bedragOpScherm);
        midden.add(transactieOpScherm);



        return midden;


    }



    public void labelSetup(JLabel label)
    {
        label.setFont(fontTwee);
        label.setForeground(textColor);

    }

    public void headerLabelSetup(JLabel label)
    {
        label.setFont(fontDrie);
        label.setForeground(textColor);

    }

    public void frameSetup()
    {
        f = new FullScreenFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(controlPannel());
        f.setBackground(backgroundColor);
        f.setVisible(true);
    }



    public void updateInfo()
    {


    }
}
