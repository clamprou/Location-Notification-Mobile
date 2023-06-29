package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

// Implement OnMapReadyCallback.
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://"+GeofenceActionsProvider.AUTHORITY+"/"+GeofenceActionsProvider.PATH);
        Cursor c = resolver.query(uri,null,null,null,null);
        if(c.moveToFirst()){
            do{
                Log.d("Cursor",c.getString(1));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(c.getString(1)), Double.parseDouble(c.getString(2))))
                        .title(c.getString(3)+":"+c.getString(4)));
            }while (c.moveToNext());
        }
    }
}