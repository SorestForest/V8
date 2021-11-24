package ru.REStudios.v8.components;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.REStudios.v8.components.custom.Transform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public abstract class GameObject {

    private final ArrayList<Component> components = new ArrayList<>();
    ArrayList<Class<? extends Component>> dependencies = new ArrayList<>();


    public void addComponent(Component component){
        if (component == null){ throw new NullPointerException("Component == null"); }
        component.parent = this;
        components.add(component);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T getComponent(Class<? extends Component> clazz){
        for (Component component : components) {
            if (component.getClass().isAssignableFrom(clazz)){
                return (T) component;
            }
        }
        return null;
    }

    void addDependency(Class<? extends Component> clazz){
        if (!dependencies.contains(clazz)){
            dependencies.add(clazz);
        }
    }

    public void init() throws IOException {
        validateComponents();
        for (Component component : components) {
            component.init();
        }

    }

    public void update(double dt){
        for (Component component : components) {
            if (component.enabled){
                component.update(dt);
            }
        }
    }

    public void render(){
        for (Component component : components) {
            if (component.enabled){
                component.render();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void validateComponents(){
        for (Component component : components) {
            component.validateDependencies((ArrayList<Component>) components.clone());
        }
    }

    @NotNull
    public Transform getTransform(){
        return Objects.requireNonNull(getComponent(Transform.class));
    }


}
