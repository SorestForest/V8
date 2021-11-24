package ru.REStudios.v8.components.custom.physics;

import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.components.GameObject;

import java.io.IOException;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class BoxCollider extends Collider {

    public BoxCollider(){
        this(false);
    }

    public final boolean trigger;

    public BoxCollider(boolean trigger){
        this.trigger = trigger;
    }



    @Override
    public void init() throws IOException {

    }

    public interface CollisionDetection {

        default void onCollisionEnter(GameObject with) {

        }

        default void onTriggerEnter(GameObject with){

        }

    }

}
