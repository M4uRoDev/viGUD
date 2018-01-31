package com.example.estudiante.vigud;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Estudiante on 15/01/2018.
 */

public class ProximityIntentReceiver extends BroadcastReceiver {

    public static final String EVENT_ID_INTENT_EXTRA = "EventIDIntentExtraKey";

    private static final int NOTIFICATION_ID = 1000;
    @Override
    public void onReceive(Context context, Intent intent) {

        long eventID = intent.getLongExtra(EVENT_ID_INTENT_EXTRA, -1);
        Log.v("gauntface","Proximity Alert Intent Received, eventID = "+eventID);

        if(eventID == 1){

            Intent intents = new Intent(context, EventActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intents,0);

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("El destacado escritor chileno Pablo Simonetti presentará en el Centro Cultural Palacio Rioja en Viña del Mar su última novela titulada \"Desastres naturales\"...");
            bigText.setSummaryText("Pablo Simenotti");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.notification_flat);
            builder.setBadgeIconType(R.drawable.notification_flat);
            builder.setContentIntent(pendingIntent);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            builder.setAutoCancel(true);
            builder.setGroup("evento");
            builder.setGroupSummary(true);
            builder.setVibrate(new long[] {1000,1000,1000,1000});
            builder.setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.notification_flat));
            builder.setContentTitle("Palacio Rioja");
            builder.setStyle(bigText);
            notificationManager.notify(NOTIFICATION_ID,builder.build());
        }else if (eventID == 2){
            String key = LocationManager.KEY_PROXIMITY_ENTERING;

            Boolean entering = intent.getBooleanExtra(key, false);

            if (entering) {
                Intent intents = new Intent(context, PuntoVigudActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intents,0);

                NotificationManager notificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setSmallIcon(R.drawable.notification_flat);
                builder.setBadgeIconType(R.drawable.notification_flat);
                builder.setContentIntent(pendingIntent);
                builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                builder.setAutoCancel(true);
                builder.setGroup("pointvigud");
                builder.setGroupSummary(true);
                builder.setVibrate(new long[] {1000,1000,1000,1000});
                builder.setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.notification_flat));
                builder.setContentTitle("Nueva Cartelera de Palacio Rioja");
                builder.setContentText("Estás cerca del palacio, puedes actualizar la cartelera de eventos.");
                builder.setColor(0xffF1991C);
                builder.setLights(0xffF1991C, 1000, 1000);
                notificationManager.notify(NOTIFICATION_ID,builder.build());
                Toast.makeText(context, "¡Estás cerca de un Punto Vigud!", Toast.LENGTH_LONG).show();
                MenuActivity.booleanVariable.setVariable(true);
            }
            else {
                Toast.makeText(context, "¡Te estás alejando del Punto Vigud!", Toast.LENGTH_LONG).show();
                MenuActivity.booleanVariable.setVariable(false);
            }


        }
    }


}
