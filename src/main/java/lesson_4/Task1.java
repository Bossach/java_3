package lesson_4;

public class Task1 {
    private static final Object lock = new Object();
    private static final String first = "A";
    private static String current;

    private static final int count = 5;

    public static void main(String[] args) {

        current = first;

        new Thread(() -> print("A", "B")).start();

        new Thread(() -> print("B", "C")).start();

        new Thread(() -> print("C", "A")).start();
    }

    public static void print(String cur, String next) {
        synchronized (lock) {
            try {
                for (int i = 0; i < count; i++) {
                    while (!cur.equals(current)) {
                        lock.wait();
                    }
                    System.out.print(cur);
                    current = next;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
