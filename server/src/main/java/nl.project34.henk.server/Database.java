package nl.project34.henk.server;

import nl.project34.henk.api.*;


/**
 * Created by Elvira on 03-03-16.
 */
public interface Database
{
    long getBalance(String rekeningNummer);

    boolean withdraw(String rekeningNummer, long amount);
    boolean blokkeer(String pasID);

    TransactieResponse getTransactie(String rekeningNummer);

    long getOverigeRekeningen(String klantID);

    String getRekeningnummer(String pasID);

    boolean getGeblokkeerd(String pasID);

    RekeningOverzichtResponse getRekeningOverzicht(String pasID);

    public String getPincode(String pasID);

    public int getPogingen(String pasID);

    public void setPogingen(int pogingen, String pasID);
}
