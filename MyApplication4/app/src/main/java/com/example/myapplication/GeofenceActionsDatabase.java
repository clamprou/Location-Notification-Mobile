package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GeofenceAction.class},version = 3)
public abstract class GeofenceActionsDatabase extends RoomDatabase {
    public abstract GeofenceActionsDao GeofenceActionsDao();
}
