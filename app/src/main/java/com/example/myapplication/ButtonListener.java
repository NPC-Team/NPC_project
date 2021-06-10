package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.osmdroid.views.MapView;

public class ButtonListener implements Button.OnClickListener {

    Context context;

    MapView map = null;

    boolean location = false;
    boolean addmarker = false;

    MyLocation myLocation;
    EnableAddMarker enableAddMarker;

    public ButtonListener(Context context){

        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        map = (MapView) ((Activity)context).findViewById(R.id.map);

        if (v.getId() == R.id.mylocation) {
            if (!location) {
                Toast.makeText(v.getContext(), "location on" , Toast.LENGTH_SHORT).show();
                myLocation = new MyLocation(context, true);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF6200EE")));
                location = true;
            }
            else {
                Toast.makeText(v.getContext(), "location off" , Toast.LENGTH_SHORT).show();
                myLocation = new MyLocation(context, false);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF03DAC5")));
                location = false;
            }

        } else if (v.getId() == R.id.addmarker) {
            if (!addmarker) {
//                Toast.makeText(v.getContext(), "marker on" , Toast.LENGTH_SHORT).show();
                enableAddMarker = new EnableAddMarker(context, true);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF6200EE")));
                addmarker = true;
            }
            else {
//                Toast.makeText(v.getContext(), "marker off" , Toast.LENGTH_SHORT).show();
                enableAddMarker = new EnableAddMarker(context, false);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF03DAC5")));
                addmarker = false;
            }
        }
    }
}
