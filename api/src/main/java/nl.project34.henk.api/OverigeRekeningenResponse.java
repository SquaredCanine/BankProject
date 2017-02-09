package nl.project34.henk.api;

/**
 * Created by Martijn on 18/03/16.
 */
public class OverigeRekeningenResponse
{
    private final long overigeRekeningen;

    public OverigeRekeningenResponse(long overigeRekeningen)
    {
        this.overigeRekeningen = overigeRekeningen;
    }

    public long getOverigeRekeningen()
    {
        return overigeRekeningen;
    }
}

