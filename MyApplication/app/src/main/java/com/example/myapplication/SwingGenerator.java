package com.example.myapplication;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwingGenerator { //randomly generates swings for user to hit. Variables are shape and length
    ArrayList <String> swingShapes = new ArrayList<>();
    ArrayList <Integer> swingLengths = new ArrayList<>();
    String swingShape;
    int swingLength;
    int maxSwingLenght = 10;

    public void setSwingShapes() {

        swingShapes.add("Draw");
        swingShapes.add("Fade");
        swingShapes.add("Slice");
        swingShapes.add("Hook");
        swingShapes.add("Straight");

    }

    public void setSwingLengths() {

        swingLengths.add(50);
        swingLengths.add(75);
        swingLengths.add(100);
        swingLengths.add(125);
        swingLengths.add(150);
        swingLengths.add(175);
        swingLengths.add(200);
        swingLengths.add(225);
        swingLengths.add(250);
        swingLengths.add(275);
        swingLengths.add(300);
    }

    public void setSwingShape() {
        Random randomSwingShape = new Random();
        swingShape = swingShapes.get(randomSwingShape.nextInt(4));
    }

    public void setSwingLength() {
        Random randomSwingLenght = new Random();
        swingLength = swingLengths.get(randomSwingLenght.nextInt(10));
    }

    public void setMaxSwingLenght(int maxSwingLenght) {
        this.maxSwingLenght = maxSwingLenght;
    }

    public String getSwingShape() {
        return swingShape;
    }

    public int getSwingLength() {
        return swingLength;
    }

    public int getMaxSwingLenght() {
        return maxSwingLenght;
    }
}

