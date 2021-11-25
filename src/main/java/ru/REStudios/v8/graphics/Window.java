package ru.REStudios.v8.graphics;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import ru.REStudios.v8.components.custom.InputReceiver;
import ru.REStudios.v8.utils.Time;

import java.io.IOException;
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

        // Create window
        windowHandle = glfwCreateWindow(this.width, this.height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);
        // Enable v-sync
        glfwSwapInterval(vsync ? 1 : 0);

        // Make the window visible
        glfwShowWindow(windowHandle);

        glfwSetKeyCallback(windowHandle, (long window, int key, int scancode, int action, int mods) -> {
            for (InputReceiver inputReceiver : inputReceivers) {
                inputReceiver.receive(key, scancode, action, mods);
            }
        });

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
    private final ArrayList<InputReceiver> inputReceivers = new ArrayList<>();

    public void addInputReceiver(InputReceiver inputReceiver) {
        inputReceivers.add(inputReceiver);
    }
}
