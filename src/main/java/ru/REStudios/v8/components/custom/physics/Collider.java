package ru.REStudios.v8.components.custom.physics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.MatrixPoint;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static ru.REStudios.v8.graphics.Renderer.*;

public class Collider extends Component {
    protected boolean debug;
    @Override
    public void render() {
        /*MatrixPoint points = getPoints();
        v8Begin(GL_LINES);
        v8Color(Color.BLACK);
        ArrayList<Vector2f> v = points.getVector2fs();
        v8End();*/
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void init() throws IOException {

    }
    public MatrixPoint getPoints(){
        return new MatrixPoint(){{
            add(new Vector2f(-0.5f, -0.5f));
            add(new Vector2f(0.5f, -0.5f));
            add(new Vector2f(0.5f, 0.5f));
            add(new Vector2f(-0.5f, 0.5f));
        }};
    }
}
