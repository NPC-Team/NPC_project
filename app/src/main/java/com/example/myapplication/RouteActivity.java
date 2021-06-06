package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RouteActivity extends AppCompatActivity {

    TextView routeGuide, routeGuideResult, routeGuideTime, routeGuideTimeResult;
    Button GuideButton;
    Integer distanceResult, timeResult;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        routeGuide = (TextView) findViewById(R.id.routeGuide);
        routeGuideResult = (TextView) findViewById(R.id.routeGuideResult);
        routeGuideTime = (TextView) findViewById(R.id.routeGuideTime);
        routeGuideTimeResult = (TextView) findViewById(R.id.routeGuideTimeResult);

        GuideButton = (Button) findViewById(R.id.GuideButton);

//        GuideButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        routeGuideResult.setText(distanceResult.toString() + "m");
        routeGuideTimeResult.setText(timeResult.toString() + "분");

    }




}
