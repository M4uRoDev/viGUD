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
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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
            bigText.bigText("Aproximaciones a la literatura a través de grandes escritores, a cargo del profesor Adelmo Yori, el cual analizará en conjunto a los participantes \"Voces femeninas chilenas del siglo XX: de Gabriela Mistral a Teresa Calderón\".");
            bigText.setSummaryText("Evento en Palacio Rioja");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.notification_flat);
            builder.setBadgeIconType(R.drawable.notification_flat);
            builder.setContentIntent(pendingIntent);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            builder.setAutoCancel(true);
            builder.setVibrate(new long[] {1000,1000,1000,1000});
            builder.setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.notification_flat));
            builder.setContentTitle("Lecturas de Palacio");
            builder.setStyle(bigText);
            notificationManager.notify(NOTIFICATION_ID,builder.build());
        }else if (eventID == 2){
            Intent intents = new Intent(context, EventActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intents,0);

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.notification_flat);
            builder.setBadgeIconType(R.drawable.notification_flat);
            builder.setContentIntent(pendingIntent);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            builder.setAutoCancel(true);
            builder.setVibrate(new long[] {1000,1000,1000,1000});
            builder.setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.notification_flat));
            builder.setContentTitle("Nueva Cartelera de Palacio Rioja");
            builder.setContentText("Estás cerca del palacio, puedes actualizar la cartelera de eventos.");
            builder.addAction(new NotificationCompat.Action(
                    R.drawable.ic_done_black_24dp,
                    "Si, actualizar",
                    PendingIntent.getActivity(context, 0, intents, PendingIntent.FLAG_UPDATE_CURRENT)));
            builder.addAction(new NotificationCompat.Action(
                    R.drawable.ic_cancel_black_24dp,
                    "No actualizar",
                    PendingIntent.getActivity(context, 0, intents, PendingIntent.FLAG_UPDATE_CURRENT)));
            notificationManager.notify(NOTIFICATION_ID,builder.build());
            proximityListener pL = new proximityListener();
            pL.setBoo(true);
        }
    }


}
