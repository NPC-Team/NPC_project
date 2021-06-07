package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailInfoWindow extends Activity {
    TextView buildingInfo, buildingNameText;
    Button guideRouteBtn;
    ImageView buildingImage;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        buildingInfo = (TextView) findViewById(R.id.buildingInfo);

        buildingNameText = (TextView) findViewById(R.id.buildingNameText);

        guideRouteBtn = (Button) findViewById(R.id.guideRouteBtn);

        buildingImage = (ImageView) findViewById(R.id.buildingImage);


    }

}
