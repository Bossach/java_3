package lesson_1.Task_3;

import lesson_1.Task_3.Fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> exactlyBox = new ArrayList<>();

    public Box(){

    }

    public float getWeight() {
        if (exactlyBox.size() == 0) return 0f;
        return exactlyBox.get(0).getWeight() * exactlyBox.size();
    }

    //сравниваем с любой коробкой
    public boolean compare(Box<?> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    //перекладываем по количеству
    public void putToAnotherBox(Box<T> anotherBox, int quantity) {
        anotherBox.add(this.getMulti(quantity));
    }

    //перекладываем все
    public void putToAnotherBox(Box<T> anotherBox) {
        putToAnotherBox(anotherBox, exactlyBox.size());
    }


    public void add(T fruit) {
        exactlyBox.add(fruit);
    }

    public void add(List<T> fruits) {
        exactlyBox.addAll(fruits);
    }

    //все get происходят с удалением фрукта из коробки

    //забираем крайний фрукт
    public T get(){
        return get(exactlyBox.size() - 1);
    }

    //забираем конкретный фрукт
    public T get(int i) {
        if (i < 0 || i >= exactlyBox.size()) {
            System.out.println("Нет такого фрукта");
            return null;
        }
        T tmp = exactlyBox.get(i);
        exactlyBox.remove(i);
        return tmp;
    }

    //забираем несколько
    public List<T> getMulti(int i) {
        if (i < 0) i = 0;
        if (i > exactlyBox.size()) i = exactlyBox.size();
        List<T> toGive = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            toGive.add(get());
        }
        return toGive;
    }

}
