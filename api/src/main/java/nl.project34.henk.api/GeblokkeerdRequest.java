package nl.project34.henk.api;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Martijn on 07-04-16.
 */
public class GeblokkeerdRequest
{
    @JsonProperty
    private String pasID;

    public String getPasID()
    {
        return pasID;
    }

    public void setPasID(String pasID)
    {
        this.pasID = pasID;
    }
}