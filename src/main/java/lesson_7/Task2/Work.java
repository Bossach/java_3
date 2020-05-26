package lesson_7.Task2;

import java.util.ArrayList;
import java.util.List;

class Work {
    String filename;
    String className;
    List<TaskForWork> tasks;

    public Work(String filename) {
        this.filename = filename;
        this.className = filename.substring(0, filename.length() - 6);    //обрезаем ".class"
        tasks = new ArrayList<>();
    }

    private String bool2str(boolean bool) {
        return bool ? "correct" : "incorrect or can't check";
    }

    public String toString() {

        StringBuilder str = new StringBuilder("Student " + className + " :");

        for (TaskForWork task : tasks) {
            str.append("\n\t").append(task.name).append(" : ").append(bool2str(task.grade));
        }

        return str.toString();
    }
}
