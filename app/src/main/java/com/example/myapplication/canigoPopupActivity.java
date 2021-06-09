package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.IdRes;

public class canigoPopupActivity extends Activity {

    String profile = "routed-foot";//초기화
    String starttointent;
    String cometointent;
    String startvalue;
    String comevalue;
    RadioButton radiowalk, radiobike, radiocar;
    RadioGroup profileradioGroup;



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

        //라디오 버튼 설정
        radiowalk = (RadioButton) findViewById(R.id.radiowalk);
        radiobike = (RadioButton) findViewById(R.id.radiobike);
        radiocar = (RadioButton) findViewById(R.id.radiocar);
//         radiowalk.setOnClickListener(radioButtonClickListener);
//         radiobike.setOnClickListener(radioButtonClickListener);
//         radiocar.setOnClickListener(radioButtonClickListener);

        // 라디오 그룹 설정
        profileradioGroup = (RadioGroup) findViewById(R.id.profileradiogroup);
        profileradioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        //어댑터 뷰 연결
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
        intent.putExtra("형식", profile);
        intent.putExtra("출발지", starttointent);
        intent.putExtra("도착지", cometointent);
        intent.putExtra("출발좌표", startvalue);
        intent.putExtra("도착좌표", comevalue);
        setResult(RESULT_OK, intent);
        //액티비티(팝업) 닫기
        finish();
    }

    //라디오 버튼 클릭 리스너
//    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener(){
//        @Override public void onClick(View view) {
//        }
//    };

    //라디오 그룹 클릭 리스너
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.radiowalk){
                profile = "routed-foot";
                Toast.makeText(canigoPopupActivity.this, "도보 기준으로 계산합니다.", Toast.LENGTH_SHORT).show(); }
            else if(i == R.id.radiobike){
                profile = "routed-bike";
                Toast.makeText(canigoPopupActivity.this, "자전거 기준으로 계산합니다.", Toast.LENGTH_SHORT).show(); }
            else if(i == R.id.radiocar){
                profile = "routed-car";
                Toast.makeText(canigoPopupActivity.this, "자동차 기준으로 계산합니다.", Toast.LENGTH_SHORT).show(); }
        }
    };

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
