package nl.project34.henk.api;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Jelle on 19/03/16.
 */
public class RekeningOverzichtResponse
{
    @JsonProperty
    private String rekeningNummer;

    @JsonProperty
    private String klantID;

    @JsonProperty
    private long geblokkeerd;

    @JsonProperty
    private String pinCode;

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public void setRekeningNummer(String rekeningNummer) {
        this.rekeningNummer = rekeningNummer;
    }

    public String getKlantID() {
        return klantID;
    }

    public void setKlantID(String klantID) {
        this.klantID = klantID;
    }

    public long getGeblokkeerd() {
        return geblokkeerd;
    }

    public void setGeblokkeerd(long geblokkeerd) {
        this.geblokkeerd = geblokkeerd;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
