package com.example.myapplication;

public class Swing implements Comparable <Swing>{ //swing class that contains comparable interface for sorting swing arrays by name
    String swingName;
    int abilityPoints;

    public void setSwingName(String swingName) {
        this.swingName = swingName;
    }

    public void setAbilityPoints(int abilityPoints) {
        this.abilityPoints = abilityPoints;
    }

    public String getSwingName() {
        return swingName;
    }

    public int getAbilityPoints() {
        return abilityPoints;
    }

    public int compareTo (Swing swing){
        int compare = swingName.compareTo(swing.swingName);
        return compare;
    }

}

