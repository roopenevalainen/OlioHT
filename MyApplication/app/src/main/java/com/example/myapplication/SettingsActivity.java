package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsActivity extends AppCompatActivity {

    EditText enterEmail;
    Button sendLink;
    Context context = null;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        enterEmail = (EditText) findViewById(R.id.enterEmail);
        sendLink = (Button) findViewById(R.id.sendLinkToEmail);
        context = SettingsActivity.this;
        fAuth = FirebaseAuth.getInstance();
    }

    public void onChangePasswordBtnClicked (View view) { //sends password reseting email to users wanted address
        fAuth.sendPasswordResetEmail(enterEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(SettingsActivity.this, "Password reset link sent to email " + enterEmail.getText().toString(), Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(SettingsActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }




}