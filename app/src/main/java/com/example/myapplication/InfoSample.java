package com.example.myapplication;

public class InfoSample {
    private int Number;
    private String Name;
    private String Info;

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

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    @Override
    public String toString() {
        return "InfoSample{" +
                "Number=" + Number +
                ", Name='" + Name + '\'' +
                ", Info='" + Info + '\'' +
                '}';
    }
}
