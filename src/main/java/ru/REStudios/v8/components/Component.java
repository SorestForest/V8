package ru.REStudios.v8.components;

import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.physics.Rigidbody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

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

        int length = (int) objectComponents.stream().map(Component::getClass).takeWhile(dependencies::contains).count();
        if (this instanceof Rigidbody){

            System.out.println(objectComponents.stream().map(Component::getClass).takeWhile(dependencies::contains).collect(Collectors.toUnmodifiableList()));
            System.out.println("find length: "+length);
            System.out.println("deps length: "+dependencies.size());
        }
        enabled = length == dependencies.size();
        return enabled;

    }

    public Transform getTransform(){
        return parent.getTransform();
    }

}
