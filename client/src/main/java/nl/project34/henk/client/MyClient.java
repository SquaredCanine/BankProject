package nl.project34.henk.client;



import nl.project34.henk.api.*;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Created by Elvira on 09-03-16.
 */



public class MyClient
{
    private Client client;
    private String target = "http://145.24.222.141:8025";
   // private String target = "145.24.222.208:8025";

    public MyClient()
    {
        javax.ws.rs.client.ClientBuilder cBuilder = javax.ws.rs.client.ClientBuilder.newBuilder();
        cBuilder.register(JacksonFeature.class);
        client  = cBuilder.build();

        this.client = ClientBuilder.newBuilder().build();

    }


    public WithdrawResponse withdraw(WithdrawRequest request)
    {
        WithdrawResponse response = client
                .target(target)
                .path("/withdraw")
                .request()
                .post(Entity.entity(request, MediaType.APPLICATION_JSON), WithdrawResponse.class);

        return response;
    }

    public BalanceResponse balance(BalanceRequest request)
    {

        BalanceResponse response = client
                .target(target)
                .path("/balance")
                .request()
                .post(Entity.entity(request, MediaType.APPLICATION_JSON), BalanceResponse.class);

        return response;
    }

    public CheckPasResponse checkPas(CheckPasRequest request)
    {
        CheckPasResponse response = null;

            try
            {
                response = client
                        .target(target)
                        .path("/checkpas/" )
                        .request()
                        .post(Entity.entity(request, MediaType.APPLICATION_JSON), CheckPasResponse.class);
            }
            catch (Exception e)
            {
                System.out.println("Check pass fail");
            }
        return response;
    }

    public CheckPinResponse checkPin(CheckPinRequest request)
    {
        CheckPinResponse response = null;

        try
        {
            response = client
                    .target(target)
                    .path("/checkpin/" )
                    .request()
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON), CheckPinResponse.class);
        }
        catch (Exception e)
        {
            System.out.println("error: " + e);
        }
        System.out.println(response.getBlocked());
        System.out.println(response.getCorrect());
        return response;
    }

    public TransactieResponse transactie(String rekeningNummer)
    {
        TransactieResponse response = null;
        try
        {
            response = client
                    .target(target)
                    .path("/transactie/" + rekeningNummer)
                    .request()
                    .get(TransactieResponse.class);


        }
        catch (Exception e)
        {
            System.out.println("TransactieResponse Failure");
        }
        return response;
    }

    public OverigeRekeningenResponse overigeRekeningen(String klantID)
    {
        OverigeRekeningenResponse response = null;
        try
        {
            response = client
                    .target(target)
                    .path("/overigerekeningen/" + klantID)
                    .request()
                    .get(OverigeRekeningenResponse.class);
        }
        catch(Exception e)
        {
            System.out.println("OverigeRekeningenResponse Failure.");
        }

        return response;
    }

    public RekeningOverzichtResponse rekeningOverzicht(String pasID)
    {
        RekeningOverzichtResponse response = null;
        try
        {
            response = client
                    .target(target)
                    .path("/rekeningoverzicht/" + pasID)
                    .request()
                    .get(RekeningOverzichtResponse.class);
        }
        catch(Exception e)
        {
            System.out.println("RekeningOverzichtResponse Failure.");
        }

        return response;
    }



    public GeblokkeerdResponse geblokkeerdResponse(GeblokkeerdRequest request)
    {
        GeblokkeerdResponse response = null;
        try
        {
            response = client
                    .target(target)
                    .path("/blokkeer/")
                    .request()
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON), GeblokkeerdResponse.class);
        }
        catch (Exception e)
        {
            System.out.println("GeblokkeerdResponse Failure");
        }
        return response;
    }


}
