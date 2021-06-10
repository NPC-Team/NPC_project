package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

public class DrawRoute {

    Context context;

    MapView map = null;

    public DrawRoute(Context context) {

        this.context = context;
        map = (MapView) ((Activity) context).findViewById(R.id.map);

        List<GeoPoint> point = new ArrayList<>();
        //add your points here
        GeoPoint point1 = new GeoPoint(33.45854,126.56034);
        GeoPoint point2 = new GeoPoint(33.45869,126.56114);
        GeoPoint point3 = new GeoPoint( 33.45504,126.56115);
        GeoPoint point4 = new GeoPoint( 33.45481, 126.56180);

//        33.45854,126.56034
//
//        33.45869,126.56114
//
//        33.45504,126.56115
//
//        33.45481, 126.56180

//        for(int i = 0;i < point.size();i++){
//            point.add(point.get(i));
//        }

        point.add(point1);
        point.add(point2);
        point.add(point3);
        point.add(point4);
        Polyline line = new Polyline();   //see note below!
        line.setPoints(point);
        line.setColor(Color.parseColor("#FF6200EE"));
        line.setWidth((float)40.0);
        line.getPaint().setStrokeCap(Paint.Cap.ROUND);
        map.getOverlays().add(line);
    }
}