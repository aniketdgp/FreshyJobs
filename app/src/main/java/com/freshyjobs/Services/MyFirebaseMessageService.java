package com.freshyjobs.Services;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.freshyjobs.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }

    public void showNotification(String title,String message){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"jobs")
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.logo)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.logo)
                .setContentText(message);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999 ,builder.build());

    }


}
