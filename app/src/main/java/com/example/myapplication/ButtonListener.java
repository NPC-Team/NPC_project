package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonListener implements Button.OnClickListener {

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mylocation) {
            Toast.makeText(v.getContext(), "location" , Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.addmarker) {
            Toast.makeText(v.getContext(), "marker" , Toast.LENGTH_SHORT).show();
        }
    }
}
