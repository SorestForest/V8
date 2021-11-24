package ru.REStudios.v8.graphics;

import org.jetbrains.annotations.Nullable;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.graphics.CameraComponent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public abstract class Scene {

    protected ArrayList<GameObject> sceneObjects = new ArrayList<>();

    public abstract void update(double dt);

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
