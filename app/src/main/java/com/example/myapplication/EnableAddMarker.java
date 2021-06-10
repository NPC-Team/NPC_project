package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class EnableAddMarker implements MapEventsReceiver {

    Context context;

    MapView map = null;


    int custom_overlays = 0;

    public EnableAddMarker(Context context, boolean bool){

        this.context = context;
        map = (MapView) ((Activity)context).findViewById(R.id.map);

        if(bool){
            MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(context, this);
            map.getOverlays().add(0, mapEventsOverlay);
        } else {
            map.getOverlays().remove(0);
        }

    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {

        addMarker(map, p);
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }

    public void addMarker(MapView map, GeoPoint p) {

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
