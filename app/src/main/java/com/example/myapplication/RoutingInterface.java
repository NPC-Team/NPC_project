package com.example.myapplication;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoutingInterface {
    @GET("{profile}/{startLat},{startLon};{endLat},{endLon}?overview=false&steps=true")
    Call<Object> getRoute(@Path("profile") String profile, @Path("startLat") double startLat, @Path("startLon") double startLon,
                          @Path("endLat") double endLat, @Path("endLon") double endLon);
}
