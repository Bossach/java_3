package lesson_2.NetworkServer.networkserver;

import lesson_2.NetworkServer.networkserver.auth.AuthService;
import lesson_2.NetworkServer.networkserver.auth.BaseAuthService;
import lesson_2.NetworkServer.networkserver.clienthandler.ClientHandler;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final int port;
    private final List<ClientHandler> clients;
    private final AuthService authService;
    private final Logger logger = Logger.getLogger(getClass());

    public MyServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.authService = new BaseAuthService();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Starting server");
            authService.start();

            //noinspection InfiniteLoopStatement
            while (true) {
                logger.info("Waiting for client connection...");
                Socket clientSocket = serverSocket.accept();
                logger.info("Client has been connected");
                ClientHandler handler = new ClientHandler(clientSocket, this);
                try {
                    handler.handle();
                } catch (IOException e) {
                    logger.warn("Failed to handle client connection!");
                    clientSocket.close();
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNicknameBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public synchronized void privateMessage(String message, ClientHandler sender) throws IOException {
        String[] splittedMessage = message.split(" ", 3);
        if (splittedMessage.length < 3) {
            sender.sendMessage("Server: Invalid private message");
            return;
        }

        String receiverNickname = splittedMessage[1];
        String messageBody = splittedMessage[2];

        ClientHandler receiver = getClientByNickname(receiverNickname);

        if (receiver == null) {
            sender.sendMessage("Server: There is no " + receiverNickname + " in chat");
        } else {
            receiver.sendMessage(String.format("%s: %s", sender.getNickname(), messageBody ));
        }



    }

    public synchronized ClientHandler getClientByNickname(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) return client;
        }
        return null;
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        logger.info("User " + clientHandler.getNickname() + " entered chat.");
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        logger.info("User " + clientHandler.getNickname() + " leaved chat.");
    }
}
