package lesson_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    private Car winner = null;

    public CountDownLatch startCountDown = new CountDownLatch(MainClass.CARS_COUNT);
    public CountDownLatch finishCountDown = new CountDownLatch(MainClass.CARS_COUNT);

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void tryWin(Car car) {
        if (winner == null) {
            winner = car;
        }
    }

    public Car getWinner() {
        return winner;
    }
}
