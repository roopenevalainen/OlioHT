package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class LogInActivity extends AppCompatActivity {

    EditText editTxtUsername;
    EditText editTxtPassword;
    Button addNewUserbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar_login;
    FirebaseFirestore fStore;
    DocumentReference documentReference;
    String userID;
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LogInActivity.this;
        editTxtUsername = (EditText) findViewById(R.id.editTxtUsername);
        editTxtPassword = (EditText) findViewById(R.id.editTxtPassword);
        addNewUserbtn = (Button) findViewById(R.id.addNewUserBtn);
        fAuth = FirebaseAuth.getInstance(); //Instaciating firebaseAuth on firebaseStorage databases
        fStore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder() //Setting firestore settings for session
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);


        progressBar_login = (ProgressBar) findViewById(R.id.progressbar_login);



        if(fAuth.getCurrentUser() != null) { //FirebaseAuth checks if user is allready signed in
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void onLoginBtnClicked (View view) {

        if(editTxtUsername.getText().toString().isEmpty()) {
            editTxtUsername.setError("Email is required!");
            return;
        }
        if (editTxtPassword.getText().toString().isEmpty()) {
            editTxtPassword.setError("Password is required!");
            return;
        }

        progressBar_login.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(editTxtUsername.getText().toString(), editTxtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()) { //if login is succesful...
                    FileManager fileManager = new FileManager();
                    fileManager.deleteFile(context);
                    userID = fAuth.getCurrentUser().getUid(); //Uid is unique key that identifies user
                    documentReference = fStore.collection("users").document(userID); //firestore creates a document for user
                    fStore.collection("users").document(userID).addSnapshotListener(LogInActivity.this, new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            //app fetches users personal swing data from cloud storage with uid
                            assert documentSnapshot != null;
                            if (documentSnapshot.exists()) {
                                List<Swing> swingData= new ArrayList<Swing>();
                                Map<String, Object> map = documentSnapshot.getData();
                                assert map != null;
                                for(Map.Entry<String, Object> entry : map.entrySet()) {
                                    Swing swing = new Swing();
                                    swing.setSwingName(entry.getKey());
                                    swing.setAbilityPoints(Integer.parseInt(entry.getValue().toString()));
                                    swingData.add(swing); //swing data is collected to a list

                                }
                                FileManager fileManager = new FileManager();
                                fileManager.writeFileFromList(context, swingData); //temporary file is writed from list

                            }else{
                                Toast.makeText(LogInActivity.this, "Shoot some balls first", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                    Toast.makeText(LogInActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class)); //on succes, new activity starts
                }else {
                    Toast.makeText(LogInActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progressBar_login.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onNewUserBtnClicked(View view) {


        startActivity(new Intent(LogInActivity.this, NewUserActivity.class));

    }

}