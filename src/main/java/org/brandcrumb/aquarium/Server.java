package org.brandcrumb.aquarium;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Server {

    private static final String BASE_API_URI = "http://localhost:8080/";


    public HttpHandler getHttpHandler() {
        return new CLStaticHttpHandler(Server.class.getClassLoader(), "webapp/");
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        final HttpServer httpServer = server.startServer();
        System.out.println("Press enter to stop the server...");
        System.in.read();
        httpServer.shutdown();
    }

    public HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("org.brandcrumb.aquarium");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_API_URI), rc);
        server.getServerConfiguration().addHttpHandler(getHttpHandler(), "/page");
        return server;
    }



}