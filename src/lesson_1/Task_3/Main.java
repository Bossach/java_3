package lesson_1.Task_3;

import lesson_1.Task_3.Fruits.Apple;
import lesson_1.Task_3.Fruits.Orange;

public class Main {
    public static void main(String[] args) {

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();


        for (int i = 0; i < 5; i++) {
            appleBox1.add(new Apple());
        }

        for (int i = 0; i < 3; i++) {
            appleBox2.add(new Apple());
        }

        for (int i = 0; i < 4; i++) {
            orangeBox.add(new Orange());
        }

        System.out.println("Apple box one weight: " + appleBox1.getWeight());
        System.out.println("Apple box two weight: " + appleBox2.getWeight());
        System.out.println("Orange box weight: " + orangeBox.getWeight());

        System.out.println("Compare apple box one with two: " + appleBox1.compare(appleBox2));

        System.out.println("Placing one apple from box two to box one...");
        appleBox2.putToAnotherBox(appleBox1, 1);

        System.out.println("Apple box one new weight: " + appleBox1.getWeight());

        System.out.println("Compare apple box one with orange box: " + appleBox1.compare(orangeBox));


    }
}
