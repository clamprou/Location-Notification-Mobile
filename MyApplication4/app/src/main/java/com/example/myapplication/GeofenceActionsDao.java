package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeofenceActionsDao {
    @Query("SELECT * FROM GEOFENCEACTIONS")
    public List<GeofenceAction> getAllGeofenceActions();

    @Insert
    public void insertGeofenceActions(GeofenceAction... geofenceAction);
}
