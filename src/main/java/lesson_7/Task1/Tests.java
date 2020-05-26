package lesson_7.Task1;

import lesson_7.Task1.annotations.AfterSuite;
import lesson_7.Task1.annotations.BeforeSuite;
import lesson_7.Task1.annotations.Test;

public class Tests {


    @BeforeSuite
    public void before() {
        System.out.println("There is before");
    }

    @AfterSuite
    public void after() {
        System.out.println("There is after");
    }

    @Test(priority = 3)
    public void test1() {
        System.out.println("Test 1 (priority 3): ");
        MyAssert.test(1, 1);
    }

    @Test
    public void test2() {
        System.out.println("Test 2 (priority 5(default)): ");
        MyAssert.test(4, new TestSubject().add(2, 2));
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("Test 3 (priority 1): ");
        MyAssert.test(TestSubject.getOddInt(), (i) -> i % 2 == 0);
    }

    @Test(priority = 10)
    public void test4() {
        System.out.println("Test 4 (priority 10): ");
        MyAssert.test(new TestSubject().add(3, 3) == 6);
    }

}
