package ru.REStudios.v8.graphics;

import org.joml.Vector4f;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.glActiveTexture;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */


public class Renderer {

    private static boolean rendering = false;

    public static void v8Begin(int render){
        glBegin(render);
        rendering = true;
    }

    public static void v8End(){
        glEnd();
        rendering = false;
    }

    private static void validate(){
        if (!rendering){
            throw new IllegalStateException("Can't draw when not rendering");
        }
    }

    public static void v8Color(Color color){
        validate();
        glColor4f(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
    }

    public static void v8Quad(Vector4f vec){
        validate();

        glVertex2f(vec.x,vec.y);
        glVertex2f(vec.x+vec.z,vec.y);
        glVertex2f(vec.x+vec.z,vec.y+vec.w);
        glVertex2f(vec.x,vec.y+vec.w);
    }

    public static void v8Texture(Texture texture,Vector4f vec){
        validate();
        glActiveTexture(GL_TEXTURE_2D);
        texture.bind();

        glTexCoord2f(1,1);
        glVertex2f(vec.x,vec.y);
        glTexCoord2f(0,1);
        glVertex2f(vec.x+vec.z,vec.y);
        glTexCoord2f(0,0);
        glVertex2f(vec.x+vec.z,vec.y+vec.w);
        glTexCoord2f(1,0);
        glVertex2f(vec.x,vec.y+vec.w);


    }

}
