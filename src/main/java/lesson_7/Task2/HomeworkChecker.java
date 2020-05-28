package lesson_7.Task2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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

        List<Task> taskList = TaskList.getTasks();

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
            //noinspection deprecation
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
