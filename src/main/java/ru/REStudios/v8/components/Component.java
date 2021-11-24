package ru.REStudios.v8.components;

import ru.REStudios.v8.components.custom.Transform;

import java.io.IOException;
import java.util.ArrayList;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public abstract class Component {

    public GameObject parent;

    public Component(){
        requiredComponent(Transform.class);
    }

    private final ArrayList<Class<? extends Component>> dependencies = new ArrayList<>();

    protected boolean enabled = true;

    public abstract void render();

    public abstract void update(double dt);

    public abstract void init() throws IOException;

    public Component disable(){ enabled = false; return this; }

    public Component enable(){ enabled = true; return this;}

    public final void requiredComponent(Class<? extends Component> component){
        dependencies.add(component);
    }


    final boolean validateDependencies(ArrayList<Component> objectComponents) {
        boolean find = false;
        for (Class<? extends Component> dependency : dependencies) {
            for (Component objectComponent : objectComponents) {
                if (dependency.equals(objectComponent.getClass())){
                    find = true;
                    break;
                }
            }
            if (!find){
                disable();
                break;
            }
        }
        if (dependencies.size() == 0){
            return true;
        }
        return find;
    }

    public Transform getTransform(){
        return parent.getTransform();
    }
}
