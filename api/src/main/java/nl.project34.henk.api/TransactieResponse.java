package nl.project34.henk.api;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Martijn on 24-03-16.
 */
public class TransactieResponse
{
    @JsonProperty
    private String transactieNummer;

    @JsonProperty
    private String klantID;

    @JsonProperty
    private String rekeningNummer;

    @JsonProperty
    private String bedrag;

    @JsonProperty
    private String datum;

    public String getTransactieNummer() {
        return transactieNummer;
    }

    public void setTransactieNummer(String transactieNummer) {
        this.transactieNummer = transactieNummer;
    }

    public String getKlantID() {
        return klantID;
    }

    public void setKlantID(String klantID) {
        this.klantID = klantID;
    }

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public void setRekeningNummer(String rekeningNummer) {
        this.rekeningNummer = rekeningNummer;
    }

    public String getBedrag() {
        return bedrag;
    }

    public void setBedrag(String bedrag) {
        this.bedrag = bedrag;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

}
