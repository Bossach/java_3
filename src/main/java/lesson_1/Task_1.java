package lesson_1;

import java.util.Arrays;

public class Task_1 {
    public static void main(String[] args) {

        String[] strArray = {"first", "second", "third", "last"};

        Integer[] intArray = {1, 2, 3, 4};

        class IntHolder {
            private int value;

            public IntHolder(int a) {
                value = a;
            }

            @Override
            public String toString() {
                return "I'm " + value + "!";
            }
        }


        IntHolder[] holders = {new IntHolder(33), new IntHolder(66), new IntHolder(128)};


        swap(strArray, 1, 3);
        System.out.println(Arrays.toString(strArray));

        swap(intArray, 0, 1);
        System.out.println(Arrays.toString(intArray));


        swap(holders, 1, 2);
        System.out.println(Arrays.toString(holders));
    }

    public static <T> void swap(T[] array, int index1, int index2) {

        if (index1 < 0 || index2 < 0 || index1 >= array.length || index2 >= array.length) {
            System.err.println("Invalid index");
            return;
        }
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
