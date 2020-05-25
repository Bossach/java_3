package lesson_6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestTask1 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new int[][][]{
                        {{5, 6}, {1, 2, 3, 4, 5, 6}, {}},
                        {{3, 5, 6}, {4, 1, 4, 3, 5, 6}, {2, 2, 3}},
                        {{}, {4, 4, 4, 4}, {1, 1, 1, 1}},
                        {{}, {4}, {0, 0, -1}}
                }
        );
    }

    private int[] res;
    private int[] src;
    private int[] exp;

    public TestTask1(int[] res, int[] src, int[] exp) {
        this.res = res;
        this.src = src;
        this.exp = exp;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(res, Task1.method(src));
    }

    @Test(expected = RuntimeException.class)
    public void testException() {
        Assert.assertArrayEquals(new int[]{}, Task1.method(exp));
    }
}
