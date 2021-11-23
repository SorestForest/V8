package ru.REStudios.v8.utils;

/**
 * (C) Copyright REStudios 2021
 */
public class ThreadBuilder {

    private Runnable task;
    private Thread thread;

    public ThreadBuilder(Runnable task){
        this.task = task;
        thread = new Thread(task);

    }


    public ThreadBuilder setPriority(int priority){
        this.thread.setPriority(priority);
        return this;
    }

    public ThreadBuilder setName(String name){
        this.thread.setName(name);
        return this;
    }

    public Thread build(){
        return thread;
    }

    public ThreadBuilder setHandler(Thread.UncaughtExceptionHandler handler){
        this.thread.setUncaughtExceptionHandler(handler);
        return this;
    }

}