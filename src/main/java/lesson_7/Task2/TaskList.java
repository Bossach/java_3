package lesson_7.Task2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class TaskList {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface TaskAnnot {}

    /*
    Here is to write your tasks:

    @TaskAnnot
    public Task *anyMethodName*() { ... }

    Method must return Task object


    Task constructor:

    new Task((1), (2), (3), (4), (5));

    (1) String: *supposed name of method you wish to check*

    (2) Class: *type of return value*

    (3) Class[]: * array of argument types in order *

    (for example public int myMethod(int a, float b) --> new Task("myMethod", int.class, new Class[]{int.class, float.class},..,..))

    (4) Predicate: (o) -> { check logics returns boolean }
    o - Object[] where is o[0] is one element array wis supposed result from data
                        o[1] is result of checking method
                        o[2] is array pf arguments

    this example is works well:
    o -> {          Object[] arr = (Object[]) o;
                    *returnType* expectedValue = (*returnType*) ((Object[]) arr[0])[0];
                    *returnType* resultValue = (*returnType*) arr[1];
                    return resultValue.equals(expectedValue);
                }
    or any your logic returns boolean that means is method works right


    (5) *AnyObjectType*[][][]: test data
                {{expected Return Value (if needed)},  {parameters...}},{...},{...}

     */




    @TaskAnnot
    public Task task1() {
        Task currentTask = new Task("calculate", int.class, new Class[]{int.class, int.class, int.class, int.class},
                o -> {
                    //prepare data
                    Object[] arr = (Object[]) o;

                    int expectedValue = (int) ((Object[]) arr[0])[0];

                    int resultValue = (int) arr[1];
                    //
                    //there is checks
                    return resultValue == expectedValue;
                }, new Integer[][][]{
                //test data
                //{{expected Return Value},  {parameters...}},{...},{...}
                {{15}, {5, 1, 4, 2}},
                {{21}, {3, 2, 15, 3}}
        });

        currentTask.setDescription("int");

        return currentTask;
    }

    @TaskAnnot
    public Task task2() {
        Task currentTask = new Task("calculate", float.class, new Class[]{float.class, float.class, float.class, float.class},
                o -> {
                    //prepare data
                    Object[] arr = (Object[]) o;

                    float expectedValue = (float) ((Object[]) arr[0])[0];

                    float resultValue = (float) arr[1];
                    //
                    //there is checks
                    return resultValue == expectedValue;
                }, new Float[][][]{
                //test data
                //{{expected Return Value},  {parameters...}},{...},{...}
                {{13.5f}, {3f, 2f, 5f, 2f}},
                {{17f}, {4f, 0.5f, 15f, 4f}}
        });

        currentTask.setDescription("float");


        return currentTask;
    }

    @TaskAnnot
    public Task task3() {
        return new Task("checkTwoNumbers", boolean.class, new Class[]{int.class, int.class},
                o -> {
                    //prepare data
                    Object[] arr = (Object[]) o;

                    boolean expectedValue = (boolean) ((Object[]) arr[0])[0];

                    boolean resultValue = (boolean) arr[1];
                    //
                    //there is checks
                    return resultValue == expectedValue;
                }, new Object[][][]{
                //test data
                //{{expected Return Value},  {parameters...}},{...},{...}
                {{true}, {7, 9}},
                {{false}, {1, 3}},
                {{false}, {15, 30}}
        });
    }

    @TaskAnnot
    public Task task4() {
        return new Task("isNegative", boolean.class, new Class[]{int.class},
                o -> {
                    //prepare data
                    Object[] arr = (Object[]) o;

                    boolean expectedValue = (boolean) ((Object[]) arr[0])[0];

                    boolean resultValue = (boolean) arr[1];
                    //
                    //there is checks
                    return resultValue == expectedValue;
                }, new Object[][][]{
                //test data
                //{{expected Return Value},  {parameters...}},{...},{...}
                {{false}, {7}},
                {{true}, {-5}},
                {{false}, {0}}
        });
    }

    @TaskAnnot
    public Task task5() {
        return new Task("isLeapYear", boolean.class, new Class[]{int.class},
                o -> {
                    //prepare data
                    Object[] arr = (Object[]) o;

                    boolean expectedValue = (boolean) ((Object[]) arr[0])[0];

                    boolean resultValue = (boolean) arr[1];
                    //
                    //there is checks
                    return resultValue == expectedValue;
                }, new Object[][][]{
                //test data
                //{{expected Return Value},  {parameters...}},{...},{...}
                {{true}, {2000}},
                {{false}, {2001}},
                {{false}, {2600}}
        });
    }

}
