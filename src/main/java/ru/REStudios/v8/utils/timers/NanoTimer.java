package ru.REStudios.v8.utils.timers;

import ru.REStudios.v8.utils.Time;
import ru.REStudios.v8.utils.oop.RENumbers;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class NanoTimer extends Timer {

    private float timeStarted;
    private float timeElapsed;

    @Override
    public void end() {
        timeElapsed =  (Time.getTimeNano() - timeStarted);
    }

    @Override
    public void start() {
        timeStarted = Time.getTimeNano();
    }

    @Override
    public float timeStarted() {
        return timeStarted;
    }

    @Override
    public float elapsedTime() {
        return RENumbers.roundTo(timeElapsed,5);
    }
}
