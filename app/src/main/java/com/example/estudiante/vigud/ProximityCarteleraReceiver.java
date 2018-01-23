package com.example.estudiante.vigud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Estudiante on 22/01/2018.
 */

public class ProximityCarteleraReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1000;

    @Override
    public void onReceive(Context context, Intent intent) {

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


    }
}

