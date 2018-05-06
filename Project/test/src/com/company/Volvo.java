package com.company;

import java.util.Random;

public class Volvo  extends Car {
    int model;
    Random rand = new Random();
    public int setM(){
        return rand.nextInt();
    }
    public Volvo(int wheels, int color, int model) {
        super(wheels, color);
        this.model = setM();
    }

    @Override
    public String toString() {
        return "Volvo{" +
                "model=" + model +
                ", wheels=" + wheels +
                ", color=" + color +
                '}';
    }
}
