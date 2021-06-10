package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static android.content.ContentValues.TAG;

public class MyInfoWindow extends MarkerInfoWindow {

    String result;

    public MyInfoWindow(int layoutResId, MapView mapView) {
        super(layoutResId, mapView);
    }
    public void onClose() {
    }

    public void onOpen(Object arg0) {
        Button btnMoreInfo = (Button) mView.findViewById(R.id.bubble_moreinfo);
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Implement onClick behaviour
                CallOsrm();
//                Toast.makeText(v.getContext(), "click" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void  CallOsrm() {

        RetrofitConnection retrofitConnection = new RetrofitConnection();

        Call call =  retrofitConnection.server.getRoute("driving", 13.388860, 52.517037, 13.397634, 52.529407);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    // 성공적으로 서버에서 데이터 불러옴.
                    Log.d(TAG, "onResponse: " + response.body());
                    Toast.makeText(Map_activity.getContext(), "onResponse: 성공" + response.body(), Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: 성공" + response.body());
                } else {
                    //통신 실패 응답 코드
                    Toast.makeText(Map_activity.getContext(), "false", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //통신 실패 시스템 문제
                Toast.makeText(Map_activity.getContext(), "onFail", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public class RetrofitConnection {

        String URL = "http://router.project-osrm.org/route/v1/"; // 서버 API

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RoutingInterface server = retrofit.create(RoutingInterface.class);
    }
}
