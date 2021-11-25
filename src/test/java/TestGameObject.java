import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.InputReceiver;
import ru.REStudios.v8.components.custom.Sprite;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.physics.BoxCollider;
import ru.REStudios.v8.components.custom.physics.Collider;
import ru.REStudios.v8.components.custom.physics.Rigidbody;

import java.io.IOException;
import java.util.Objects;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class TestGameObject extends GameObject {

    public TestGameObject(){
        super();
        addComponent(new Collider());
        getComponent(Transform.class).position = new Vector2f(0, 0);
        getComponent(Transform.class).size = new Vector2f(100, 100);
        addComponent(new Sprite("v8.png"));
        addComponent(new Rigidbody());
        addComponent(new InputReceiver(){
            @Override
            public void receive(int key, int scancode, int action, int mods) {
                if(key == GLFW.GLFW_KEY_W){
                    getComponent(Rigidbody.class).impulse(new Vector2f(0, -100));
                }
            }
        });
    }

    @Override
    public void init() throws IOException {
        super.init();

        getComponent(Rigidbody.class).setMass(500);
    }
}
