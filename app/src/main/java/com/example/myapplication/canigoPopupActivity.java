package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class canigoPopupActivity extends Activity {

//    TextView txtText;
//    Spinner spinner;
    String starttointent;
    String cometointent;
    String startvalue;
    String comevalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_canigo);

        //UI 객체생성
        Spinner startto = (Spinner)findViewById(R.id.wheretostartSpinner);
        Spinner cometo = (Spinner)findViewById(R.id.wheretocomeSpinner);

        //데이터 가져오기
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("data");
//        txtText.setText(data);

        startto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                starttointent = parent.getItemAtPosition(position).toString();
                startvalue = getResources().getStringArray(R.array.campusValue)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        cometo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cometointent = parent.getItemAtPosition(position).toString();
                comevalue = getResources().getStringArray(R.array.campusValue)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }



    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("출발지", starttointent);
        intent.putExtra("도착지", cometointent);
        intent.putExtra("출발좌표", startvalue);
        intent.putExtra("도착좌표", comevalue);
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }
}
