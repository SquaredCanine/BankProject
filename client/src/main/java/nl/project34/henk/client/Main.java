package nl.project34.henk.client;

/**
 * Created by Martijn on 29-03-16.
 */


import com.fazecast.jSerialComm.SerialPort;
import nl.project34.henk.api.*;

import java.util.Scanner;

public class Main
{
    protected static String     pasID           = "leeg";
    static ClientDatabase       klant;
    static WithdrawRequest      request         = new WithdrawRequest();

    protected static MyClient   client          = new MyClient();

    protected static String     anders;
    protected static String     value;
    protected static int     bankID;
    protected static String     rfidInput;
    protected static String     bonAfschrijving;
    protected static int        userID;
    protected static int        wrongPinCount   = 0;

    protected static String     pincode         = "";
    protected static String     xbedrag         = "";
    protected static int        gekozenBedrag;
    protected static int        verkeerdCount;

    protected static Scanner    data;

    protected static int        tijdCounter;
    protected static SerialPort serialPort;

    protected static PinInvoerScherm k;




    protected static boolean    open            = true;
    protected static boolean    cancel          = false;
    protected static boolean    geldkeuze       = false;
    protected static boolean    print           = false;
    protected static boolean    welkom          = false;
    protected static boolean    key             = false;
    protected static boolean    anderbedrag     = false;
    protected static boolean    doneren         = false;
    protected static boolean    fijnedag        = false;
    protected static boolean    infoofpinnen    = false;
    protected static boolean    gegevens        = false;


    public static void main(String[] args)
    {

        System.out.println("Balance");
        BalanceRequest brequest = new BalanceRequest();
        brequest.setPasID("16610111111101011010111000101100010");
        brequest.setBankID(0);
        BalanceResponse bresponse = client.balance(brequest);
        System.out.println(bresponse.getBalance());

        System.out.println("Check Pas");
        CheckPasRequest crequest = new CheckPasRequest();
        crequest.setBankID(0);
        crequest.setPasID("16610111111101011010111000101100010");
        CheckPasResponse cresponse = client.checkPas(crequest);
        System.out.println(cresponse.doesExist());
        System.out.println(cresponse.isBlocked());

        System.out.println("Withdraw");
        WithdrawRequest wrequest = new WithdrawRequest();
        wrequest.setBankID(0);
        wrequest.setPasID("16610111111101011010111000101100010");
        wrequest.setPinAmount(2000);
        WithdrawResponse wresponse = client.withdraw(wrequest);
        System.out.println(wresponse.isSucceeded());
        System.out.println(wresponse.getTransactieNummer());


        System.out.println("Balance");
        bresponse = client.balance(brequest);
        System.out.println(bresponse.getBalance());

        System.out.println("CheckPin");
        CheckPinResponse cpresponse = null;
        try
        {
            CheckPinRequest cprequest = new CheckPinRequest();
            cprequest.setBankID(0);
            cprequest.setPasID("16610111111101011010111000101100010");
            cprequest.setPinCode("1234");
            cpresponse = client.checkPin(cprequest);
        }
        catch(Exception e)
        {
            System.out.println("berror: " + e);
        }

        System.out.println(cpresponse.getCorrect());
        System.out.println(cpresponse.getBlocked());




    }

    public static void start()
    {
        while (data.hasNextLine())
        {

            while (true)
            {
                value = data.nextLine();
                anders = value;

                if (value.length() > 2)
                {
                    if(pasID.equals("leeg"))
                    {
                        pasID = value;
                        System.out.println("Kaart ID is: " + pasID);
                        rfidInput = value;
                        value = "";
                        klant = new ClientDatabase(pasID);


                    }


                }




                welkomScherm();
                pinScherm();
                pinnenOfSaldoScherm();
                gegevensScherm();
                geldKeuzeScherm();
                anderBedragScherm();
                donatieScherm();
                bonVraagScherm();
                testPrints();


            }
        }

    }

    public static void arduinoComm()
    {
        SerialPort[] ports = SerialPort.getCommPorts();

        //System.out.println("Select a port:");
        int i = 1;
        for (SerialPort port : ports)
            System.out.println(i++ + ": " + port.getSystemPortName());
        Scanner s = new Scanner(System.in);
        int chosenPort = 5; //s.nextInt();



        try
        {
            serialPort = ports[chosenPort - 1];
            if (serialPort.openPort())
                System.out.println("Port opened successfully.");
            else
            {
                System.out.println("Unable to open the port.");
                return;
            }
        }
        catch (Exception e)
        {
            new BuitenGebruik();
        }



        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

        data = new Scanner(serialPort.getInputStream());
    }

