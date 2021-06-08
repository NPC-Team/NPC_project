package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;



public class whereisfoodActivity extends Activity {

    Button btnFood1, btnFood2, btnFood3, btnFood4, btnFood5, btnFood6, btnFood7, btnFood8;

    private MapView map = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_food);



        Button btnFood1 = (Button) findViewById(R.id.btnFood1);
        btnFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
                //
//                IMapController mapController = map.getController();
//                mapController.setZoom(25.5);
//                GeoPoint food1Point = new GeoPoint(33.460254111206766, 126.5609782935218);
//                mapController.setCenter(food1Point);

                Context ctx = getApplicationContext();
                Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

                setContentView(R.layout.activity_map);

                map = (MapView) findViewById(R.id.map);
                map.setTileSource(TileSourceFactory.MAPNIK);

//                GeoPoint food1Point = new GeoPoint(33.46025, 126.56097);
//                Marker food1Marker = new Marker(map);
//                food1Marker.setPosition(food1Point);
//                food1Marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//                map.getOverlays().add(food1Marker);

//                int custom_overlays = 0;

                IMapController mapController = map.getController();
                mapController.setZoom(19.5);

                GeoPoint food1Point = new GeoPoint(33.46025, 126.56097);

                Marker marker = new Marker(map);
                marker.setPosition(food1Point);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

//                if(custom_overlays > 0){
//                    map.getOverlays().remove(map.getOverlays().toArray().length - 1);
//                    custom_overlays--;
//                }

                map.getOverlays().add(marker);
//                custom_overlays++;

                map.getController().animateTo(food1Point);

            }
        });

        Button btnFood2 = (Button) findViewById(R.id.btnFood2);
        btnFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood3 = (Button) findViewById(R.id.btnFood3);
        btnFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood4 = (Button) findViewById(R.id.btnFood4);
        btnFood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood5 = (Button) findViewById(R.id.btnFood5);
        btnFood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood6 = (Button) findViewById(R.id.btnFood6);
        btnFood6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood7 = (Button) findViewById(R.id.btnFood7);
        btnFood7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
        Button btnFood8 = (Button) findViewById(R.id.btnFood8);
        btnFood8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 버튼을 눌렀을때
            }
        });
    }


}
