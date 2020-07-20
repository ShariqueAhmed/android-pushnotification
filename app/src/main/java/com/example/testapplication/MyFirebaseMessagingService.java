package com.example.testapplication;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String fcmId) {
        super.onNewToken(fcmId);
        //This is fcmId,use this id to send notification to users.
        Log.d("FCM ID: ",fcmId);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> dataFromFCM = remoteMessage.getData();
        //This is data which we have received from the FCM.
        Log.d("Data: ",remoteMessage.toString());

        //Get data payload from FCM and show as Notification to users
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                //Set your own icon here
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
