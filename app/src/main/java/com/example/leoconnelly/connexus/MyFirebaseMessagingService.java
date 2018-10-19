package com.example.leoconnelly.connexus;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.helpshift.Core;

import java.util.Map;

/**
 * Created by rajvirsingh on 02/02/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
      //  Log.d(TAG, "From: " + remoteMessage.getFrom());
     //   Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        //Toast.makeText(this, remoteMessage.getNotification().getBody(), Toast.LENGTH_SHORT).show();
        Map<String, String> data = remoteMessage.getData();
        String origin = data.get("origin");
        if (origin != null && origin.equals("helpshift")) {
            Core.handlePush(this, data);
        }
        createNotifications(remoteMessage.getData().get("one"));
       // startActivity(new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("one",remoteMessage.getData().get("one")));
    }

    void createNotifications(String title) {
        int id = 786;
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // cancel old intent and create new one
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("one", title);
        intent.putExtra("notify", 100);
        PendingIntent pIntent = PendingIntent.getActivity(this, id, intent, flags);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.hs__cam_action_send_feedback)
                .setContentTitle("vipul's fcm")
                .setAutoCancel(true)
                .setContentIntent(pIntent)
                .setContentText(title)
                .setSound(uri)
                .setVibrate(new long[]{500, 2000});
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());

    }
}