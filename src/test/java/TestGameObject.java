import org.joml.Vector2f;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.graphics.Sprite;
import ru.REStudios.v8.components.custom.physics.BoxCollider;
import ru.REStudios.v8.components.custom.physics.Rigidbody;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class TestGameObject extends GameObject {

    public TestGameObject(){
        addComponent(new BoxCollider());
        addComponent(new Transform(new Vector2f(200,200),new Vector2f(1000,1000)));
        addComponent(new Sprite("v8.png"));
        addComponent(new Rigidbody(){{
            setMass(0.001);
        }});
    }


}
