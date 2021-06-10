package com.example.myapplication;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MyLocation {

    Context context;

    MapView map = null;

    private MyLocationNewOverlay mLocationOverlay;

    float gpsspeed;
    float gpsbearing;
    float lat = 0;
    float lon = 0;
    float alt = 0;

    boolean headingAble = false;

    private FusedLocationProviderClient fusedLocationClient;




    public MyLocation(Context context, boolean bool) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        this.context = context;
        map = (MapView) ((Activity) context).findViewById(R.id.map);

        mLocationOverlay = new MyLocationNewOverlay(map);

        if (bool) {
            map.getOverlays().add(1, mLocationOverlay);
            mLocationOverlay.enableMyLocation();
            headingAble = true;
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }

        if(!bool) {
            mLocationOverlay.getMyLocationProvider().stopLocationProvider();
            map.getOverlays().remove(1);
            mLocationOverlay = null;
            headingAble = false;
            Log.d("TAG", "MyLocation1: " + headingAble);
        }

        Log.d("TAG", "MyLocation2: " + headingAble);
        map.invalidate();
    }

    LocationCallback locationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(@NonNull @NotNull LocationResult locationResult) {

            for (Location location : locationResult.getLocations()) {
                // Update UI with location data
                // ...
                gpsbearing = location.getBearing();
                gpsspeed = location.getSpeed();
                lat = (float) location.getLatitude();
                lon = (float) location.getLongitude();
                alt = (float) location.getAltitude(); //meters

                //use gps bearing instead of the compass

                float t = (360 - gpsbearing);
                if (t < 0) {
                    t += 360;
                }
                if (t > 360) {
                    t -= 360;
                }
                //help smooth everything out
                t = (int) t;
                t = t / 5;
                t = (int) t;
                t = t * 5;

                if (gpsspeed >= 0.01) {
                    map.setMapOrientation(t);
                    map.getController().setCenter(mLocationOverlay.getMyLocation());
                    //otherwise let the compass take over
                }
                Log.d("", "onLocationResult: " + headingAble);
            }
        }
    };
}