package nl.project34.henk.api;

import org.codehaus.jackson.annotate.JsonProperty;


public class GeblokkeerdResponse
{

    @JsonProperty
    private String response;

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }
}

