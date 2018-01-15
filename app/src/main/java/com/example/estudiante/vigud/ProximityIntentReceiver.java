package com.example.estudiante.vigud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Estudiante on 15/01/2018.
 */

public class ProximityIntentReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1000;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_event_available);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setVibrate(new long[] {1000,1000,1000,1000});
        builder.setTicker("Hay un evento cerca!");
        builder.setContentTitle("Programa de Talleres de Verano 2018\n");
        builder.setSubText("Toque para mas informacion");

        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }
}
