package lesson_3;

import java.io.*;
import java.util.Scanner;

public class Task3 {
    private final static int PAGE_SIZE = 1800;
    private static RandomAccessFile file;


    public static void main(String[] args) {
        File filename = new File("files/lesson_3/Task3/text.txt");

        try ( RandomAccessFile tempFile = new RandomAccessFile(filename, "r")) {
            file = tempFile;

            Scanner scan = new Scanner(System.in);

            int pageNumber = 0;
            String command;

            while (true) {
                System.out.print("Type page or next/prev/this/exit : ");
                command = scan.nextLine();
                switch (command.toLowerCase()) {
                    case "exit":
                        file.close();   //Закроется ли ресурс автоматически если вызвать System.exit() внутри блока try-with-resources?
                        System.exit(0);
                    case "next":
                        pageNumber++;
                        break;
                    case "prev":
                        pageNumber--;
                        break;
                    case "this":
                        break;
                    default:
                        try {
                            pageNumber = Integer.parseInt(command);
                        } catch (NumberFormatException e) {
                            System.out.println("Unknown command.");
                            continue;
                        }
                }

                if (pageNumber < 0) pageNumber = 0;
                System.out.println("Page " + pageNumber + ":");
                System.out.println(readPage(pageNumber));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String readPage(int page) {
        int startByte = page * PAGE_SIZE;

        byte[] arr = new byte[PAGE_SIZE];

        try {
            file.seek(startByte);
            file.read(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = new StringBuilder();

        for (byte b : arr) {
            if (b != 0) {
                result.append((char) b);
            } else break;
        }

        return result.toString();
    }
}
