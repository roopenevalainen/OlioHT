/*package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText oldPassword;
    EditText newPassword;
    EditText reEnteredNewPassword;
    Button submitButton;
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = (EditText) findViewById(R.id.enterOldPassword);
        newPassword = (EditText) findViewById(R.id.enterNewPassword);
        reEnteredNewPassword = (EditText) findViewById(R.id.reEnterNewPassword);
        submitButton = (Button) findViewById(R.id.submitNewPasswordBtn);
        context = ChangePasswordActivity.this;
    }

    public void onSubmitNewPasswordBtnClicked (View view) {
        User user = (User) getIntent().getSerializableExtra("user");
        String strOldPassword = oldPassword.getText().toString();
        String strNewPassword = newPassword.getText().toString();
        String strReEnteredNewPassword = reEnteredNewPassword.getText().toString();

        UserManager um = new UserManager();

        if ((user.getPassword().equals(strOldPassword)) && (um.checkPasswordSimilarity(strNewPassword, strReEnteredNewPassword))) {
            user.setPassword(strNewPassword);

            finish();
        }

        //check password
        //submit
    }
}*/