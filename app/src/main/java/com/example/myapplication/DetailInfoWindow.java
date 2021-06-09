package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailInfoWindow extends Activity {
    TextView buildingInfo, buildingNameText;
    Button guideRouteBtn;
    ImageView buildingImage;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailinfowindow);

        buildingInfo = (TextView) findViewById(R.id.buildingInfo);

        buildingNameText = (TextView) findViewById(R.id.buildingNameText);

        guideRouteBtn = (Button) findViewById(R.id.guideRouteBtn);

        buildingImage = (ImageView) findViewById(R.id.buildingImage);


        Button guideRouteBtn = (Button) findViewById((R.id.guideRouteBtn));
        guideRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

}
