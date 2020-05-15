package lesson_3;

import java.io.*;
import java.util.*;

public class Task2 {
    private static final String path = "files/lesson_3/Task2/";

    public static void main(String[] args) {

        List<InputStream> streamList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            try {
                streamList.add(new FileInputStream(new File(path + "file" + i)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        SequenceInputStream inputStream = new SequenceInputStream(Collections.enumeration(streamList));

        File resultFile = new File(path + "resultFile");

        try (FileOutputStream outputStream = new FileOutputStream(resultFile)) {
            if (!resultFile.exists()) {
                resultFile.createNewFile();
            }
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
