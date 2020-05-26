package lesson_7.Task1;

import lesson_7.Task1.annotations.AfterSuite;
import lesson_7.Task1.annotations.BeforeSuite;
import lesson_7.Task1.annotations.Test;

public class Tests {


    @BeforeSuite
    public void before() {
        System.out.println("before");
    }

    @AfterSuite
    public void after() {
        System.out.println("after");
    }

    @Test(priority = 3)
    public void test0() {
        System.out.println(3);
    }

    @Test(priority = 5)
    public void test1() {
        System.out.println(5);
    }

    @Test(priority = 1)
    public void test2() {
        System.out.println(1);
    }

}
