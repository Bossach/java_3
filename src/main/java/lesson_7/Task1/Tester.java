package lesson_7.Task1;

import lesson_7.Task1.annotations.AfterSuite;
import lesson_7.Task1.annotations.BeforeSuite;
import lesson_7.Task1.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        //start(Tests.class);
        start("lesson_7.Task1.Tests");
    }

    public static void start(String className) {
        try {
            start(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> void start(Class<T> testsClass) {

        Method[] methods = testsClass.getMethods();

        List<Method> before = new ArrayList<>();
        List<Method> after = new ArrayList<>();
        List<Method> test = new ArrayList<>();

        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {before.add(method); continue;}
            if (method.getAnnotation(AfterSuite.class) != null) {after.add(method); continue;}
            if (method.getAnnotation(Test.class) != null) test.add(method);
        }

        if (before.size() > 1 || after.size() > 1) throw new RuntimeException("More than 1 methods marked as @AfterSuite or @BeforeSuite");

        if (before.size() == 1) runMethod(before.get(0));

        test.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()));

        for (Method method : test) {
            runMethod(method);
        }

        if (after.size() == 1) runMethod(after.get(0));

    }

    private static void runMethod(Method method) {
        try {
            method.invoke(method.getDeclaringClass().getConstructor().newInstance());
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
