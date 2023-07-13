package com.example.demonotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Notification notification;
    Button btnSendNotificationChannel1, btnSendNotificationChannel2,
            btnCustomNotification, btnMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendNotificationChannel1 = findViewById(R.id.btn_send_notification);
        btnSendNotificationChannel2 = findViewById(R.id.btn_send_notification_2);
        btnCustomNotification = findViewById(R.id.btn_custom_notification);
        btnMedia = findViewById(R.id.btn_notification_mediaStyle);

        btnSendNotificationChannel1.setOnClickListener(view -> sendNotificationChannel1());
        btnSendNotificationChannel2.setOnClickListener(view -> sendNotificationChannel2());
        btnCustomNotification.setOnClickListener(view -> sendCustomNotification());
        btnMedia.setOnClickListener(view -> displayMediaStyle());
    }

    //create notification channel 1
    private void sendNotificationChannel1() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_avatar);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification);

        Intent notifyIntent = new Intent(this, VnExpressActivity.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, getNotificationId(), notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        notification = new NotificationCompat.Builder(this, CreateNotificationChannel.CHANNEL_ID)
                .setContentTitle(getString(R.string.title_notification))
                .setContentText(getString(R.string.content_notification))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.content_notification)))
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setSound(uri)
                .setContentIntent(notifyPendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(getNotificationId(), notification);
    }

    //create notification channel 2
    private void sendNotificationChannel2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.big_picture);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pikachu_notification);

        notification = new NotificationCompat.Builder(this, CreateNotificationChannel.CHANNEL_ID_2)
                .setContentTitle("First Notification from Channel 2")
                .setContentText("This is the Body of message")
                .setSmallIcon(R.drawable.ic_facebook)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setSound(uri)
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(getNotificationId(), notification);
    }

    //create custom notification
    private void sendCustomNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_youtube);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pikachu_notification);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String date = dateFormat.format(new Date());

        // Collapsed
        RemoteViews collapsedNotifyLayout = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_collapsed);
        collapsedNotifyLayout.setTextViewText(R.id.tv_title_custom_collapsed, "Collapsed notification");
        collapsedNotifyLayout.setTextViewText(R.id.tv_message_custom_collapsed, "This is the message of collapsed notification");
        collapsedNotifyLayout.setTextViewText(R.id.tv_time_custom_collapsed, date);
        collapsedNotifyLayout.setImageViewBitmap(R.id.imv_custom_collapsed, bitmap);

        //Expanded
        RemoteViews expandedNotifyLayout = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_expanded);
        expandedNotifyLayout.setTextViewText(R.id.tv_title_custom_expanded, "Expanded notification");
        expandedNotifyLayout.setTextViewText(R.id.tv_message_custom_expanded, "This is the message of expanded notification");
        expandedNotifyLayout.setImageViewResource(R.id.img_custom_expanded, R.drawable.big_picture);

        notification = new NotificationCompat.Builder(this, CreateNotificationChannel.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.ic_youtube)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setSound(uri)
                .setCustomContentView(collapsedNotifyLayout)
                .setCustomBigContentView(expandedNotifyLayout)
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(getNotificationId(), notification);
    }

    //create notification media style
    private void displayMediaStyle() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_media_style);
        Uri sound3 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gunshot_notification);

        notification = new NotificationCompat.Builder(this, CreateNotificationChannel.CHANNEL_MUSIC)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_audio_track)
                .setSubText("T.T")
                .setContentTitle("A thousand year")
                .setContentText("Christina Perri")
                .setLargeIcon(bitmap)
                //Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_thumb_down, "Thumb_down", null)
                .addAction(R.drawable.ic_skip_previous, "Previous", null)
                .addAction(R.drawable.ic_pause, "Pause", null)
                .addAction(R.drawable.ic_skip_next, "Next", null)
                .addAction(R.drawable.ic_thumb_up, "Thumb_up", null)
                //Apply the media style
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3))
                .setSound(sound3)
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(getNotificationId(), notification);

    }
    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}