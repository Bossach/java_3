package lesson_7.Task2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Task {

    public final String supposedName;
    public final Class<?> returnType;
    public final Class<?>[] parameterTypes;
    private final Predicate<Object> predicate;
    private final List<Object[]> parametersSet;

    public Task(String supposedName, Class<?> returnType, Class<?>[] parameterTypes, Predicate<Object> predicate, Object[][] parameters) {
        this.supposedName = supposedName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.predicate = predicate;
        parametersSet = Arrays.asList(parameters);
    }

    public void check(Class<?> workClass) {

        String name = workClass.getName().substring(0, workClass.getName().length() - 6);

        System.out.println(name + " :");

        List<Method> checkCandidates = filter(workClass.getMethods());

        System.out.println("\t" + supposedName + " : ");

        String msg = "Failed or can't check!";

        //If any of candidates passed the task is passed
        for (Method method : checkCandidates) {
            try {
                if (test(workClass, method)) {
                    msg = "Passed!";
                    break;
                }
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
    }

    public boolean test(Class<?> aClass, Method method) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Object[] parameters : parametersSet) {
            if (!singleTest(aClass, method, parameters))
                return false;
        }
        return true;
    }

    private boolean singleTest(Class<?> aClass, Method method, Object[] parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return predicate.test(method.invoke(aClass.getConstructor().newInstance(), parameters));
    }

    private List<Method> filter(Method[] methods) {
        List<Method> list = new ArrayList<>();

        //Find methods matching by arguments and return type
        for (Method method : methods) {
            if (method.getReturnType().equals(returnType) && Arrays.equals(method.getParameterTypes(), parameterTypes))
                list.add(method);
        }

        //Try find by name
        for (Method method : list) {
            if (method.getName().equalsIgnoreCase(supposedName)) {

                //TODO delete debug!!!
                System.out.println("FoundByName! " + supposedName);

                ArrayList<Method> nameMatchList = new ArrayList<>();
                nameMatchList.add(method);
                return nameMatchList;
            }
        }

        //if name didnt found returns all matching by return type and parameters types
        return list;
    }
}
