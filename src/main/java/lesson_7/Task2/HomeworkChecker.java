package lesson_7.Task2;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeworkChecker {


    private List<TaskChecker> checkers;
    private List<Work> works;
    private String dir;

    public HomeworkChecker(String workDirectory) {
        this.dir = workDirectory;
    }

    public static void main(String[] args) {
        HomeworkChecker checker = new HomeworkChecker("target/classes/lesson_7/Task2/HomeworksToCheck");

        checker.startCheck();
    }

    public void startCheck() {

        File fDir = new File(dir);

        works = new ArrayList<>();

        for (String filename : Objects.requireNonNull(fDir.list((file, s) -> s.endsWith(".class")))) {
            works.add(new Work(filename));
        }

        for (Work work : works) {
            check(work);
        }
    }

    private void check(Work work) {
        Class<?> workClass;

        //
        String filePath = dir + "/" + work.filename;
        File file = new File(filePath);
        //
        try {
            workClass = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(work.className);
            //Вылетает ClassNotFoundException. Путь коррекен, файл существует, проверял. Имя классса тоже совпадает
        } catch (ClassNotFoundException | MalformedURLException e) {
            System.err.println(work.filename + " : can't load class");
            e.printStackTrace();
            return;
        }

        Method[] methods = workClass.getMethods();

        for (TaskChecker checker : checkers) {
            checker.check(work, methods);
        }

//        Checkers.checkCalcFloat(work, methods);
//
//        Checkers.checkTwoNumbers(work, methods);
//
//        Checkers.checkIsNegative(work, methods);
//
//        Checkers.checkLeapYear(work, methods);
    }
}
