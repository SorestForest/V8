package ru.REStudios.v8.components.custom;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.Window;

import java.io.IOException;

public class CallbackReceiver extends Component {
    @Override
    public void render() {}

    @Override
    public void update(double dt) {}

    @Override
    public void init() throws IOException {
        Window.get().addInputReceiver(this);
    }

    public void key(int key, int scancode, int action, int mods){}
    public void mouse_window_enter(){}
    public void mouse_window_exit(){}
    public void mouse(int key, int action, int mods){}
    public void window_focus(){}
    public void window_unfocus(){}
    public void window_position(Vector2i position){}
    public void window_size(Vector2i size){}
    public void mouse_scroll(Vector2d offset){}
}
