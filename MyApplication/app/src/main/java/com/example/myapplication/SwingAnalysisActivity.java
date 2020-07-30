package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwingAnalysisActivity extends AppCompatActivity {

    Context context = null;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //data from temp file is transfered into list, that is sorted and made to string format and displayed on listview
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_swing_analysis);
            listView = (ListView) findViewById(R.id.swingListView);
            context = SwingAnalysisActivity.this;
            FileManager fileManager = new FileManager();
            ListManager listManager = new ListManager();
            List<Swing> swingData;
            swingData = fileManager.readFileToList(context);
            swingData = listManager.sortListBySwingName(swingData);
            List <String> displayedArray = new ArrayList<>();
            int i = 0;
            swingData = listManager.sortListByAbilityPoints(swingData);
            while(i < swingData.size()) {
                displayedArray.add(swingData.get(i).getSwingName() + ": " + swingData.get(i).getAbilityPoints());
                i += 1;
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, displayedArray);
            listView.setAdapter(arrayAdapter);
            // int i = 0;
            //while (i < swingData.size()){
            //System.out.println(swingData.get(i).getSwingName() + ": " + swingData.get(i).getAbilityPoints());
            // i += 1;
            // }
            fileManager.writeFileFromList(context, swingData); //after data from file is transfered into list, its also parsed so that same named swings appear only ones
        }catch (IndexOutOfBoundsException e){                   // thats why new file is now writed and old one discarted
            Toast.makeText(SwingAnalysisActivity.this, "Shoot some balls first!", Toast.LENGTH_LONG).show();
        }

    }

    public  void onBackFromSwingAnalysisBtnClicked(View view) {
        finish();
    }
}