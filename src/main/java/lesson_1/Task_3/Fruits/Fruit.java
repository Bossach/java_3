package lesson_1.Task_3.Fruits;

public abstract class Fruit {
    //private abstract static final float WEIGHT;
    //Не даёт)

    public abstract float getWeight();

    //Не смог вынести сюда абсолютно одинаковый для потомков метод getWeight(),
    //тк метод родитея возвращает своё поле, а не поле потомка
    //Есть ли способ чтобы из метода родителя, вызванного из потомка возвращать поле потомка?
    //Или какой-нибудь другой способ не писать в каждом фркуте абсолютно одинаковый getWeight?
}
