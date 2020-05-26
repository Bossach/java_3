package lesson_7.Task1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class MyAssert {
    public static <T, U> void test(T o1, U o2, BiPredicate<T, U> predicate) {
        if (predicate.test(o1, o2)) {
            pass();
        } else {
            fail();
        }
    }

    public static <T> void test(T o1, Predicate<T> predicate) {
        if (predicate.test(o1)) {
            pass();
        } else {
            fail();
        }
    }

    public static void test(Object o1, Object o2) {
        if (o1.equals(o2)) {
            pass();
        } else {
            fail();
        }
    }
    public static void test(boolean b) {
        if (b) pass(); else fail();
    }


    public static void pass() {
        System.out.println("Passed!");
    }

    public static void fail() {
        System.out.println("Failed!");
    }
}
