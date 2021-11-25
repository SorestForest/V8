package ru.REStudios.v8.components.custom;

import org.joml.Vector2f;
import org.joml.Vector4f;
import ru.REStudios.v8.components.Component;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class Transform extends Component {

    public Vector2f position;
    public Vector2f size;
    public float angle;

    public Transform(){
        this(new Vector2f(0, 0));
    }

    public Transform(Vector2f position){
        this(position,new Vector2f(0,0),0);
    }

    public Transform(Vector2f position,Vector2f size){
        this(position,size,0);
    }

    public Transform(Vector2f position, Vector2f size, float angle) {
        this.position = position;
        this.size = size;
        this.angle = angle;
    }

    public Vector4f asVector4f(){
        return new Vector4f(position.x,position.y,size.x,size.y);
    }


    @Override
    public void render() {}

    @Override
    public void update(double dt) {}

    @Override
    public void init() {}
}
