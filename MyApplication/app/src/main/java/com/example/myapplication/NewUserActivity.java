package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NewUserActivity extends AppCompatActivity {

    Button submitButton, backButton;
    EditText newEmail, newPassword, newReEnteredPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        submitButton = (Button) findViewById(R.id.submitNewUser);
        backButton = (Button) findViewById(R.id.backToLogInScreen);
        newEmail = (EditText) findViewById(R.id.newUsername);
        newPassword = (EditText) findViewById(R.id.newPassword);
        newReEnteredPassword = (EditText) findViewById(R.id.newReEnteredPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_register);
        fAuth = FirebaseAuth.getInstance();

    }

    public void onSubmitBtnClicked (View view) { //checks if fields are filled correctly and creates a new user to firebaseAuth database
        String strNewEmail = newEmail.getText().toString().trim();
        String strNewPassword = newPassword.getText().toString().trim();
        String strNewReEnteredPassword = newReEnteredPassword.getText().toString().trim();

        UserManager userManager = new UserManager();


        if (!(userManager.checkPasswordSimilarity(strNewPassword, strNewReEnteredPassword))) {
            Toast.makeText(NewUserActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return;

        }

        if (strNewEmail.isEmpty()) {
            newEmail.setError("Email is required!");
            return;
        }

        if (strNewPassword.isEmpty()) {
            newPassword.setError("Password is required!");
            return;
        }

        if (strNewPassword.length() < 6) {
            newPassword.setError("Password needs to be more than 6 characters");
        }

        progressBar.setVisibility(View.VISIBLE);


        fAuth.createUserWithEmailAndPassword(strNewEmail, strNewPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(NewUserActivity.this, "New user created!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(NewUserActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        }





    public void onBackToLogInBtnClicked (View view) {
        finish();
    }

    }
