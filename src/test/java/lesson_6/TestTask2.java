package lesson_6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class TestTask2 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new int[][][]{
                {new int[]{1, 2, 3, 4}, new int[]{0, 0, 7, 6, 7, 4, 5}},
                {new int[]{4, 4, 4, 1}, new int[]{0, 0, 0, 0, 0}},
                {new int[]{1, 1, 1, 1, 1, 7, 1, 4}, new int[]{}}
        });
    }

    private int[] trueData;
    private int[] falseData;

    public TestTask2(int[] trueData, int[] falseData) {
        this.trueData = trueData;
        this.falseData = falseData;
    }

    @Test
    public void trueTest() {
        Assert.assertTrue(Task2.method(trueData));
    }

    @Test
    public void falseTest() {
        Assert.assertFalse(Task2.method(falseData));
    }
}
