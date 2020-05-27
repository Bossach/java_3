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
    private final List<Object[][]> dataSet;
    private String description = "";

    public Task(String supposedName, Class<?> returnType, Class<?>[] parameterTypes, Predicate<Object> predicate, Object[][][] data) {
        this.supposedName = supposedName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.predicate = predicate;
        dataSet = Arrays.asList(data);
    }

    public void checkTask(Class<?> workClass) {

        List<Method> checkCandidates = filterMatchingMethods(workClass.getDeclaredMethods());

        for (Method checkCandidate : checkCandidates) {
            checkCandidate.setAccessible(true);
        }

        System.out.print("\t" + supposedName + " " + description + " : ");

        String msg = "Failed or can't check!";

        //If any of candidates passed the task is passed
        for (Method method : checkCandidates) {
            try {
                if (checkMethod(workClass, method)) {
                    msg = "Passed!";
                    break;
                }
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
    }

    public boolean checkMethod(Class<?> aClass, Method method) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Object[] data : dataSet) {
            if (!singleTest(aClass, method, data)) {
                return false;
            }
        }
        return true;
    }

    private boolean singleTest(Class<?> aClass, Method method, Object[] data) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object runObject = aClass.getConstructor().newInstance();
        //Arr with 3 elems goin to test |
        //-----------(1)expected return |value------(2)returns fact value----------(--this| is arguments array to invoke with them--)
        //----------------------------- V----V(1)-----V(2)--------------------------------V--------V(3)Arguments array
        Object testArgument = new Object[]{data[0], method.invoke(runObject,(Object[]) data[1]), data[1]};
        return predicate.test(testArgument);
    }

    private List<Method> filterMatchingMethods(Method[] methods) {
        List<Method> list = new ArrayList<>();

        //Find methods matching by arguments and return type
        for (Method method : methods) {
            if (method.getReturnType().equals(returnType) && Arrays.equals(method.getParameterTypes(), parameterTypes))
                list.add(method);
        }

        //Try find by name
        for (Method method : list) {
            if (method.getName().equalsIgnoreCase(supposedName)) {

                ArrayList<Method> nameMatchList = new ArrayList<>();
                nameMatchList.add(method);
                return nameMatchList;
            }
        }

        //if name didn't found returns all matching by return type and parameters types
        return list;
    }

    public void setDescription(String description) {
        this.description = "(description: " + description + ")";
    }
}
