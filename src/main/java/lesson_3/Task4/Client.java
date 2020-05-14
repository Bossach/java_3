package lesson_3.Task4;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        SomeObject object = new SomeObject(3, 5);

        try (Socket socket = new Socket("localhost", 6789);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeObject(object);
            System.out.println("Object sended.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
