package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SwingActivity extends AppCompatActivity {


    TextView swingInfo;
    RatingDialog rd;
    private String swingName;
    Context context = null;
    Button ratingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swing);

        swingInfo = (TextView) findViewById(R.id.swingInfoTxt);
        SwingGenerator newSwing = new SwingGenerator();
        newSwing.setSwingShapes();
        newSwing.setSwingLengths();
        newSwing.setSwingLength();
        newSwing.setSwingShape();
        String swing = newSwing.getSwingShape() + " " + newSwing.getSwingLength() + "m";
        swingInfo.setText(swing);
        rd = new RatingDialog(SwingActivity.this);
        swingName = newSwing.getSwingShape() + "_" + newSwing.getSwingLength();
        context = SwingActivity.this;
        ratingBtn = (Button) findViewById(R.id.swingRatingBtn);


    }

    public void onSwingRatingBtnClicked(View view) { //when user has hit swing, he can rate it only ones. After rating rate button disappears
        rd.startRatingDialog(swingName, context);
        ratingBtn.setVisibility(View.INVISIBLE);
        ratingBtn.setClickable(false);

    }

    public void onBackFromSwingBtnClicked (View view) {
        finish();
    }

    public void onNextSwingBtnClicked (View view) {
        recreate();
    } //if user wants to keep on swinging, he will recreate activity by pressing next swing



}