package lesson_7.Task2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface TaskAnnot {
    }

    /*
    Here is to write your tasks:

    //Task template ------------------
        @TaskAnnot
        public Task *anyMethodName*() { ... }
    //--------------------------------
    Your method must return Task object


    Task constructor:

    new Task((1), (2), (3), (4), (5));

    (1) String: *supposed name of method you wish to check*

    (2) Class: *type of return value*

    (3) Class[]: * array of argument types in order *

    (for example: public int myMethod(int a, float b) --> new Task("myMethod", int.class, new Class[]{int.class, float.class},..,..))

    (4) Checker: new Checker() {
                    @Override
                    public boolean check(Object resVal, Object expVal, Object[] paramSet) {
                        return (int) expVal == (int) resVal;
                    }
    check arguments:    resVal - result of checking method
                        expVal - expected value (may no exist if don't written in (5))
                        argSet - array of arguments


    (5) *Object*[][][]: test data
                {{expected Return Value (if needed)},  {parameters...}},
                {{.}, {...}},
                {{.}, {...}} ...

     */

    //EXAMPLE TASK
//    @TaskAnnot
//    public Task myTask() {
//        return new Task("sum", int.class, new Class[]{int.class, int.class},
//                new Checker() {
//                    @Override
//                    public boolean check(Object resVal, Object expVal, Object[] argSet) {
//                        int a = (int) argSet[0];
//                        int b = (int) argSet[1];
//
//                        return a + b == (int) resVal;
//
//                        //or just return (int) resVal == (int) expVal;
//                    }
//                }, new Object[][][]{
//                {{4}, {2, 2}},
//                {{6}, {3, 3}},
//                {{100}, {73, 27}}
//        });
//    }

    @TaskAnnot
    public Task task1() {
        Task currentTask = new Task("calculate", int.class, new Class[]{int.class, int.class, int.class, int.class},
                new Checker() {
                    @Override
                    public boolean check(Object resVal, Object expVal, Object[] argSet) {
                        return (int) expVal == (int) resVal;
                    }
                }, new Integer[][][]{
                {{15}, {5, 1, 4, 2}},
                {{21}, {3, 2, 15, 3}}
        });

        currentTask.setDescription("int");

        return currentTask;
    }

    @TaskAnnot
    public Task task2() {
        Task currentTask = new Task("calculate", float.class, new Class[]{float.class, float.class, float.class, float.class},
                new Checker() {
                    @Override
                    public boolean check(Object resVal, Object expVal, Object[] argSet) {
                        return (float) expVal == (float) resVal;
                    }
                }, new Float[][][]{
                {{13.5f}, {3f, 2f, 5f, 2f}},
                {{17f}, {4f, 0.5f, 15f, 4f}}
        });

        currentTask.setDescription("float");


        return currentTask;
    }

    @TaskAnnot
    public Task task3() {
        return new Task("checkTwoNumbers", boolean.class, new Class[]{int.class, int.class},
                new Checker() {
                    @Override
                    public boolean check(Object resVal, Object expVal, Object[] argSet) {
                        return (boolean) expVal == (boolean) resVal;
                    }
                }, new Object[][][]{
                {{true}, {7, 9}},
                {{false}, {1, 3}},
                {{false}, {15, 30}}
        });
    }

    @TaskAnnot
    public Task task4() {
        return new Task("isNegative", boolean.class, new Class[]{int.class},
                new Checker() {
                    @Override
                    public boolean check(Object resVal, Object expVal, Object[] argSet) {
                        return (boolean) resVal == ((int) argSet[0] < 0);
                    }
                }, new Object[][][]{
                {{}, {7}},
                {{}, {-5}},
                {{}, {0}}
        });
    }

    @TaskAnnot
    public Task task5() {
        return new Task("isLeapYear", boolean.class, new Class[]{int.class},
                new Checker() {
                    @Override
                    public boolean check(Object resVal, Object expVal, Object[] argSet) {
                        return (boolean) expVal == (boolean) resVal;
                    }
                }, new Object[][][]{
                {{true}, {2000}},
                {{false}, {2001}},
                {{false}, {2600}}
        });
    }


    public static List<Task> getTasks() {
        TaskList exemplar = new TaskList();

        List<Task> list = new ArrayList<>();

        Class<TaskList> taskListClass = TaskList.class;

        Method[] tasksMethods = taskListClass.getMethods();
        for (Method m : tasksMethods) {
            if (m.getAnnotation(TaskAnnot.class) != null) {
                try {
                    list.add((Task) m.invoke(exemplar));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println("Error preparing Task " + m.getName() + " :" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

}
