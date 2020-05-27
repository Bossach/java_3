package lesson_7.Task2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeworkChecker {


    private List<Task> tasks;
    private String dir;

    public HomeworkChecker(String workDirectory, List<Task> tasks) {
        this.dir = workDirectory;
        this.tasks = tasks;
    }

    public static void main(String[] args) {
        final String PATH = "files/lesson_7/HomeworksToCheck";
        //final boolean verboseOutput = false;


        List<Task> taskList = new ArrayList<>();

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

        taskList.add(currentTask);

        currentTask = new Task("calculate", float.class, new Class[]{float.class, float.class, float.class, float.class},
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

        taskList.add(currentTask);

        currentTask = new Task("checkTwoNumbers", boolean.class, new Class[]{int.class, int.class},
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
        taskList.add(currentTask);

        currentTask = new Task("isNegative", boolean.class, new Class[]{int.class},
                o -> {
                    //prepare data
                    Object[] arr = (Object[]) o;

                    boolean resultValue = (boolean) arr[1];

                    int argument = (int) ((Object[]) arr[2])[0];
                    //
                    //there is checks
                    boolean expectedResult = argument < 0;

                    return expectedResult == resultValue;
                }, new Object[][][]{
                //test data
                //{{expected Return Value (if need)},  {parameters...}},{...},{...}
                {{}, {7}},
                {{}, {-5}},
                {{}, {0}}
        });

        taskList.add(currentTask);

        currentTask = new Task("isLeapYear", boolean.class, new Class[]{int.class},
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

        taskList.add(currentTask);


        HomeworkChecker checker = new HomeworkChecker(PATH, taskList);

        checker.checkHomeworks();
    }

    public void checkHomeworks() {
        File fDir = new File(dir);

        for (String filename : Objects.requireNonNull(fDir.list((file, s) -> s.endsWith(".class")))) {
            checkHomework(filename);
        }
    }

    private void checkHomework(String filename) {

        //
        String className = filename.substring(0, filename.length() - 6);
//        String filePath = dir + "/" + filename;
        String filePath = dir;
        File file = new File(filePath);
        //

        Class<?> workClass;
        try {
            ClassLoader loader = new URLClassLoader(new URL[]{file.toURL()}, getClass().getClassLoader());
            workClass = loader.loadClass(className);
        } catch (ClassNotFoundException | MalformedURLException e) {
            System.err.println(filename + " : can't load class");
            e.printStackTrace();
            return;
        }

        System.out.println(workClass.getName() + " :");

        for (Task task : tasks) {
            task.checkTask(workClass);
        }

    }
}
