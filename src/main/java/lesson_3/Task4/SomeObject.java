package lesson_3.Task4;

import java.io.Serializable;

public class SomeObject implements Serializable {
    private int field1;
    private int field2;

    public SomeObject(int field1, int field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public int method() {
        return field1 + field2;
    }

    public void info() {
        System.out.println("Some object: " + field1 + " + " + field2 + " = " + method());
    }
}
