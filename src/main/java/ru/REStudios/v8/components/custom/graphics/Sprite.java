package ru.REStudios.v8.components.custom.graphics;

import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.Renderer;
import ru.REStudios.v8.graphics.Texture;
import ru.REStudios.v8.utils.files.ResourceLoader;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_QUADS;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class Sprite extends Component {

    private Texture texture;
    private final String fileName;

    public Sprite(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void render() {
        Renderer.v8Begin(GL_QUADS);
        Renderer.v8Texture(texture, getTransform().asVector4f());
        Renderer.v8End();
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void init() throws IOException {
        texture = ResourceLoader.getOrLoad(fileName);
    }
}
