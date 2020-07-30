package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ListManager {

    public List<Swing> sortListBySwingName(List<Swing> swingData){ //sorts list so that same named swings are next to each other to help list sorting
        String tempSwingName;
        List<Swing> refinedSwingData = new ArrayList<Swing>();
        Collections.sort(swingData);
        tempSwingName = swingData.get(0).getSwingName();
        int i = 0;
        int abilityPoints = 0;

        while(i < swingData.size()) { //checks if there is more same named swings and adds their ability points together
            Swing swing = new Swing();
            if (tempSwingName.equals(swingData.get(i).getSwingName())) {
                abilityPoints = abilityPoints + swingData.get(i).getAbilityPoints();
                i += 1;

            } else {
                swing.setAbilityPoints(abilityPoints);
                swing.setSwingName(tempSwingName);
                abilityPoints = swingData.get(i).getAbilityPoints();
                tempSwingName = swingData.get(i).getSwingName();
                refinedSwingData.add(swing);
                i += 1;
            }

        }
        Swing swing = new Swing();
        swing.setAbilityPoints(abilityPoints);
        swing.setSwingName(tempSwingName);
        refinedSwingData.add(swing);

        return refinedSwingData;
    }

    public List<Swing> sortListByAbilityPoints(List <Swing> swingData) { //sorts list so that swings that have most ability points are in top of listview

       Collections.sort(swingData, new AbilityPointComparator());
       return swingData;
    }
}
