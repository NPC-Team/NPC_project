package com.example.myapplication;

public class CsvSampele {
    private int Number;
    private String Name;
    private double Latitude;
    private double Longitude;

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @Override
    public String toString() {
        return "NpcCsv {" + "Number='" + Number + '\'' + ", Name=" + Name + ", Latitude=" + Latitude + ", Longitude=" + Longitude + '}';
    }
}
