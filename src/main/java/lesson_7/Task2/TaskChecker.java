package lesson_7.Task2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskChecker {

    private TaskDescription task;

    private Work currentWork;

    public TaskChecker(TaskDescription task) {
        this.task = task;
    }

    public void check(Work work, Method[] methods) {

        currentWork = work;

        List<Method> checkCandidates = filter(methods);

        for (Method method : checkCandidates) {

            System.out.println(method.getName());
            //TODO тут логика проверки метода ДЗ

        }

        //If find no one try to find by return type and arguments


        currentWork = null;
    }

    private List<Method> filter(Method[] methods) {
        List<Method> list = new ArrayList<>();

        //Find methods matching by arguments and return type
        for (Method method : methods) {
            if (method.getReturnType().equals(task.returnType) && Arrays.equals(method.getParameterTypes(), task.parameterTypes))
                list.add(method);
        }

        //Try find by name
        for (Method method : list) {
            if (method.getName().equalsIgnoreCase(task.supposedName)) {

                //debug!!!
                System.out.println("FoundByName! " + task.supposedName);

                ArrayList<Method> nameMatchList = new ArrayList<>();
                nameMatchList.add(method);
                return nameMatchList;
            }
        }

        return list;
    }

}
