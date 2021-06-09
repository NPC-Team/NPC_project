package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.compass.IOrientationProvider;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

public class Map_activity extends AppCompatActivity implements LocationListener, MapEventsReceiver {

    private static Context mContext;

    private BackPressCloseHandler backPressCloseHandler;

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;

    private MyLocationNewOverlay mLocationOverlay;

    int deviceOrientation = 0;
    float gpsspeed;
    float gpsbearing;
    float lat = 0;
    float lon = 0;
    float alt = 0;

    private LocationManager lm;
    private Location currentLocation = null;

    EnableAddMarker addMarker = new EnableAddMarker();
    ButtonListener buttonListener = new ButtonListener();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's
        //tile servers will get you banned based on this string

        //inflate and create the map
        setContentView(R.layout.activity_map);

        mContext = this;

        FloatingActionButton mylocation = (FloatingActionButton) findViewById(R.id.mylocation);
        FloatingActionButton addmarker = (FloatingActionButton) findViewById(R.id.addmarker);

        mylocation.setOnClickListener(buttonListener);
        addmarker.setOnClickListener(buttonListener);

        //뒤로가기 버튼 핸들러
        backPressCloseHandler = new BackPressCloseHandler(this);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(19.5);
        GeoPoint startPoint = new GeoPoint(33.4585, 126.5611);
        mapController.setCenter(startPoint);

        mLocationOverlay = new MyLocationNewOverlay(map);
        mLocationOverlay.enableMyLocation();
//        mLocationOverlay.enableFollowLocation();
        map.getOverlays().add(mLocationOverlay);

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        map.getOverlays().add(0, mapEventsOverlay);

//        List<GeoPoint> geoPoints = new ArrayList<>();
//        //add your points here
//        GeoPoint point1 = new GeoPoint(33.45915, 126.56118);
//        GeoPoint point2 = new GeoPoint(33.45743,126.56122);
//        GeoPoint point3 = new GeoPoint(33.45741,126.56166);
//
//        geoPoints.add(point1);
//        geoPoints.add(point2);
//        geoPoints.add(point3);
//        Polyline line = new Polyline();   //see note below!
//        line.setPoints(geoPoints);
//        line.setColor(Color.parseColor("#FF6200EE"));
//        line.setWidth((float)40.0);
//        line.getPaint().setStrokeCap(Paint.Cap.ROUND);
//        map.getOverlays().add(line);


        Marker building = new Marker(map);
        GeoPoint point = new GeoPoint(33.45736, 126.56017);
        building.setPosition(point);
        building.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(building);

        // 공대 4호관 마커 추가

        Marker ColEngineering4 = new Marker(map);
        GeoPoint colEngineering4 = new GeoPoint(33.45481,126.56527);

        ColEngineering4.setPosition(colEngineering4);
        ColEngineering4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(ColEngineering4);



        ColEngineering4.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("공대4호관");
                bubble_description.setText("컴퓨터공학전공/메카트로닉스공학전공/에너지공학전공");


                Button bubble_moreinfo = (Button) findViewById(R.id.bubble_moreinfo);
                bubble_moreinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        setContentView(R.layout.detailinfowindow);
                        TextView buildingNameText = (TextView) findViewById(R.id.buildingNameText);
                        TextView buildingInfo = (TextView) findViewById(R.id.buildingInfo);
                        ImageView buildingImage = (ImageView) findViewById(R.id.buildingImage);
                        buildingNameText.setText("공대 4호관");
                        buildingInfo.setText("공대 4호관은 건축학전공, 메카트로닉스전공, 에너지공학전공, 컴퓨터공학전공이 있습니다.");
                        buildingImage.setImageResource(R.drawable.engineering4);

                    }

                });

                return true;


            }

//                        Button bubble_moreinfo = (Button) findViewById(R.id.bubble_moreinfo);
//
//            public void setBubble_moreinfo(Button bubble_moreinfo) {
//                this.bubble_moreinfo = bubble_moreinfo;
//                bubble_moreinfo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        setContentView(R.layout.detailinfowindow);
//                        TextView buildingNameText = (TextView) findViewById(R.id.buildingNameText);
//                        TextView buildingInfo = (TextView) findViewById(R.id.buildingInfo);
////                ImageView buildingImage = (ImageView) findViewById(R.id.buildingImage);
//                        buildingNameText.setText("공대 4호관");
//                        buildingInfo.setText("공대 4호관은 건축학전공, 메카트로닉스전공, 에너지공학전공, 컴퓨터공학전공이 있습니다.");
//
//                    }
//                });
//
//
//             }

            //            public Button getBubble_moreinfo() {
