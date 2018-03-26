package com.example.estudiante.vigud;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class ProximityAlerts extends Service {

    private LocationManager locationManager;
    private boolean active = false;

    private static final String PROX_ALERT_INTENT =
            "cl.unab.mauro.androidproximityalertproject.ProxAlertActivity";
    private static final String PROX_CARTELERA_ALERT_INTENT =
            "cl.unab.mauro.androidproximityalertproject.ProxAlertCarteleraActivity";

    public ProximityAlerts() {
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Notificaciones Activadas", Toast.LENGTH_LONG).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityIntentReceiver(), filter);

        setProximityAlert(-33.020758,-71.547234,25f,300000,1,1);
        setProximityAlert(-33.020787, -71.546043,60f,300000,2,2 );

        return START_NOT_STICKY; //indica que el servicio no debe recrearse al ser destruido sin importar que haya quedado un trabajo pendiente.
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        active = false;
        stopSelf();
        Toast.makeText(this, "Notificaciones Desactivadas", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("MissingPermission")
    private void setProximityAlert(double lat, double lon, float radius, long expiration, final long eventID, int requestCode)
    {

        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra(ProximityIntentReceiver.EVENT_ID_INTENT_EXTRA, eventID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        locManager.addProximityAlert(lat, lon, radius, expiration, pendingIntent);
    }
}
