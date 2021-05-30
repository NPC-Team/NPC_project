package com.example.myapplication;


import android.content.Context;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class EnableAddMarker {

//    Context main = Map_activity.getContext();

    int custom_overlays = 0;

    public void addMarker(MapView map , GeoPoint p) {

        Marker marker = new Marker(map);
        marker.setPosition(p);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        if(custom_overlays > 0){
            map.getOverlays().remove(map.getOverlays().toArray().length - 1);
            custom_overlays--;
        }

        map.getOverlays().add(marker);
        custom_overlays++;

        map.getController().animateTo(p);

        Toast.makeText(Map_activity.getContext(), "클릭 위치는 ("+String.format("%.4f" ,p.getLatitude())+","+String.format("%.4f", p.getLongitude())+")", Toast.LENGTH_SHORT).show();
    }
}
