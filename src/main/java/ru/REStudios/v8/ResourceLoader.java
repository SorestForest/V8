package ru.REStudios.v8;

import ru.REStudios.v8.graphics.Texture;
import ru.REStudios.v8.utils.files.FileHandle;

import java.io.IOException;
import java.util.HashMap;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class ResourceLoader {

    private static final HashMap<String, Texture> loaded = new HashMap<>();

    public static Texture getOrLoad(String path) throws IOException {
        String full = FileHandle.asFull(path);
        if (loaded.containsKey(full)){
            return loaded.get(full);
        }
        Texture t = new Texture(full);
        loaded.put(full,t);
        return t;
    }

}
