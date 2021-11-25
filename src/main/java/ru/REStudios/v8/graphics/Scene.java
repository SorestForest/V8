package ru.REStudios.v8.graphics;

import org.jetbrains.annotations.Nullable;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.graphics.CameraComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.io.IOException;
import java.util.ArrayList;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public abstract class Scene  {

    public World world;
    protected ArrayList<GameObject> sceneObjects = new ArrayList<>();

    public Scene(){
        world = new World(new Vector2(0f, 98f), true);
    }

    public void update(double dt){
        world.step((float) dt, 6, 2);
    }

    public abstract void render();

    public void init() throws IOException {}

    public ArrayList<GameObject> getSceneObjects() {
        return sceneObjects;
    }

    public <T extends GameObject> T addGameObject(T object) throws IOException {
        getSceneObjects().add(object);
        object.init();
        return object;
    }

    @Nullable
    public CameraComponent findCamera(){
        for (GameObject sceneObject : getSceneObjects()) {
            if (sceneObject.getComponent(CameraComponent.class) != null){
                return sceneObject.getComponent(CameraComponent.class);
            }
        }
        return null;
    }
}
