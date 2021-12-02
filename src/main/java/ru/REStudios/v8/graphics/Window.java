package ru.REStudios.v8.graphics;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import ru.REStudios.v8.components.custom.input.IReceiver;
import ru.REStudios.utils.Time;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class Window {
    private static Window window = null;

    private static Scene currentScene;
    private final String title;

    private Window(int width, int height, boolean vsync,String title) {
        this.width = width;
        this.height = height;
        this.vsync = vsync;
        this.title = title;
    }
    public static Scene getCurrentScene(){
        return currentScene;
    }
    public static Window get(){
        if (window == null) {
            throw new IllegalStateException("Initialization first!");
        }
        return window;
    }

    public static Window setup(int width,int height,boolean vsync,String title){
        if (window != null) {
            throw new IllegalStateException("One application per engine!");
        }
        window = new Window(width,height,vsync,title);
        return window;
    }

    private static boolean ready = false;

    public static void setScene(Scene toSet){
        currentScene = toSet;
        if (ready){
            try {
                currentScene.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private final int width;
    private final int height;
    private final boolean vsync;
    private long windowHandle;


    public boolean shouldClose(){
        return glfwWindowShouldClose(windowHandle);
    }

    public void waitFor(){
        //noinspection StatementWithEmptyBody
        while (!shouldClose()){
        }
    }

    public void run(){
        init();
        loop();
    }

    public void destroy(){
        // Free the memory
        glfwFreeCallbacks(windowHandle);
        glfwDestroyWindow(windowHandle);

        // Terminate GLFW and the free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void init(){
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()){
            throw new IllegalStateException("Can't init GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        windowHandle = glfwCreateWindow(this.width, this.height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }
        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(vsync ? 1 : 0);
        glfwShowWindow(windowHandle);
        events();
        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        Vector2f position = new Vector2f(10, 10);
        glOrtho(0+position.x, width+position.x, height+position.y, 0+position.y, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        ready = true;
        try {
            currentScene.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector2f cameraPosition = new Vector2f(0, 0);


    public void setCursorPosition(Vector2f position){glfwSetCursorPos(windowHandle, position.x, position.y);}
    public Vector2d getCursorPosition(){DoubleBuffer coords = BufferUtils.createDoubleBuffer(2);glfwGetCursorPos(windowHandle, coords, coords);return new Vector2d(coords.get(0), coords.get(1));}
    public void hideCursor(){glfwSetInputMode(windowHandle, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);}
    public void showCursor(){glfwSetInputMode(windowHandle, GLFW_CURSOR, GLFW_CURSOR_NORMAL);}
    public void setClipboard(String string){glfwSetClipboardString(windowHandle, string);}
    public String getClipboard(){return glfwGetClipboardString(windowHandle);}
    public void setWindowPosition(Vector2i position){glfwSetWindowPos(windowHandle, position.x,position.y);}
    public Vector2f getWindowPosition(){IntBuffer x =BufferUtils.createIntBuffer(2);glfwGetWindowPos(windowHandle, x, x);return new Vector2f(x.get(0), x.get(1));}
    public void setWindowTitle(String title){glfwSetWindowTitle(windowHandle, title);}
    public void showWindow(){glfwShowWindow(windowHandle);}
    public void hideWindow(){glfwHideWindow(windowHandle);}
    public void enableVsync(){glfwSwapInterval(1);}
    public void disableVsync(){glfwSwapInterval(0);}


    private void loop(){
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;
        glEnable(GL_TEXTURE_2D);
        while (!glfwWindowShouldClose(windowHandle)){
            // Poll events
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            
            // Do our update
            while (dt >= 0){
                if (currentScene != null) {
                    currentScene.update(dt);
                }
                dt -= 1;
            }

            if (currentScene != null) {
                currentScene.render();
            }

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0+cameraPosition.x, width+cameraPosition.x, height+cameraPosition.y, 0+cameraPosition.y, 1, -1);
            glMatrixMode(GL_MODELVIEW);

            glfwSwapBuffers(windowHandle);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
    private final ArrayList<IReceiver> IReceivers = new ArrayList<>();

    public void addInputReceiver(IReceiver IReceiver) {
        IReceivers.add(IReceiver);
    }

    private void events(){
        glfwSetKeyCallback(windowHandle, (long window, int key, int scancode, int action, int mods) -> {
            for (IReceiver IReceiver : IReceivers) {
                IReceiver.key(key, scancode, action, mods);
            }
        });

        glfwSetMouseButtonCallback(windowHandle, (final long window, final int button, final int action, final int mods) -> {
            for (IReceiver IReceiver : IReceivers) {
                IReceiver.mouse(button, action, mods);
            }
        });
        glfwSetWindowFocusCallback(windowHandle, (l, b) -> {
            if(b){
                for (IReceiver IReceiver : IReceivers) {
                    IReceiver.window_focus();
                }
            }else{
                for (IReceiver IReceiver : IReceivers) {
                    IReceiver.window_unfocus();
                }
            }
        });
        glfwSetCursorEnterCallback(windowHandle, (l, b) -> {
            if(b){
                for (IReceiver IReceiver : IReceivers) {
                    IReceiver.mouse_window_enter();
                }
            }else{
                for (IReceiver IReceiver : IReceivers) {
                    IReceiver.mouse_window_exit();
                }
            }
        });
        glfwSetWindowPosCallback(windowHandle, (l, i, i1) -> {
            for (IReceiver IReceiver : IReceivers) {
                IReceiver.window_position(new Vector2i(i, i1));
            }
        });
        glfwSetWindowSizeCallback(windowHandle, (l, i, i1) -> {
            for (IReceiver IReceiver : IReceivers) {
                IReceiver.window_size(new Vector2i(i, i1));
            }
        });
        glfwSetScrollCallback(windowHandle, (l, v, v1) -> {
            for (IReceiver IReceiver : IReceivers) {
                IReceiver.mouse_scroll(new Vector2d(v, v1));
            }
        });
    }
}
