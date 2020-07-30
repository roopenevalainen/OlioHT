package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView displayed_username;
    TextView displayed_hcp;
    TextView displayed_golfClub;
    Context context = null;
    FirebaseAuth fAuth;
    String userID;
    FirebaseFirestore fStore;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayed_username = (TextView) findViewById(R.id.displayed_username);
        displayed_hcp = (TextView) findViewById(R.id.displayed_hcp);
        displayed_golfClub = (TextView) findViewById(R.id.displayed_golfClub);
        context = MainActivity.this;
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);
        documentReference = fStore.collection("users").document(userID);


    }


    public void onNewSwingBtnClicked (View view) {
        startActivity(new Intent(getApplicationContext(), SwingActivity.class));
    }

    public  void onShowSwingDataBtnClicked (View view) {

        Intent intent = new Intent(MainActivity.this, SwingAnalysisActivity.class);
        startActivity(intent);


    }

    public void onLogOutBtnClicked (View view){ //when user logs out, swing data from temp file is stored to cloud and temp file is emptied
        documentReference.delete();
        final FileManager fileManager  = new FileManager();
        List<Swing> swingList;
        swingList = fileManager.readFileToList(context);
        Map<String, Object> dbSwingData = new HashMap<>();
        int i = 0;
        while (i < swingList.size()) {
            dbSwingData.put(swingList.get(i).getSwingName(), String.valueOf(swingList.get(i).getAbilityPoints()));
            i += 1;
        }
        documentReference.set(dbSwingData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Swing data uploaded to cloud", Toast.LENGTH_SHORT).show();
                fileManager.deleteFile(context);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
            }
        });

    }


    public void onSettingsBtnClicked (View view) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

}