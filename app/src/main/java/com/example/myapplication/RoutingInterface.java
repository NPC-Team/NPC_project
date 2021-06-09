package com.example.myapplication;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoutingInterface {
    @GET("{profile}/{startX},{startY};{endX},{endY}?overview=false")
    Call<Object> getRoute(@Path("profile") String profile, @Path("startX") double startX, @Path("startY") double startY, @Path("endX") double endX, @Path("endY") double endY);
}
