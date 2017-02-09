package nl.project34.henk.server;

import java.net.URI;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Elvira on 18-02-16.
 */
public class Server
{
    private static final Logger logger = LoggerFactory.getLogger(DatabaseImpl.class);
    private int port = 8025;
    private static Database DB_INSTANCE;

    public Server() throws Exception
    {
        HttpServer server = initWebserver();
        server.start();
        logger.info("Server is on, on port " + port);

        while (true)
        {
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws Exception
    {
        DB_INSTANCE = (args.length == 1 && args[0].equals("--mock-db"))
                ? new MockDatabase()
                : new DatabaseImpl();
        new Server();
    }

    public static Database getDatabase()
    {
        return DB_INSTANCE;
    }



    private HttpServer initWebserver()
    {
        ResourceConfig config = new ResourceConfig(BankEndpoint.class);
        config.register(JacksonJaxbJsonProvider.class);
        URI uri = URI.create("http://0.0.0.0:"+ port);


        return GrizzlyHttpServerFactory.createHttpServer(uri, config, true);

    }
}