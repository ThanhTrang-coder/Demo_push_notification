package com.example.demonotification;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

public class CreateNotificationChannel extends Application {
    public static final String CHANNEL_ID = "CHANNEL_1";
    public static final String CHANNEL_ID_2 = "CHANNEL_2";
    public static final String CHANNEL_MUSIC = "CHANNEL_MUSIC";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri sound1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification);
            Uri sound2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pika);
            Uri sound3 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gunshot_notification);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            //Config channel 1
            CharSequence channelName = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(description);
            channel.setSound(sound1, audioAttributes);

            //Config channel 2
            CharSequence channelName_2 = getString(R.string.channel_name_2);
            String description_2 = getString(R.string.channel_description_2);
            int importance_2 = NotificationManager.IMPORTANCE_MAX;
            @SuppressLint("WrongConstant")
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, channelName_2, importance_2);
            channel.setDescription(description_2);
            channel_2.setSound(sound2, audioAttributes);

            //Config channel music
            NotificationChannel channelMusic = new NotificationChannel(CHANNEL_MUSIC, "Channel music", NotificationManager.IMPORTANCE_HIGH);
            channelMusic.setDescription("This is channel music");
            channelMusic.setSound(sound3, audioAttributes);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            manager.createNotificationChannel(channel_2);
            manager.createNotificationChannel(channelMusic);
        }
    }
}
