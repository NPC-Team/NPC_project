package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(2000); //wait per millis
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, Map_activity.class));
        finish();
    }
}