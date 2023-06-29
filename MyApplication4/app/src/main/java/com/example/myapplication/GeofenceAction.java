package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GeofenceActions")
public class GeofenceAction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "LAT")
    private String lat;
    @ColumnInfo(name = "LON")
    private String lon;
    @ColumnInfo(name = "ACTION")
    private String action;
    @ColumnInfo(name = "TIMESTAMP")
    private String timestamp;

    public GeofenceAction() {
    }

    public GeofenceAction(String lat, String lon, String action, String timestamp) {
        this.lat = lat;
        this.lon = lon;
        this.action = action;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GeofenceAction{" +
                "id=" + id +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
