package com.example.naledi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    public static String hospitals;

    public static void setHospitals(String hospitals) {
        Home.hospitals = hospitals;
    }
    TextView textView;
    Toolbar mToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, null));

        mToolbar =  findViewById(R.id.toolbar1);
        setSupportActionBar(mToolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.naviView);

        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, mToolbar,
                R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable()
                .setColor(getResources().getColor(R.color.white, null));

        textView.setText(hospitals);
        getPermission();

    }

    public void getPermission() {

        if (ContextCompat.checkSelfPermission(Home.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Home.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        } else {
            LocationUtil.getLatLng(Home.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                LocationUtil.getLatLng(Home.this);
            }
        }
    }
}