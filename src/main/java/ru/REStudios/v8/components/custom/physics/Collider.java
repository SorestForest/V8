package ru.REStudios.v8.components.custom.physics;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import org.joml.Vector2f;
import org.joml.Vector4f;
import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.MatrixPoint;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static ru.REStudios.v8.graphics.Renderer.*;

public class Collider extends Component {
    protected boolean debug;

    @Override
    public void render() {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void init() throws IOException {

    }
    public PolygonShape asPolygon(){
        return new PolygonShape(){{
            setAsBox(1, 1);
        }};
    }
}