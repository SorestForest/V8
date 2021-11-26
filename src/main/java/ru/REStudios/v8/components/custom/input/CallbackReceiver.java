package ru.REStudios.v8.components.custom.input;

import ru.REStudios.v8.components.Component;
import ru.REStudios.v8.graphics.Window;

import java.io.IOException;

public class CallbackReceiver extends Component implements IReceiver {
    @Override
    public void update(double dt) {}

    @Override
    public void init() throws IOException {
        Window.get().addInputReceiver(this);
    }

}
