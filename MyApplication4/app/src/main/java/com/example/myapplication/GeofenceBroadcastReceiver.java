package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;
import androidx.room.Room;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceBroadcastReceiv";
    private String action = null;
    private Date timestamp=null;
    private Location location=null;

    @Override
    public void onReceive(Context context, Intent intent) {
        GeofenceActionsDatabase database = Room.databaseBuilder(context,GeofenceActionsDatabase.class,"GEOFENCEACTIONS_DB").build();
        GeofenceActionsDao geofenceActionsDao = database.GeofenceActionsDao();
        NotificationHelper notificationHelper = new NotificationHelper(context);
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){ Log.d(TAG,"onReceive: Error receiving geofence event..."); }
        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        for(Geofence geofence:geofenceList){ Log.d(TAG,"onReceive:"+geofence.getRequestId()); }
        location = geofencingEvent.getTriggeringLocation();
        int transitionType = geofencingEvent.getGeofenceTransition();
        timestamp = Calendar.getInstance().getTime();
        //SAVE TO DATABASE location.lat location.lon transitionType timestamp
        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER: action = "Enter";
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT: action = "Exit";
                break;
        }
        Thread t = new Thread(() -> geofenceActionsDao.insertGeofenceActions(new GeofenceAction(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()),action,timestamp.toString())));
        t.start();

        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER: Toast.makeText(context,"ENTERED ΤΗΕ GEOFENCE",Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("ENTERED ΤΗΕ GEOFENCE", "", MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT: Toast.makeText(context,"EXITED FROM GEOFENCE",Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("EXITED FROM GEOFENCE", "", MapsActivity.class);
                break;
        }
    }
}