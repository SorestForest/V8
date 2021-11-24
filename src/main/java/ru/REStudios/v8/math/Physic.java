package ru.REStudios.v8.math;

import org.joml.Vector2f;

import java.util.Vector;

public class Physic {
    public static double g = 9.78;
    public static double G = 6.67;



    public static class F {
        PowerType type;
        Vector2f direction;
        double impulse;

        public F(double m) {
            type = PowerType.ATTRACTION;
            direction = new Vector2f(0, -1);
            impulse = new P().eval(m);
        }

        public PowerType getType() {
            return type;
        }

        public Vector2f getDirection() {
            return direction;
        }

        public double getImpulse() {
            return impulse;
        }

        @Override
        public String toString() {
            return "F{" +
                    "type=" + type +
                    ", direction=" + direction +
                    ", impulse=" + impulse +
                    '}';
        }
    }
    public static class P implements MathFunction<Double> {

        @Override
        public Double eval(Double... args) {
            if(args.length != 1){
                return 0d;
            }
            double m = args[0];
            return m*g;
        }
    }

}
