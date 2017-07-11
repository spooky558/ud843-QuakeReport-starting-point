package com.example.android.quakereport;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;

/**
 * Created by Peter on 06/07/2017.
 */

public class Earthquake {

    //String magnitude of earthquake
    private double mMagnitude;

    //String location of earthquake
    private String mLocation;

    //String time of earthquake
    private Long mTimeInMilliseconds;

    //Website url of earthquake
    private String mUrl;

    public Earthquake(double magnitude, String location, Long timeInMilliseconds, String url) {

        mMagnitude = magnitude;

        mLocation = location;

        mTimeInMilliseconds = timeInMilliseconds;

        mUrl = url;
    }

    //Get magnitude of quake
    public double getMagnitude() {return mMagnitude;}

    public String getLocation() {return mLocation;}

    public Long getTimeInMilliseconds() {return mTimeInMilliseconds;}

    public String getUrl() {return mUrl;}

}
