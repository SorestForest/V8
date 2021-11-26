package ru.REStudios.v8.utils;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * (C) Copyright REStudios 2021
 */
public class REUtils {

    @Nullable
    public static <T> T castOrNull(Class<T> clazz,Object object){
        if (clazz.isAssignableFrom(object.getClass())){
            return clazz.cast(object);
        }
        return null;
    }

    public static int keepInRange(int value,int minimal,int maximal){
        if (value >= maximal){ return maximal; }
        return Math.max(value, minimal);
    }



    @SafeVarargs
    public static <T> Collection<T> removeAll(Collection<T> collection, T... toRemove){
        ArrayList<T> out = new ArrayList<>();
        ArrayList<T> remove = new ArrayList<>(Arrays.asList(toRemove));
        for (T string : collection) {
            if(!remove.contains(string)){
                out.add(string);
            }
        }
        return out;
    }

    public static int[] getFrom(int from,int to){
        if (from > to) { throw new IllegalArgumentException("From is bigger than to. From: "+from+", To: "+to); }
        int[] array = new int[to-from];
        for (int i = 1; i < array.length+1; i++) {
            array[i-1] = to-i;
        }

        return reverse_array(array);
    }

    public static int[] reverse_array(int[] a)
    {
        int n = a.length;
        int[] dest_array = new int[n];
        int j = n;
        for (int value : a) {
            dest_array[j - 1] = value;
            j = j - 1;
        }


        return dest_array;
    }


    public static <T> T[] collapse(T[] a, T[] b){
        ArrayList<T> output = new ArrayList<>();
        output.addAll(Lists.asList(null,a));
        output.addAll(Lists.asList(null,b));
        output.remove(0);
        output.remove(a.length);
        return output.toArray(a);
    }

    public static int parseInteger(String s){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            e.printStackTrace();
            System.err.println(s);
            return 0;
        }
    }

    public static double parseDouble(String s){
        try{
            return Double.parseDouble(s);
        } catch (NumberFormatException ignored){
            return 0d;
        }
    }

    public static StackTraceElement getCaller(int depth){
        Throwable throwable = new Throwable();
        try {
            throw throwable;
        }catch (Throwable t){
            int actualDepth = Math.min(depth+1,t.getStackTrace().length);
            return t.getStackTrace()[actualDepth];
        }

    }

    public static boolean safeEquals(Object a,Object b){
        if (a == null && b == null){ return true;}
        if (a == null){
            return false;
        }
        else{
            return a.equals(b);
        }
    }
}
