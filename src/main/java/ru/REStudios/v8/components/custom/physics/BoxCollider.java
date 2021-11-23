package ru.REStudios.v8.components.custom.physics;

import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.components.GameObject;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class BoxCollider extends Component {

    public BoxCollider(){
        this(false);
    }

    public final boolean trigger;

    public BoxCollider(boolean trigger){
        this.trigger = trigger;
    }


    private Rectangle2D.Float rectangle2D;
    private CollisionDetection parentInstance;

    @Override
    public void render() {}

    @Override
    public void update(double dt) {

    }

    @Override
    public void init() throws IOException {
        if (parent instanceof CollisionDetection){
            parentInstance = (CollisionDetection) parent;

        } else { disable(); }
        if (parentInstance != null){
            rectangle2D = new Rectangle2D.Float(getTransform().position.x,getTransform().position.y,getTransform().size.x,getTransform().size.y);
        }
    }

    public interface CollisionDetection {

        default void onCollisionEnter(GameObject with) {

        }

        default void onTriggerEnter(GameObject with){

        }

    }

}
