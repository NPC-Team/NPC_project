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


        TextView buildingNameText = (TextView) findViewById(R.id.buildingNameText);
        TextView buildingInfo = (TextView) findViewById(R.id.buildingInfo);
        ImageView buildingImage = (ImageView) findViewById(R.id.buildingImage);
//        Button guideRouteBtn = (Button) findViewById(R.id.guideRouteBtn);

        buildingNameText.setText("공대 4호관");
        buildingInfo.setText("공대 4호관은 건축학전공, 메카트로닉스전공, 에너지공학전공, 컴퓨터공학전공이 있습니다. 기계공학전공,건축공학전공 사무실은 1층에 있고, 매카트로닉스공학전공사무실은 2층에 있습니다." +
                "에너지공학전공 사무실은 3층에 있고, 컴퓨터공학전공 사무실은 4층에 있습니다.  ");
        buildingInfo.setTextSize(20);
//        guideRouteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        buildingImage.setImageResource(R.drawable.engineering4);


        Button guideRouteBtn = (Button) findViewById((R.id.guideRouteBtn));
        guideRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

}
