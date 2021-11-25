package ru.REStudios.v8.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.physics.Collider;
import ru.REStudios.v8.graphics.Scene;
import ru.REStudios.v8.graphics.Window;

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
    public Body body;

    public GameObject(){
        addComponent(new Transform());

    }

    public void addComponent(Component component){
        if (component == null){ throw new NullPointerException("Component == null"); }
        component.parent = this;
        components.add(component);
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<? extends T> clazz){
        for (Component component : components) {
            if (component.getClass().isAssignableFrom(clazz)){
                return Objects.requireNonNull((T) component);
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
        Scene scene = Window.getCurrentScene();
        World world = scene.world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(getComponent(Transform.class).position.x, getComponent(Transform.class).position.y));
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = getComponent(Collider.class).asPolygon();

        fixtureDef.density = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
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
