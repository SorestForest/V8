import org.joml.Vector2f;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.Sprite;
import ru.REStudios.v8.components.custom.Transform;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class TestGameObject extends GameObject {

    public TestGameObject(){
        addComponent(new Transform(new Vector2f(-0.8f,-0.8f),new Vector2f(1,1)));
        addComponent(new Sprite("v8.png"));
    }


}
