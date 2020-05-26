package lesson_7.Task2;

import java.util.function.BiPredicate;

class TaskDescription {

    public final String supposedName;
    public final Class<?> returnType;
    public final Class<?>[] parameterTypes;
    private FunctionalInterface[] checks;

    public TaskDescription(String supposedName, Class<?> returnType, Class<?>[] parameterTypes) {
        this.supposedName = supposedName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }
}
