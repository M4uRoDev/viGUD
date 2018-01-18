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
 * Created by Estudiante on 15/01/2018.
 */

public class ProximityIntentReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1000;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intents = new Intent(context, EventActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intents,0);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_event_available);
        builder.setBadgeIconType(R.drawable.ic_event_available);
        builder.setContentIntent(pendingIntent);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setAutoCancel(true);
        builder.setVibrate(new long[] {1000,1000,1000,1000});
        builder.setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.ic_launcher));
        builder.setTicker("Hay un evento cerca!");
        builder.setContentTitle("Programa de Talleres de Verano 2018");
        builder.setSubText("Toque para mas informacion");

        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }
}
