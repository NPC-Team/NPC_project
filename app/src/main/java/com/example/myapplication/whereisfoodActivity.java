package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class whereisfoodActivity extends Activity {

    Button btnFood1, btnFood2, btnFood3, btnFood4, btnFood5, btnFood6, btnFood7, btnFood8;

    private MapView map = null;

    private List<restuarnatSample> restuarnatSamples = new ArrayList<>();

    private void readRestuarantData() {
        InputStream is = getResources().openRawResource(R.raw.restaurantlocation);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: "+ line);
                String [] tokens = line.split(",");
                restuarnatSample sample = new restuarnatSample();
                sample.setNumber(Integer.parseInt(tokens[0]));
                sample.setName((tokens[1]));
                sample.setLatitude(Double.parseDouble(tokens[2]));
                sample.setLongitude(Double.parseDouble(tokens[3]));
                restuarnatSamples.add(sample);

                Log.d("MyActivity", "Just created: " + sample);

//                여기서 바로 오버레이 찍는 함수한테 토큰으로 떼놓은 값 넘기면서 호출함녀 됨
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading NpcCsv file on line" + line, e);
            e.printStackTrace();
        }
    }

//

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        readRestuarantData();
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_food);

//        readRestuarantData();

        Button btnFood1 = (Button) findViewById(R.id.btnFood1);
        btnFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int i=3;
////
//                list.get(i);
//                List<String> line = list.get(i);

                Context ctx = getApplicationContext();
                Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

                setContentView(R.layout.activity_map);

                map = (MapView) findViewById(R.id.map);
                map.setTileSource(TileSourceFactory.MAPNIK);

                IMapController mapController = map.getController();
                mapController.setZoom(19.5);




//                GeoPoint food1Point = new GeoPoint(Double.parseDouble(line.get(2)), Double.parseDouble(line.get(3)));
                GeoPoint food1Point = new GeoPoint(33.46025,126.56097);


                Marker marker = new Marker(map);
                marker.setPosition(food1Point);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                map.getOverlays().add(marker);

                map.getController().animateTo(food1Point);

            }
        });

        Button btnFood2 = (Button) findViewById(R.id.btnFood2);
        btnFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context ctx = getApplicationContext();
                Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

                setContentView(R.layout.activity_map);

                map = (MapView) findViewById(R.id.map);
                map.setTileSource(TileSourceFactory.MAPNIK);

                IMapController mapController = map.getController();
                mapController.setZoom(19.5);

                GeoPoint food2Point = new GeoPoint(33.46034, 126.56154);

                Marker marker = new Marker(map);
                marker.setPosition(food2Point);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                map.getOverlays().add(marker);
//                custom_overlays++;

                map.getController().animateTo(food2Point);

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
