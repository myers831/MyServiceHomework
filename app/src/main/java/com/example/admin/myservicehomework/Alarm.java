package com.example.admin.myservicehomework;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.system.ErrnoException;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 10/10/2017.
 */

public class Alarm extends BroadcastReceiver {

    boolean songChose = false;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, MusicPlayer.songName, Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle("Music Player")
                .setContentText(MusicPlayer.songName)
                .setSmallIcon(R.mipmap.ic_launcher);
        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, notification.build());
        songChose = true;

        Log.d("Check", "onReceive: ");
    }
}
