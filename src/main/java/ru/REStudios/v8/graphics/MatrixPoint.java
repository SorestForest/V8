package ru.REStudios.v8.graphics;

import org.joml.Vector2f;

import java.util.ArrayList;

public class MatrixPoint {

    ArrayList<Vector2f> vector2fs;

    public MatrixPoint() {
        this.vector2fs = new ArrayList<>();
    }

    public ArrayList<Vector2f> getVector2fs() {
        return vector2fs;
    }

    public MatrixPoint add(Vector2f vec){
        vector2fs .add(vec);
        return this;
    }

    public void setVector2fs(ArrayList<Vector2f> vector2fs) {
        this.vector2fs = vector2fs;
    }
}
