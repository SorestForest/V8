package ru.REStudios.v8.components.custom.input;

import org.joml.Vector2d;
import org.joml.Vector2i;

public interface IReceiver {
    default void key(int key, int scancode, int action, int mods){}
    default void mouse_window_enter(){}
    default void mouse_window_exit(){}
    default void mouse(int key, int action, int mods){}
    default void window_focus(){}
    default void window_unfocus(){}
    default void window_position(Vector2i position){}
    default void window_size(Vector2i size){}
    default void mouse_scroll(Vector2d offset){}
}
