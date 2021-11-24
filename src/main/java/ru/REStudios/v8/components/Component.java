package ru.REStudios.v8.components;

import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.physics.Rigidbody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        List<Class<? extends Component>> classes = objectComponents.stream().map(Component::getClass).collect(Collectors.toList());
        if (this instanceof Rigidbody){
            System.out.println(classes);
            System.out.println(dependencies);
        }
        boolean find = containsAll(classes);
        System.out.println(find);
        enabled = find;
        return enabled;

    }

    private boolean containsAll(List<Class<? extends Component>> object){
        for (Class<? extends Component> c : dependencies) {
            if (!contains(c,object)){
                return false;
            }
        }
        return true;
    }

    private boolean contains(Class<? extends Component> o,List<Class<? extends Component>> object){
        Iterator<Class<? extends Component>> it = object.iterator();
        if (o==null) {
            while (it.hasNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hasNext()){
                Class<? extends Component> in = it.next();
                if (in.equals(o) || o.isAssignableFrom(in)){
                    return true;
                }
            }
        }
        return false;
    }

    public Transform getTransform(){
        return parent.getTransform();
    }

}
