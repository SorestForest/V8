package ru.REStudios.v8.utils;

import org.jetbrains.annotations.NotNull;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public interface NonNullSupplier<T> {

    /**
     * Simple supplier method
     * @return value
     */
    T a();

    @NotNull
    default T get(){
        if (a() == null){
            throw new NullPointerException("NonNullSupplier can't return null");
        }
        return a();
    }

}
