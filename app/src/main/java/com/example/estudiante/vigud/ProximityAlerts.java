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

    private LocationManager locationManager, locationManager2;
    private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
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
        locationManager2 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        addProximityAlert();
        addProximityCarteleraAlert();

        /*locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATE,
                MINIMUM_DISTANCECHANGE_FOR_UPDATE,
                new MyLocationListener()
        );*/
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

    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
        }
        public void onStatusChanged(String s, int i, Bundle b) {
        }
        public void onProviderDisabled(String s) {
        }
        public void onProviderEnabled(String s) {
        }
    }
    @SuppressLint("MissingPermission")
    private void addProximityAlert() {
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //set data proximity coordenates
            //UNAB
                long POINT_RADIUS = 70;
                long PROX_ALERT_EXPIRATION = -1;
                double latitude = -33.013234;
                double longitude = -71.541238;
        locationManager.addProximityAlert(
                latitude, // the latitude of the central point of the alert region
                longitude, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );
        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityIntentReceiver(), filter);
    }
    @SuppressLint("MissingPermission")
    private void addProximityCarteleraAlert() {
        Intent intent = new Intent(PROX_CARTELERA_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //set data proximity coordenates
        //PALACIO RIOJA
        long POINT_RADIUS = 60; // in Meters
        long PROX_ALERT_EXPIRATION = -1;
        double latitude = -33.0206282;
        double longitude = -71.5465233;

        locationManager2.addProximityAlert(
                latitude, // the latitude of the central point of the alert region
                longitude, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );
        IntentFilter filter = new IntentFilter(PROX_CARTELERA_ALERT_INTENT);
        registerReceiver(new ProximityCarteleraReceiver(), filter);
    }


}