//                setContentView(R.layout.detailinfowindow);
//                TextView buildingNameText = (TextView) findViewById(R.id.buildingNameText);
//                TextView buildingInfo = (TextView) findViewById(R.id.buildingInfo);
////                ImageView buildingImage = (ImageView) findViewById(R.id.buildingImage);
//                buildingNameText.setText("공대 4호관");
//                buildingInfo.setText("공대 4호관은 건축학전공, 메카트로닉스전공, 에너지공학전공, 컴퓨터공학전공이 있습니다.");
//
//                return bubble_moreinfo;
//            }


        });


        Marker ColEngineering3 = new Marker(map);
        GeoPoint colEngineering3 = new GeoPoint(33.45603,126.56051);

        ColEngineering3.setPosition(colEngineering3);
        ColEngineering3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(ColEngineering3);

        ColEngineering3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("체육관");
                bubble_description.setText("설명 추가");
                return true;
            }


        });


        Marker Building1 = new Marker(map);
        GeoPoint building1 = new GeoPoint(33.45885,126.56186);

        Building1.setPosition(building1);
        Building1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building1);

        Building1.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("제주대박물관");
                bubble_description.setText("설명추가");

                return true;
            }
        });

        Marker Building2 = new Marker(map);
        GeoPoint building2 = new GeoPoint(33.45846,126.56058);

        Building2.setPosition(building2);
        Building2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building2);

        Building2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("공대3호");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building3 = new Marker(map);
        GeoPoint building3 = new GeoPoint(33.45495,126.56311);

        Building3.setPosition(building3);
        Building3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building3);

        Building3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("사범 1호");
                bubble_description.setText("설명추가");
                return true;
            }
        });
        Marker Building4 = new Marker(map);
        GeoPoint building4 = new GeoPoint(33.45486,126.56162);

        Building4.setPosition(building4);
        Building4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building4);

        Building4.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("사범 1호");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building5 = new Marker(map);
        GeoPoint building5 = new GeoPoint(33.4545,126.56355);

        Building5.setPosition(building5);
        Building5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building5);

        Building5.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("정보화본부");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building6 = new Marker(map);
        GeoPoint building6 = new GeoPoint(33.45594,126.56203);

        Building6.setPosition(building6);
        Building6.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building6);

        Building6.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("대학본관");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building7 = new Marker(map);
        GeoPoint building7 = new GeoPoint(33.455,126.56063);

        Building7.setPosition(building7);
        Building7.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building7);

        Building7.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("학생회관");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building8 = new Marker(map);
        GeoPoint building8 = new GeoPoint(33.45359,126.55946);

        Building8.setPosition(building8);
        Building8.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building8);

        Building8.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("아라뮤즈홀");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building9 = new Marker(map);
        GeoPoint building9 = new GeoPoint(33.45288,126.56387);

        Building9.setPosition(building9);
        Building9.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building9);

        Building9.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("의전원");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building10 = new Marker(map);
        GeoPoint building10 = new GeoPoint(33.45606,126.5644);

        Building10.setPosition(building10);
        Building10.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building10);

        Building10.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("간호대학");
                bubble_description.setText("설명추가");
                return true;
            }
        });

        Marker Building11 = new Marker(map);
        GeoPoint building11 = new GeoPoint(33.45265,126.56086);

        Building11.setPosition(building11);
        Building11.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        map.getOverlays().add(Building11);

        Building11.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                TextView bubble_title = (TextView) findViewById(R.id.bubble_title);
                TextView bubble_description = (TextView) findViewById(R.id.bubble_description);
                bubble_title.setText("중앙도서관");
                bubble_description.setText("설명추가");
                return true;
            }
        });






        //------------------------



        building.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                InfoWindow infoWindow = new MyInfoWindow(R.layout.infowindow, map);
                marker.setInfoWindow(infoWindow);
                marker.showInfoWindow();
                return true;
            }
        });

        requestPermissionsIfNecessary(new String[]{
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            //on API15 AVDs,network provider fails. no idea why
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        } catch (Exception ex) {
            //usually permissions or
            //java.lang.IllegalArgumentException: provider doesn't exist: network
            ex.printStackTrace();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up

    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (map == null)
            return;

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
    }

    @SuppressLint("DefaultLocale")
    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {

        addMarker.addMarker(map, p);
        return true;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }

    //뒤로가기 핸들러 클래스
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

    //메뉴 만드는 클래스
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map, menu);
        return true;
    }

    //메뉴 띄우는 클래스
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_10min:
                Intent intent = new Intent(this, canigoPopupActivity.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.action_whattaeat:
//                Toast.makeText(this, "뭐먹젠 미구현", Toast.LENGTH_SHORT).show();
//                return true;
                Intent intent1 = new Intent(this, whereisfoodActivity.class);
                startActivity(intent1);
//            case R.id.action_toilet:
//                Toast.makeText(this, "화장실 어디인 미구현", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //데이터 받는 클래스
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String startToast = data.getStringExtra("출발지");
                String comeToast = data.getStringExtra("도착지");
                String startvalueToast = data.getStringExtra("출발좌표");
                String comevalueToast = data.getStringExtra("도착좌표");
                Toast.makeText(this,"선택한 출발지는 "+startToast+": "+startvalueToast+"\n선택한 도착지는 "+comeToast+": "+comevalueToast,Toast.LENGTH_SHORT).show();
            }
        }
    }

}