package ru.REStudios.v8.components.custom.input;

import org.joml.Vector2d;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.Window;

import java.io.IOException;
import java.nio.DoubleBuffer;

public class Button extends Component {

    private IReceiver receiver;

    @Override
    public void update(double dt) {

    }

    @Override
    public void init() throws IOException {
        receiver = new IReceiver() {
            @Override
            public void mouse(int key, int action, int mods) {
                DoubleBuffer coords = BufferUtils.createDoubleBuffer(2);
                Window.get().getCursorPosition();
                double x = coords.get(0);
                double y = coords.get(1);
            }
        };
    }
}
