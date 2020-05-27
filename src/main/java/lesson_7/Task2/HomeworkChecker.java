package lesson_7.Task2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class HomeworkChecker {


    private List<Task> checkers;
    private List<File> works;
    private String dir;

    public HomeworkChecker(String workDirectory, Task[] tasks) {
        this.dir = workDirectory;
    }

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("calculate", int.class, new Class[]{int.class, int.class, int.class, int.class},
                new Predicate<>() {
                    @Override
                    public boolean test(Object o) {
                        if (o = )
                        return false;
                    }
                }, new Integer[][]{}));



        HomeworkChecker checker = new HomeworkChecker("target/classes/lesson_7/Task2/HomeworksToCheck");

        checker.startCheck();
    }

    public void startCheck() {

        File fDir = new File(dir);

        works = new ArrayList<>();

        for (String filename : Objects.requireNonNull(fDir.list((file, s) -> s.endsWith(".class")))) {
            check(filename);
        }
    }

    private void check(String filename) {

        //
        String className = filename.substring(0, filename.length() - 6);
        String filePath = dir + "/" + filename;
        File file = new File(filePath);
        //


        Class<?> workClass;
        try {
            workClass = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(className);
            //Вылетает ClassNotFoundException. Путь коррекен, файл существует, проверял. Имя классса тоже совпадает
        } catch (ClassNotFoundException | MalformedURLException e) {
            System.err.println(filename + " : can't load class");
            e.printStackTrace();
            return;
        }

        for (Task checker : checkers) {
            checker.check(workClass);
        }

    }
}
