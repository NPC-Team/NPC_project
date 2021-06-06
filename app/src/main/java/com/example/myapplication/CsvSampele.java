package com.example.myapplication;

public class CsvSampele {
    private int Number;
    private String Name;
    private double Coordinates;

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

    public double getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(double coordinates) {
        Coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "CsvSampele{" + "Number='" + Number + '\'' + ", Name=" + Name + ", Coordinates=" + Coordinates + '}';
    }
}