    public static void pinCodeCheck(int bankID, String pasID, String pincode)
    {
        CheckPinRequest request = new CheckPinRequest(bankID, pasID, pincode);
        CheckPinResponse response = client.checkPin(request);
        if(response.getCorrect())
        {
            //Pincode correct
        }else
        {
            if(response.getBlocked())
            {
                //Pas geblokkeerd
                new GeblokkeerdScherm();
            }
            cancelScherm();
            //pincode niet correct
        }



        blokkeerScherm(bankID, pasID, pincode);




        pincode = "";
        PinInvoerScherm.pincodefield.setText("");
    }

    public static void startOpnieuw()
    {
        if (open == true)
        {
            WelkomsScherm a = new WelkomsScherm();
            welkom = true;
            pincode = "";
            pasID = "leeg";
            System.out.println("pas id: " + pasID);
            start();
        }
    }

    public static void welkomScherm()
    {

        try
        {
            if (rfidInput.length() > 1 && welkom == true)
            {
                open = false;
                k = new PinInvoerScherm();
                welkom = false;
                key = true;
                rfidInput = "";
            }
        }
        catch (NullPointerException e)
        {
            startOpnieuw();
        }
    }

    public static void pinScherm()
    {


        if (value.equals("#") && key == true)
        {
            pinCodeCheck(bankID, pasID, pincode);
            value = "";
        }

        if (value.equals("*") && key == true)
        {
            key = false;
            cancelScherm();
            value = "";
        }




        if (value.equals("1") && key == true)
        {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("2") && key == true)
        {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("3") && key == true)
        {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("4") && key == true)
        {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("5") && key == true) {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("6") && key == true) {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("7") && key == true) {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("8") && key == true) {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("9") && key == true) {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }
        if (value.equals("0") && key == true) {
            pincode += value;
            PinInvoerScherm.pincodefield.setText(pincode);
        }

        if (value.equals("C") && key == true)
        {
            pincode = "";
            PinInvoerScherm.pincodefield.setText(pincode);
        }
    }

    public static void pinnenOfSaldoScherm()
    {



        if(value.equals("1") && infoofpinnen == true)
        {
            infoofpinnen = false;
            GegevensGui g = new GegevensGui(klant);
            gegevens = true;
            value = "";
        }
        if(value.equals("2") && infoofpinnen == true)
        {
            infoofpinnen = false;
            KeuzeMenu k = new KeuzeMenu(klant);
            geldkeuze = true;
            value = "";

        }

        if(value.equals("3") && infoofpinnen == true)
        {
            infoofpinnen = false;
            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();

            request.setPasID(klant.getPasID());
            request.setPinAmount(7000);

            client.withdraw(request);
            fijneDagScherm();
            value = "";
        }

        if(value.equals("*") && infoofpinnen == true)
        {
            infoofpinnen = false;
            cancelScherm();
            value = "";

        }
    }

    public static void gegevensScherm()
    {
        if (value.equals("*") && gegevens == true)
        {
            gegevens = false;
            PinnenOfChecken p = new PinnenOfChecken();
            infoofpinnen = true;
            value = "";
        }
        if(value.equals("A") && gegevens == true)
        {
            KeuzeMenu k = new KeuzeMenu(klant);
            gegevens = false;
            geldkeuze = true;
            value = "";
        }
    }

