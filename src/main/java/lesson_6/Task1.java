package lesson_6;

import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {

    }

    public static int[] method(int[] srcArray) {
        int pos = 0;
        boolean fourExist = false;
        for (int i = 0; i < srcArray.length; i++) {
            if (srcArray[i] == 4) {
                fourExist = true;
                pos = i;
            }
        }
        if (!fourExist) throw new RuntimeException();
        return Arrays.copyOfRange(srcArray, pos + 1, srcArray.length);
    }
}
