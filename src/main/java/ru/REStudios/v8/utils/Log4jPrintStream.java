package ru.REStudios.v8.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;

public class Log4jPrintStream extends PrintStream implements Thread.UncaughtExceptionHandler {

    public boolean isErrorStream = false;
    private final PrintStream original;

    public Log4jPrintStream(PrintStream stream) {
        super(stream);
        original = stream;
    }

    int add = 0;

    @Override
    public void print(@Nullable String s) {
        StackTraceElement el = REUtils.getCaller(2+add);
        String clazz = el.getClassName()+":"+el.getLineNumber();
        original.printf("[%s %s %s] %s", Thread.currentThread().getName(), isErrorStream ? "ERROR" : "INFO", clazz, s);
    }

    @Override
    public void print(int i) {
        add = 1;
        this.print(String.valueOf(i));
        add = 0;
    }

    @Override
    public void print(double d) {
        add = 1;
        this.print(String.valueOf(d));
        add = 0;
    }

    @Override
    public void print(char c) {
        add = 1;
        this.print(String.valueOf(c));
        add = 0;
    }

    @Override
    public void print(long l) {
        add = 1;
        this.print(String.valueOf(l));
        add = 0;
    }

    @Override
    public void print(float f) {
        add = 1;
        this.print(String.valueOf(f));
        add = 0;
    }

    @Override
    public void print(boolean b) {
        add = 1;
        this.print(String.valueOf(b));
        add = 0;
    }

    @Override
    public void print(@Nullable Object obj) {
        add = 1;
        this.print(String.valueOf(obj));
        add = 0;
    }

    @Override
    public void print(char @NotNull [] s) {
        add = 1;
        this.print(String.valueOf(s));
        add = 0;
    }

    public static void setupPrints() {
        System.setOut(new Log4jPrintStream(System.out));
        Log4jPrintStream stream = new Log4jPrintStream(System.err);
        stream.isErrorStream = true;
        System.setErr(stream);
        Thread.currentThread().setUncaughtExceptionHandler(stream);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace(original);
    }
}