    public static void geldKeuzeScherm()
    {
        if (value.equals("*") && geldkeuze == true)
        {
            geldkeuze = false;

            cancel = true;
            value = "";

            cancelScherm();
        }
        if (value.equals("0") && geldkeuze == true)
        {
            geldkeuze = false;
            PinnenOfChecken p = new PinnenOfChecken();
            infoofpinnen = true;
            value = "";
        }
        if (value.equals("A") && geldkeuze == true)
        {
            geldkeuze = false;
            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();


            request.setPasID(klant.getPasID());
            request.setPinAmount(2000);
            bonAfschrijving = String.valueOf(request.getPinAmount());
            System.out.println("A: bedrag wat door gegeven wordt is: " + bonAfschrijving);

            client.withdraw(request);

            Doneren a = new Doneren();

            doneren = true;

            value = "";

        }
        if (value.equals("B") && geldkeuze == true) {
            geldkeuze = false;
            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();



            request.setPasID(klant.getPasID());
            request.setPinAmount(5000);

            bonAfschrijving = String.valueOf((request.getPinAmount()/100));
            System.out.println("A: bedrag wat door gegeven wordt is: " + bonAfschrijving);

            client.withdraw(request);

            Doneren a = new Doneren();

            doneren = true;

            value = "";



        }
        if (value.equals("C") && geldkeuze == true) {
            value = "";
            geldkeuze = false;
            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();



            request.setPasID(klant.getPasID());
            request.setPinAmount(10000);

            bonAfschrijving = String.valueOf((request.getPinAmount()/100));
            System.out.println("A: bedrag wat door gegeven wordt is: " + bonAfschrijving);

            client.withdraw(request);
            Doneren a = new Doneren();

            doneren = true;


        }
        if (value.equals("D") && geldkeuze == true) {
            value = "";
            key = false;
            geldkeuze = false;
            anderbedrag = true;

            AnderBedrag a = new AnderBedrag();
        }

    }

    public static void anderBedragScherm()
    {
        if (anders.equals("1") && anderbedrag == true && key == false) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("2") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("3") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("4") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("5") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("6") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("7") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("8") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("9") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }
        if (anders.equals("0") && anderbedrag == true) {
            xbedrag += anders;
            AnderBedrag.bedragInput.setText(xbedrag);
        }

        if (anders.equals("#") && anderbedrag == true)
        {
            value = "";
            bevestigingsActie();



        }

        if (anders.equals("C") && anderbedrag == true)
        {
            xbedrag = "";
            AnderBedrag.bedragInput.setText(xbedrag);
        }



    }

    public static void bevestigingsActie()
    {
        gekozenBedrag = Integer.parseInt(xbedrag);
        if(gekozenBedrag % 5 == 0)
        {
            anderbedrag = false;

            MyClient client = new MyClient();
            WithdrawRequest request = new WithdrawRequest();


            request.setPasID(klant.getPasID());


            request.setPinAmount(gekozenBedrag * 100); //cent
            bonAfschrijving = String.valueOf((request.getPinAmount()/100));

            client.withdraw(request);

            Doneren d = new Doneren();
            doneren = true;


        }
        else if (gekozenBedrag % 5 > 0)
        {
            AnderBedrag.verkeerdBedrag.setText("Kies alstublieft een veelvoudt van 5");
            xbedrag = "";

            AnderBedrag.bedragInput.setText(xbedrag);

        }
    }

    public static void donatieScherm()
    {
        if(value.equals("1")&& doneren == true)
        {
            value = "";
            MyClient client = new MyClient();

            WithdrawRequest request = new WithdrawRequest();


            request.setPasID(klant.getPasID());
            request.setPinAmount(50);

            client.withdraw(request);


            // open print scherm
            print = true;
            BonPrintScherm p = new BonPrintScherm(request);
            doneren = false;
        }
        if(value.equals("2") && doneren == true)
        {
            value = "";
            //open print scherm
            print = true;
            BonPrintScherm p = new BonPrintScherm(request);
            doneren = false;
        }

    }

    public static void bonVraagScherm()
    {
        if (value.equals("1") && print == true)
        {
            value = "";
            System.out.println("ik ga nu printen");
            Printer a = new Printer(klant, bonAfschrijving);
            a.printSettings();
            print = false;

            fijneDagScherm();
        }
        if (value.equals("2") && print == true) {
            value = "";
            print = false;

            fijneDagScherm();



        }
    }

    public static void fijneDagScherm()
    {
        FijneDagScherm f = new FijneDagScherm();
        fijnedag = true;


        timer(5000);



    }

    public static void timer(int i)
    {
        try
        {
            Thread.sleep(i);
            open = true;
            startOpnieuw();


        } catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void simpeTimer(int i)
    {
        try
        {
            Thread.sleep(i);
            open = true;
            rfidInput= "";
            cancelScherm();
//            start();



        } catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void cancelScherm()
    {
        CancelMelding c = new CancelMelding();
        timer(3000);
    }

    public static void testPrints()
    {
//        System.out.println(klant.getGeblokkeerd());


    }

    public static void blokkeerScherm(int bankID, String pasID, String pincode)
    {


    }





}

