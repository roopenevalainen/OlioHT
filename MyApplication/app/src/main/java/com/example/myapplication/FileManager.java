package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.widget.FitWindowsFrameLayout;

import com.google.common.collect.Collections2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.opencensus.internal.StringUtil;

public class FileManager{

    public void writeFile(String swingName, Context context, float stars) {

        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("temp_swing_file.txt", Context.MODE_APPEND));
            String strStars = String.valueOf(stars);
            String s = swingName + ";" + strStars + "\n";
            osw.write(s);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }
        System.out.println("Kirjoitettu");
    }

    public List<Swing> readFileToList(Context context) { //temp file is read to list so that the data is easier to use


        List<Swing> swingData = new ArrayList<Swing>();

        try{

            InputStream ins = context.openFileInput("temp_swing_file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                Swing swing = new Swing();
                String[] splittedData;
                s = s.replaceAll("\n", "");
                splittedData = s.split(";");
                swing.setSwingName(splittedData[0]);
                swing.setAbilityPoints((int)Double.parseDouble(splittedData[1]));
                swingData.add(swing);
            }

            System.out.println("Tiedosto kirjoitettu listaan.");
            ins.close();
            br.close();
        } catch (IOException e) {
            Log.e("IOException", "Virheellinen syöte");
        }

        return swingData;
    }

    public void writeFileFromList(Context context, List<Swing> swingList) { //temp file is written from list which includes data from cloud for example
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("temp_swing_file.txt", Context.MODE_PRIVATE));
            int i = 0;
            while (i < swingList.size()) {
                String s = swingList.get(i).getSwingName() + ";" + swingList.get(i).getAbilityPoints() + "\n";
                osw.write(s);
                i += 1;
            }
            osw.flush();
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }
        System.out.println("Kirjoitettu");
    }



    public void deleteFile(Context context) { //empties file by writing it to be null
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("temp_swing_file.txt", Context.MODE_PRIVATE));
            osw.write("");
            osw.close();
        } catch (Exception e) {
            Log.e("IOException", "Virhe syötteessä");
        }
        System.out.println("Tyhjennetty");
    }

}

