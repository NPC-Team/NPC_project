package com.example.myapplication;

import android.app.Activity;
import android.content.Context;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MapGenerator {

    Context context;

    MapView map = null;

    public MapGenerator(Context context){
        this.context = context;
    }


    public MapView generateMap(){
        map = (MapView) ((Activity)context).findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(19.5);
        GeoPoint startPoint = new GeoPoint(33.4585, 126.5611);
        mapController.setCenter(startPoint);



        return map;
    }
}

