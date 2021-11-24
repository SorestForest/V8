import org.joml.Vector2f;
import ru.REStudios.v8.components.GameObject;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.components.custom.graphics.CameraComponent;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class CameraObject extends GameObject {

    public CameraObject(){
        addComponent(new Transform(new Vector2f(0,0)));
        addComponent(new CameraComponent(new Vector2f(0,0)));
    }

    public Vector2f position(){
        CameraComponent c = getComponent(CameraComponent.class);
        assert c != null;
        return c.position;
    }

}
