package nl.project34.henk.client;

import nl.project34.henk.api.BalanceRequest;

/**
 * Created by Jelle on 19/03/16.
 */
public class ClientDatabase
{
    private MyClient client = new MyClient();

    private String pasID;

    //BalanceResponse(String rekeningNummer)
    private Long saldo;

    //OverigeRekeningenResponse(String klantID)
    private long overigeRekeningen;

    //TransactieResponse(String rekeningNummer)
    private String transactieNummer;
    private String transactieKlantID;
    private String transactieRekeningNummer;
    private String bedrag;
    private String datum;

    //RekeningOverzichtResponse(String pasID)
    private String rekeningNummer;
    private String klantID;
    private String werkelijkePin;
    private boolean   geblokkeerd;

    //KlantResponse(String klantID)
    private String bsn;
    private String naam;
    private String postcode;
    private String huisnummer;
    private String geboortedatum;
    private String geslacht;

    public ClientDatabase(String pasID)
    {
        this.pasID = pasID;
        BalanceRequest balanceRequest = new BalanceRequest(pasID);


        saldo                       = client.balance(balanceRequest).getBalance();

//
//        transactieNummer            = client.transactie(rekeningNummer).getTransactieNummer();
//        transactieKlantID           = client.transactie(rekeningNummer).getKlantID();
//        transactieRekeningNummer    = client.transactie(rekeningNummer).getRekeningNummer();
//        bedrag                      = client.transactie(rekeningNummer).getBedrag();
//        datum                       = client.transactie(rekeningNummer).getDatum();
//
//        bsn                         = client.klant(klantID).getBsn();

//        postcode                    = client.klant(klantID).getPostcode();
//        huisnummer                  = client.kla1nt(klantID).getHuisnummer();
//        geboortedatum               = client.klant(klantID).getGeboortedatum();
//        geslacht                    = client.klant(klantID).getGeslacht();
    }

    public MyClient getClient() {
        return client;
    }

    public void setClient(MyClient client) {
        this.client = client;
    }

    public String getPasID() {
        return pasID;
    }

    public void setPasID(String pasID) {
        this.pasID = pasID;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public long getOverigeRekeningen() {
        return overigeRekeningen;
    }

    public void setOverigeRekeningen(long overigeRekeningen) {
        this.overigeRekeningen = overigeRekeningen;
    }

    public String getTransactieNummer() {
        return transactieNummer;
    }

    public void setTransactieNummer(String transactieNummer) {
        this.transactieNummer = transactieNummer;
    }

    public String getTransactieKlantID() {
        return transactieKlantID;
    }

    public void setTransactieKlantID(String transactieKlantID) {
        this.transactieKlantID = transactieKlantID;
    }

    public String getTransactieRekeningNummer() {
        return transactieRekeningNummer;
    }

    public void setTransactieRekeningNummer(String transactieRekeningNummer) {
        this.transactieRekeningNummer = transactieRekeningNummer;
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

    public String getPincode() {
        return werkelijkePin;
    }

    public void setPincode(String werkelijkePin) {
        this.werkelijkePin = werkelijkePin;
    }
//
//    public long getGeblokkeerd() {
//        return geblokkeerd;
//    }
//
//    public void setGeblokkeerd(long geblokkeerd) {
//        this.geblokkeerd = geblokkeerd;
//    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }
}
