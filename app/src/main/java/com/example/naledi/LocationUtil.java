package com.example.naledi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import static android.content.Context.LOCATION_SERVICE;

public class LocationUtil implements LocationListener {

    private  static LocationUtil locationUtil = new LocationUtil();

    String latLng = "";
    static LocationManager locationManager;
    static LocationListener locationListener = locationUtil;

    @SuppressLint("MissingPermission")
    protected static void getLatLng(Context context) {

        try {

            locationManager = (LocationManager) context.getApplicationContext()
                    .getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 5, locationListener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {

        latLng = "" + location.getLatitude() + "," + location.getLongitude();
        PlaceApi.setLocation(latLng);
        locationManager.removeUpdates(locationListener);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
