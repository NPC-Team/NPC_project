package com.example.myapplication;

import android.app.Activity;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class BackPressCloseHandler {
    private  long backKeyPressedTime = 0;
    private  Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity context){
        this.activity = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public  void onBackPressed(){
        if (System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finishAffinity();
            toast.cancel();
        }
    }

    public void showGuide(){
        toast = Toast.makeText(activity,"\'뒤로\'버튼을 한번 더 누르시면 어플이 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
