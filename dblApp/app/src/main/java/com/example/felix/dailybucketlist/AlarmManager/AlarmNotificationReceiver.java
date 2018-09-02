package com.example.felix.dailybucketlist.AlarmManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.MainActivity;
import com.example.felix.dailybucketlist.R;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //click auf not führt nicht in die main activity!
        Intent newIntent = new Intent(context, BucketListActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, newIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Config.CHANNEL_ID);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("CONTENT TITLE")
                .setContentText("THIS IS MY ALARM")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setContentInfo("Info");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(Config.NOTIFICATION_ID, builder.build());

    }
}
