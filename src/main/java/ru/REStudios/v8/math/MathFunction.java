package ru.REStudios.v8.math;

public interface MathFunction<V extends Number> {

    @SuppressWarnings("unchecked")
    V eval(V... args);

}
