package main;

import gateway.server.HttpServer;

public class NettyServerApplication {
    public static void main(String[] args) {
        String backServer = System.getProperty("backServer", "http://localhost:8808/test");
        String proxyPort = System.getProperty("proxyPort", "8888");

        Integer port = Integer.parseInt(proxyPort);
        System.out.println("NIOGateway server starting...");
        HttpServer httpServer = new HttpServer(backServer, port);
        System.out.println("NIOGateway server started at http://localhost:" + port + "for " + backServer);
        try{
            httpServer.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
