package nl.project34.henk.server;

import nl.project34.henk.api.RekeningOverzichtResponse;
import nl.project34.henk.api.TransactieResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Elvira on 18-02-16.
 */
public class DatabaseImpl implements Database
{
    private static final Logger logger = LoggerFactory.getLogger(DatabaseImpl.class);
    private Connection con = null;
    private String host = "jdbc:mysql://localhost:3306/Bankalicious";
    private String uName = "root";
    private String uPass = "Y45cAj";
    private ResultSet rs = null;
    private int ri = 0;


    public DatabaseImpl()
    {
        this.connect();
    }

    private void connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(host, uName, uPass);
            logger.info("Connection to database established");
        }
        catch(Exception e)
        {
            logger.error("Connection to database failed", e);
        }
    }


    @Override
    public long getBalance(String pasID)
    {
        long saldo = 0;
        System.out.println(pasID);

        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT saldo "
                            + "FROM Bankalicious.RekeningOverzicht AS ro, Bankalicious.Rekening AS r "
                            + "WHERE pasID = ? AND ro.rekeningnummer = r.rekeningnummer");

            ps.setString(1, pasID);

            rs = ps.executeQuery();

            rs.next(); //ga naar de eerste regel
            saldo = rs.getLong("saldo");
            logger.debug("Saldo: " + saldo);

        }
        catch (Exception e)
        {
            logger.error("execution of query select saldo failed", e);
        }

        return saldo;
    }

    public String getRekeningnummer(String pasID)
    {
        String rekeningNummer = "";

        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT rekeningNummer "
                    + "FROM Bankalicious.RekeningOverzicht "
                    + "WHERE pasID = ?");

            ps.setString(1, pasID);

            rs = ps.executeQuery();
            rs.next();

            rekeningNummer = rs.getString("rekeningNummer");
        }
        catch (Exception e)
        {
            logger.error("execution of query select saldo failed: ", e);
        }
        return rekeningNummer;
    }

    public boolean getGeblokkeerd(String pasID)
    {
        boolean geblokkeerd = false;
        int status = 0;

        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT geblokkeerd "
                    + "FROM  Bankalicious.RekeningOverzicht "
                    + "WHERE Bankalicious.RekeningOverzicht.pasID = ?");

            ps.setString(1, pasID);

            rs = ps.executeQuery();
            rs.next();

            status = rs.getInt("geblokkeerd");


            if(status == 1)
            {
                geblokkeerd = true;
            }

        }
        catch (Exception e)
        {
            logger.error("execution of query select saldo geblokkeerd: ", e);
        }
        return geblokkeerd;
    }

    public String getPincode(String pasID)
    {
        String pincode = "";
        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT pincode "
                            + "FROM Bankalicious.RekeningOverzicht "
                            + "WHERE Bankalicious.RekeningOverzicht.pasID = ?");

            ps.setString(1, pasID);

            rs = ps.executeQuery();
            rs.next();

            pincode = rs.getString("pincode");

        }
        catch (Exception e)
        {
            logger.error("execution of query select pinCode failed: ", e);
        }
        return pincode;
    }

    @Override
    public int getPogingen(String pasID)
    {

        int pogingen = 0;
        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT pogingen "
                    + "FROM Bankalicious.RekeningOverzicht "
                    + "Where Bankalicious.RekeningOverzicht.pasID = ?");

                    ps.setString(1, pasID);

            rs = ps.executeQuery();
            rs.next();

            pogingen = rs.getInt("pogingen");

        }
        catch (Exception e)
        {
            logger.error("execution of query select getPogingen failed: ", e);
        }

        return pogingen;
    }

    public void setPogingen(int pogingen, String pasID)
    {
        try
        {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE RekeningOverzicht "
                            + "SET pogingen=? "
                            + "WHERE Bankalicious.RekeningOverzicht.pasID = ?");

            ps.setInt(1, pogingen);
            ps.setString(2, pasID);

            ri = ps.executeUpdate();
            ri = 0;


        }
        catch (Exception e)
        {
            logger.error("execution of query select setPogingen failed: ", e);
        }
    }

    @Override
    public boolean withdraw(String pasID, long amount)
    {
        long saldo = 0;
        try
        {
            saldo = (getBalance(pasID));
            if(logger.isDebugEnabled())
            {
                logger.debug("Rekening nummer: {}\t saldo: {} ", pasID, saldo);
            }
            if(saldo > amount)
            {
                logger.debug("Saldo toereikend, rekeningnummer: {}\t saldo: {}", getRekeningnummer(pasID), saldo);
                PreparedStatement ps = con.prepareStatement("UPDATE Rekening SET saldo=? WHERE rekeningNummer=?");
                ps.setLong(1, (saldo - amount));
                ps.setString(2, getRekeningnummer(pasID));
                ps.executeUpdate();

                if(logger.isDebugEnabled())
                {
                    logger.debug("Nieuwe saldo: {}", getBalance(pasID));
                }
                return true;

            }
            logger.debug("Saldo ontoereikend, rekeningnummer: {}\t saldo: {}", getRekeningnummer(pasID), saldo);

        }
        catch (Exception e)
        {
            logger.error("execution of query withdraw failed", e);
        }
        return false;
    }

    @Override
    public TransactieResponse getTransactie(String rekeningNummer)
    {
        TransactieResponse response = new TransactieResponse();

        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * "
                            + "FROM Bankalicious.Transactie "
                            + "WHERE rekeningNummer = ? "
                            + "ORDER BY datum DESC");

            ps.setString(1, rekeningNummer);

            rs = ps.executeQuery();

            rs.next();
            response.setTransactieNummer(rs.getString("transactieNummer"));
            response.setKlantID(rs.getString("klantID"));
            response.setRekeningNummer(rs.getString("rekeningNummer"));
            response.setBedrag(rs.getString("bedrag"));
            response.setDatum(rs.getString("datum"));
        }

        catch(Exception e)
        {
            logger.error("Execution of query select Transactie failed." + e);
        }

        return response;
    }

    @Override
    public long getOverigeRekeningen(String klantID)
    {
        long overigeRekeningen = 0;

        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT COUNT(rekeningNummer) "
                            + "FROM Bankalicious.RekeningOverzicht "
                            + "WHERE Bankalicious.RekeningOverzicht.klantID = ?");

            ps.setString(1, klantID);

            rs = ps.executeQuery();

            rs.next(); //ga naar de eerste regel
            overigeRekeningen = rs.getLong("COUNT(rekeningNummer)");
            logger.debug("OverigeRekeningen: " + overigeRekeningen);
        }
        catch(Exception e)
        {
            logger.error("Execution of query select Overige Rekeningen failed.");
        }

        return overigeRekeningen;
    }

    @Override
    public RekeningOverzichtResponse getRekeningOverzicht(String pasID)
    {
        RekeningOverzichtResponse response = new RekeningOverzichtResponse();

        try
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * "
                            + "FROM Bankalicious.RekeningOverzicht "
                            + "WHERE pasID = ?");

            ps.setString(1, pasID);

            rs = ps.executeQuery();

            rs.next();
            response.setRekeningNummer(Encode.encoden(rs.getString("rekeningNummer")));
            response.setKlantID(Encode.encoden(rs.getString("klantID")));
            response.setGeblokkeerd(rs.getLong("geblokkeerd"));
            response.setPinCode(Encode.encoden(rs.getString("pinCode")));
        }
        catch(Exception e)
        {
            logger.error("Execution of query select RekeningOverzicht failed.");
        }

        return response;
    }



    @Override
    public boolean blokkeer(String pasID)
    {

        try
        {
            PreparedStatement ps = con.prepareStatement("UPDATE RekeningOverzicht SET geblokkeerd=1 WHERE pasID=?");
            ps.setString(1, pasID);
            ps.executeUpdate();

            return true;
        }
        catch(Exception e)
        {
            logger.error("execution of query blokkeer failed.", e);
        }

        return false;
    }
}