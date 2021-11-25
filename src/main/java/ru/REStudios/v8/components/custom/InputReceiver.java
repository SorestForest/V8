package ru.REStudios.v8.components.custom;

import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.Window;

import java.io.IOException;

public class InputReceiver extends Component {
    @Override
    public void render() {}

    @Override
    public void update(double dt) {}

    @Override
    public void init() throws IOException {
        Window.get().addInputReceiver(this);
    }

    public void receive(int key, int scancode, int action, int mods){

    }
}
