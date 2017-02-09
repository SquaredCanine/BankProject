package nl.project34.henk.server;

import nl.project34.henk.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Elvira on 18-02-16.
 */
@Path("/")
public class BankEndpoint
{
    private static final Logger logger = LoggerFactory.getLogger(BankEndpoint.class);
    public static final int BANKID = 0;

    /*
     * Mogelijke Http methodes:
     * GET - get something from the server
     * POST - create new on the server
     * PUT - update something n the server
     * DELETE - delete something fro the server
     */

    @POST
    @Path ("/withdraw")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public WithdrawResponse withdraw(WithdrawRequest request)
    {
        logger.trace("In de BankEndpoint::withdraw");

        WithdrawResponse response = new WithdrawResponse();

        Database db = Server.getDatabase();
        if (db.withdraw(request.getPasID(), request.getPinAmount()))
        {
            response.setSucceeded(true);
            response.setTransactieNummer(12345L); // dummy natuurlijk, hier moeten jullie zelf iets beters op verzinnen....
            logger.info("response: " + response.isSucceeded());
            return response;
        }
        else
        {
            response.setSucceeded(false);
            logger.error("response: " + response.isSucceeded());
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity(response).build());
        }
    }


    @POST
    @Path("checkpas") // Check if pas bestaat
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public CheckPasResponse checkPas(CheckPasRequest request) {
        if (request.getBankID() != BANKID) {
            // sent to other bank.
        }
        CheckPasResponse response = new CheckPasResponse(true);
        return response;
    }


    @POST
    @Path("checkpin")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public CheckPinResponse checkPinCorrect(CheckPinRequest request)
    {
        CheckPinResponse response = new CheckPinResponse();
        if (request.getBankID() != BANKID)
        {
            // send to other bank.
        }
        response.setBlocked(false);
        Database db = Server.getDatabase();
        System.out.println("pincode: " + request.getPinCode());
        System.out.println("pincode van database: " + db.getPincode(request.getPasID()));
        if(request.getPinCode().equals(db.getPincode(request.getPasID())))
        {
            response.setCorrect(true);
            db.setPogingen(0, request.getPasID());
        }
        else
        {
            response.setCorrect(false);
            db.setPogingen((db.getPogingen(request.getPasID()) + 1),request.getPasID());
            if(db.getPogingen(request.getPasID()) == 3)
            {
              db.blokkeer(request.getPasID());
                response.setBlocked(true);
            }
        }
       System.out.println(response.getBlocked());
       System.out.println(response.getCorrect());
       return response;
    }



    @POST
    @Path ("/balance")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public BalanceResponse balance(BalanceRequest request)
    {
        logger.trace("In de BankEndpoint::withdraw");
        if (request.getBankID() != BANKID) {
            // sent to other bank.
        }

        Database db = Server.getDatabase();
        BalanceResponse response = new BalanceResponse(db.getBalance((request.getPasID())));

        return response;

    }

    @POST
    @Path ("/transactie/{rekeningNummer}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TransactieResponse getTransactie(@PathParam("rekeningNummer") String rekeningNummer)
    {
        Database db = Server.getDatabase();
        logger.trace("In de BankEndpoint::getTransactie");

        return db.getTransactie(rekeningNummer);
    }

    @GET
    @Path ("/overigerekeningen/{klantID}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public long getOverigeRekeningen(@PathParam("klantID") String klantID)
    {
        Database db = Server.getDatabase();
        logger.trace("In de BankEndpoint::getOverigeRekeningen");
        return db.getOverigeRekeningen(klantID);
    }

    @POST
    @Path ("/rekeningoverzicht/{pasID}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public RekeningOverzichtResponse getRekeningOverzicht(@PathParam("pasID") String pasID)
    {
        Database db = Server.getDatabase();
        logger.trace("In de BankEndpoint::getRekeningOverzicht");
        return db.getRekeningOverzicht(pasID);
    }

    @POST
    @Path ("/blokkeer")
    @Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
    public GeblokkeerdResponse blokkeer(GeblokkeerdRequest request)
    {
        logger.error("In de BankEndpoint::blokkeer");

        GeblokkeerdResponse response = new GeblokkeerdResponse();

        Database db = Server.getDatabase();
        if(db.blokkeer(request.getPasID()))
        {
            response.setResponse("Pas Geblokkeerd.");
            logger.error("response: " + response.getResponse());
            return response;
        }
        else
        {
            response.setResponse("Pas blokkeren mislukt.");
            logger.error("response: " + response.getResponse());
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity(response).build());
        }
    }




}