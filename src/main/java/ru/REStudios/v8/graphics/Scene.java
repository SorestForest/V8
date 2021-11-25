package ru.REStudios.v8.graphics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.io.IOException;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public abstract class Scene  {

    public World world;

    public Scene(){
        world = new World(new Vector2(0f, 98f), true);
    }

    public void update(double dt){
        world.step((float) dt, 6, 2);
    }

    public abstract void render();

    public void init() throws IOException {}
}
