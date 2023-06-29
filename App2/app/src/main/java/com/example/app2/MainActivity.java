package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.myapplication/GeofenceActions");
        Cursor c = resolver.query(uri,null,null,null,null);

        if(c.moveToFirst()){
            do{
                Log.d("Cursor",c.getString(0));
            }while (c.moveToNext());
        }

    }
}