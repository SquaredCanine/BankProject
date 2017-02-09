package nl.project34.henk.server;

import nl.project34.henk.api.RekeningOverzichtResponse;
import nl.project34.henk.api.TransactieResponse;

/**
 * Created by Elvira on 03-03-16.
 *
 * Class is aangemaakt om de code zonder database op de locale machine te testen.
 * Wanneer gebruik wordt gemaakt van de locale test, dient bij
 * Run -> Edit configurations ->PRogram arguments "--mock-db" vermeld te worden
 *
 */
public class MockDatabase implements Database
{
    @Override
    public long getBalance(String rekeningNummer)
    {
        return 0;
    }

    @Override
    public boolean withdraw(String rekeningNummer, long amount)
    {
        return false;
    }

    @Override
    public boolean blokkeer(String pasID)
    {
        return false;
    }

    @Override
    public TransactieResponse getTransactie(String rekeningNummer)
    {
        return null;
    }

    @Override
    public long getOverigeRekeningen(String klantID)
    {
        return 0;
    }

    @Override
    public String getRekeningnummer(String pasID)
    {
        return null;
    }

    @Override
    public boolean getGeblokkeerd(String pasID)
    {
        return false;
    }

    @Override
    public RekeningOverzichtResponse getRekeningOverzicht(String pasID)
    {
        return null;
    }


    @Override
    public String getPincode(String pasID)
    {
        return null;
    }

    @Override
    public int getPogingen(String pasID)
    {
        return 0;
    }

    @Override
    public void setPogingen(int pogingen, String pasID)
    {

    }
}
