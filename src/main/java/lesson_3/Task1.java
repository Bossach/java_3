package lesson_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        File file = new File("files/lesson_3/Task1/50byte");
        byte[] arr = null;

        try (FileInputStream inputStream = new FileInputStream(file)) {
            arr = inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(arr));

    }
}
