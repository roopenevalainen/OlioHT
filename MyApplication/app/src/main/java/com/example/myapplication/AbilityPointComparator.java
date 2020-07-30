package com.example.myapplication;

import java.util.Comparator;

public class AbilityPointComparator implements Comparator <Swing> { //compares swings abilitypoints

    public int compare(Swing swing1, Swing swing2) {
        return swing2.getAbilityPoints() - swing1.getAbilityPoints();
    }
}
