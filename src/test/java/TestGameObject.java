import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.InputReceiver;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.graphics.Sprite;
import ru.REStudios.v8.components.custom.physics.Collider;
import ru.REStudios.v8.components.custom.physics.Rigidbody;

import java.io.IOException;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class TestGameObject extends GameObject {

    public TestGameObject(){
        super();
        addComponent(new Collider());
        getComponent(Transform.class).position = new Vector2f(200, 200);
        getComponent(Transform.class).size = new Vector2f(1000, 1000);
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
