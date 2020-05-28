package lesson_7.Task2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public /*abstract*/ class Task {

    public final String supposedName;
    public final Class<?> returnType;
    public final Class<?>[] parameterTypes;
    private final Checker checker;
    private final List<Object[][]> dataSet;
    private String description = "";

    public Task(String supposedName, Class<?> returnType, Class<?>[] parameterTypes, Checker checker, Object[][][] data) {
        this.supposedName = supposedName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.checker = checker;
        dataSet = Arrays.asList(data);
    }

    /*
   //TODO
    public abstract boolean check(Object resVal, Object expVal, Object[] argSet);
     */

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
        Object[] argSet =(Object[]) data[1];
        Object[] exVlArr = (Object[]) data[0];
        Object expVal = exVlArr.length != 0? exVlArr[0] : null; //may no exist
        Object resVal;

        try {
            resVal = method.invoke(runObject, argSet);
        } catch (Exception e) {
            System.out.println("Method threw exception: " + e.getMessage());
            return false;
        }
        return checker.check(resVal, expVal, argSet);
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
