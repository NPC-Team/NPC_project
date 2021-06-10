package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.List;

public class DrawRoute {

    Context context;

    MapView map = null;

    public DrawRoute(Context context, List<GeoPoint> list) {

        this.context = context;
        map = (MapView) ((Activity) context).findViewById(R.id.map);

        List<GeoPoint> point = list;
        //add your points here
//        GeoPoint point1 = new GeoPoint(33.45915, 126.56118);
//        GeoPoint point2 = new GeoPoint(33.45743,126.56122);
//        GeoPoint point3 = new GeoPoint(33.45741,126.56166);

        for(int i = 0;i < point.size();i++){
            point.add(point.get(i));
        }

//    geoPoints.add(point1);
//    geoPoints.add(point2);
//    geoPoints.add(point3);
        Polyline line = new Polyline();   //see note below!
        line.setPoints(point);
        line.setColor(Color.parseColor("#FF6200EE"));
        line.setWidth((float)40.0);
        line.getPaint().setStrokeCap(Paint.Cap.ROUND);
        map.getOverlays().add(line);
    }
}
