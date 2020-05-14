package lesson_3.Task4;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        SomeObject object;

        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            Socket client = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            object = (SomeObject) objectInputStream.readObject();

            object.info();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
