package com.example.project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class ScreenReceiver extends BroadcastReceiver {
    public static final String TAG_NOTIFICATION = "NOTIFICATION_MESSAGE";
    public static final String CHANNEL_ID = "channel_1111";
    public static final int NOTIFICATION_ID = 111111;


    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT) && Manager.isSpacedRepetition && Manager.getSpacedModule() != null && Manager.getSpacedModule().getCards().size() != 0) {


            Log.d("", " The screen turned on!");
            MainActivity.mainActivity.startActivity(new Intent(MainActivity.mainActivity, OtherActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));

        }
    }
}
