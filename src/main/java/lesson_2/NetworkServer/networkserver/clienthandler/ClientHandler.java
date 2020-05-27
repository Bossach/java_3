package lesson_2.NetworkServer.networkserver.clienthandler;


import lesson_2.NetworkServer.networkserver.MessageConstant;
import lesson_2.NetworkServer.networkserver.MyServer;
import lesson_2.NetworkServer.networkserver.auth.AuthService;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private static final long AUTH_TIMEOUT = 120000;
    private static final Logger logger = Logger.getLogger(lesson_2.NetworkServer.networkserver.clienthandler.ClientHandler.class);
    private final MyServer serverInstance;
    private final AuthService authService;
    private final Socket clientSocket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String nickname;


    public ClientHandler(Socket clientSocket, MyServer myServer) {
        this.clientSocket = clientSocket;
        this.serverInstance = myServer;
        this.authService = serverInstance.getAuthService();
    }

    public void handle() throws IOException {
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                readMessages();
            } catch (IOException e) {
                logger.info("Client connection was dropped");
            } finally {
                closeConnection();
            }
        }).start();
    }

    private void closeConnection() {
        serverInstance.unsubscribe(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(MessageConstant.END_CMD)) {
                return;
            } else if (message.startsWith(MessageConstant.PRIVATE_CMD)) {
                serverInstance.privateMessage(message, this);
            } else {
                serverInstance.broadcastMessage(String.format("%s: %s", nickname, message));
            }
        }
    }

    private void authentication() throws IOException {
        Thread timer = new Thread(() -> {
            try {
                Thread.sleep(AUTH_TIMEOUT);
                try {
                    sendMessage("Authentication timeout expired");
                } catch (IOException ignored) {
                } finally {
                    closeConnection();
                }
            } catch (InterruptedException ignored) { }
        });
        timer.start();

        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(MessageConstant.AUTH_CMD)) {
                String[] parts = message.split("\\s+");
                String login = parts[1];
                String password = parts[2];

                String nickname = authService.getNickByLoginAndPassword(login, password);
                if (nickname == null) {
                    sendMessage("Неверные логин/пароль!");
                }
                else if (serverInstance.isNicknameBusy(nickname)) {
                    sendMessage("Учетная запись уже используется!");
                }
                else {
                    timer.interrupt();
                    sendMessage(String.format("%s %s", MessageConstant.AUTH_SUCCESSFUL_CMD, nickname));
                    setNickname(nickname);
                    serverInstance.broadcastMessage(nickname + " Зашел в чат!");
                    serverInstance.subscribe(this);
                    break;
                }
            }
        }
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
    }
}
