package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class whereisfoodActivity extends Activity {

    Button btnFood1, btnFood2, btnFood3, btnFood4, btnFood5, btnFood6, btnFood7, btnFood8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_food);

        Button btnFood1 = (Button) findViewById(R.id.btnFood1);
        btnFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood2 = (Button) findViewById(R.id.btnFood2);
        btnFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood3 = (Button) findViewById(R.id.btnFood3);
        btnFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood4 = (Button) findViewById(R.id.btnFood4);
        btnFood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood5 = (Button) findViewById(R.id.btnFood5);
        btnFood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood6 = (Button) findViewById(R.id.btnFood6);
        btnFood6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood7 = (Button) findViewById(R.id.btnFood7);
        btnFood7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood8 = (Button) findViewById(R.id.btnFood8);
        btnFood8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
    }


}
