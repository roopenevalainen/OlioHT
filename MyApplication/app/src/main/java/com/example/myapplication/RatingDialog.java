package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;


public class RatingDialog {

    public static void finishApp (AppCompatActivity appCompatActivity){
        appCompatActivity.finish();
    }

    public void refreshApp (AppCompatActivity appCompatActivity) {
        appCompatActivity.recreate();
    }

    private Activity activity;
    private AlertDialog dialog;
    RatingBar ratingBar;
    Button ratingBtn;
    float stars;


    RatingDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void startRatingDialog (final String swingName, final Context context) { //builds a popup which have rating bar and submit button for swing

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.rating_dialog, null));
        dialog = builder.create();
        dialog.show();
        ratingBar = (RatingBar) dialog.findViewById(R.id.ratingStars);
        ratingBtn = (Button) dialog.findViewById(R.id.submitRatingBtn);
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager fileManager = new FileManager();
                stars = ratingBar.getRating();
                fileManager.writeFile(swingName, context, stars); //writes temp file with swing name and ability points
                dialog.dismiss();

            }
        });
    }

    public float getStars() {
        return stars;
    }
}
