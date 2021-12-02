import org.joml.Vector2f;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.input.CallbackReceiver;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.graphics.Sprite;
import ru.REStudios.v8.components.custom.physics.Collider;
import ru.REStudios.v8.components.custom.physics.Rigidbody;
import ru.REStudios.v8.graphics.Window;
import ru.REStudios.v8.input.Input;

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
        getComponent(Transform.class).size = new Vector2f(100, 100);
        addComponent(new Sprite("v8.png"));
        addComponent(new Rigidbody());

        addComponent(new CallbackReceiver(){
            @Override
            public void key(int key, int scancode, int action, int mods) {
                if(key == Input.KEY_W){
                    getComponent(Rigidbody.class).impulse(new Vector2f(0, -100));
                }
                if (key == Input.KEY_B){
                    Window.get().cameraPosition.add(0,100);
                    System.out.println("ad");
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
