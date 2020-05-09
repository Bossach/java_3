package lesson_2.NetworkServer;


import lesson_2.NetworkServer.networkserver.MyServer;

public class ServerApp {

    private static final int PORT = 8189;

    public static void main(String[] args) {
        MyServer server = new MyServer(8189);
        server.start();
    }
}
