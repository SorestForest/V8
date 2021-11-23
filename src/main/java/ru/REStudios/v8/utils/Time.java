package ru.REStudios.v8.utils;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class Time {
    public static float timeStarted = System.nanoTime();

    public static float getTime() { return (float)((System.nanoTime() - timeStarted) * 1E-9); }
}
