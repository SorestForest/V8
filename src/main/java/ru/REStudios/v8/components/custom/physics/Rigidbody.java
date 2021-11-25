package ru.REStudios.v8.components.custom.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import org.joml.Vector2f;
import ru.REStudios.v8.components.Component;

import java.io.IOException;

public class Rigidbody extends Component {
    // PHYSICS

    public Rigidbody() {
        super();
        requiredComponent(Collider.class);
    }

    public float getMass() {
        return parent.body.getMass();
    }

    public void setMass(float m) {
        parent.body.getMassData().mass = m;
    }

    // setGravity(1) is default
    public void setGravity(float g){
        parent.body.setGravityScale(g);

    }
    public float getInertia(){return parent.body.getInertia();}
    public World getWorld(){return parent.body.getWorld();}

    public float getGravity(){
        return parent.body.getGravityScale();
    }



    @Override
    public void update(double dt) {
        Vector2 vec = parent.body.getPosition();
        parent.getTransform().position = new Vector2f(vec.x, vec.y);

    }

    @Override
    public void init() throws IOException {

    }
    public void impulse(Vector2f impulse){
        Body body = parent.body;
        body.applyLinearImpulse(toVector2(impulse), body.getPosition().cpy().add(body.getLocalCenter()), true);
    }
    private Vector2 toVector2(Vector2f vec){
        return new Vector2(vec.x, vec.y);
    }
}
