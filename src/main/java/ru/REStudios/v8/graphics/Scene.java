package ru.REStudios.v8.graphics;

import java.io.IOException;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public abstract class Scene {


    public abstract void update(double dt);

    public abstract void render();

    public void init() throws IOException {}
}
