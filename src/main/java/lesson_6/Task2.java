package lesson_6;

public class Task2 {
    public static void main(String[] args) {

    }

    public static boolean method(int[] srcArray){
        boolean oneExist = false;
        boolean fourExist = false;
        for (int i : srcArray) {
            if (i == 1) oneExist = true;
            if (i == 4) fourExist = true;
        }
        return oneExist && fourExist;
    }
}
