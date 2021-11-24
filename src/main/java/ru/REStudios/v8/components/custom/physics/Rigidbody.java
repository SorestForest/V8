package ru.REStudios.v8.components.custom.physics;

import org.joml.Vector2f;
import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.components.custom.Transform;
import ru.REStudios.v8.math.Physic;

import java.io.IOException;
import java.util.Objects;

public class Rigidbody extends Component {
    // PHYSICS

    double m = 0.00005;

    public Rigidbody() {
        super();
        requiredComponent(Collider.class);
    }

    public double getMass() {
        return m;
    }

    public void setMass(double m) {
        this.m = m;
    }

    @Override
    public void render() {

    }

    @Override
    public void update(double dt) {
        Physic.F f = new Physic.F(m);
        System.out.println(f);
        Vector2f position = new Vector2f(parent.getTransform().position);
        // ATTRACTION
        position.add(f.getDirection().mul(new Vector2f((float)f.getImpulse(), (float)f.getImpulse())));


        ((Transform) Objects.requireNonNull(parent.getComponent(Transform.class))).position = position;

    }

    @Override
    public void init() throws IOException {

    }
    protected void impulse(){

    }
}
