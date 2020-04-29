package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task_2 {
    public static void main(String[] args) {

        String[] array = {"one", "two", "three"};
        //Integer[] array = {1, 2, 3};
        List list = toArrayList(array);

        System.out.println(list.get(0).getClass());
        System.out.println(list);

    }

    public static <T> ArrayList<T> toArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));

//        Ехал ArrayList через array
//        Видит ArrayList в array'е List
//        Cунул ArrayList <T> в array
//        List ArrayList'а за <T> return;
    }
}
