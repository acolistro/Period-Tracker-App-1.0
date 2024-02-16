package com.example.periodtracker.pill_reminder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import com.example.periodtracker.R;

import java.util.Calendar;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    NotificationManager manager;
    Notification myNotication2;





//    @SuppressLint({"WrongConstant", "NotificationPermission"})
//    public void onReceive(Context context, Intent intent) {
//        int parseInt = Integer.parseInt(intent.getStringExtra(ReminderEditActivity.EXTRA_REMINDER_ID));
//        String title = new ReminderDatabase(context).getReminder(parseInt).getTitle();
//        Intent intent2 = new Intent(context, ReminderEditActivity.class);
//        intent2.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(parseInt));
//        NotificationCompat.Builder onlyAlertOnce = new NotificationCompat.Builder(context)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.pill_reminder_icon)).setSmallIcon(R.drawable.ic_alarm_on_white_24dp).setContentTitle("It's time to take your Medicion…").setTicker(title).setVibrate(new long[]{0, 500, 1000}).setContentText(title).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(context, parseInt, intent2, 134217728)).setAutoCancel(true).setOnlyAlertOnce(true);
//        this.manager = (NotificationManager) context.getSystemService("notification");
//        if (Build.VERSION.SDK_INT >= 26) {
//            NotificationChannel notificationChannel = new NotificationChannel("Channel_id", "Notification", 4);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setLightColor(-16776961);
//            notificationChannel.enableLights(true);
//            notificationChannel.setShowBadge(true);
//            this.manager.createNotificationChannel(notificationChannel);
//        }
//        if (Build.VERSION.SDK_INT >= 16) {
//            this.myNotication2 = onlyAlertOnce.build();
//        } else {
//            this.myNotication2 = onlyAlertOnce.getNotification();
//        }
//        this.manager = (NotificationManager) context.getSystemService("notification");
//        if (Build.VERSION.SDK_INT >= 26) {
//            onlyAlertOnce.setChannelId("Channel_id");
//        }
//        this.manager.notify("Channel_id", parseInt, onlyAlertOnce.build());
//    }

    @SuppressLint("WrongConstant")
    public void setAlarm(Context context, Calendar calendar, int i) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(i));
        this.mPendingIntent = PendingIntent.getBroadcast(context, i, intent, 268435456 | PendingIntent.FLAG_IMMUTABLE);
        this.mAlarmManager.set(3, SystemClock.elapsedRealtime() + (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()), this.mPendingIntent);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootReceiver.class), 1, 1);
    }

    @SuppressLint("WrongConstant")
    public void setRepeatAlarm(Context context, Calendar calendar, int i, long j) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(i));
        this.mPendingIntent = PendingIntent.getBroadcast(context, i, intent, 268435456 | PendingIntent.FLAG_IMMUTABLE);
        long j2 = j;
        this.mAlarmManager.setRepeating(3, SystemClock.elapsedRealtime() + (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()), j2, this.mPendingIntent);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootReceiver.class), 1, 1);
    }

    @SuppressLint("WrongConstant")
    public void cancelAlarm(Context context, int i) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, AlarmReceiver.class), PendingIntent.FLAG_IMMUTABLE);
        this.mPendingIntent = broadcast;
        this.mAlarmManager.cancel(broadcast);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootReceiver.class), 2, 1);
    }

    //done
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("@@@@@@@@@@", "onReceive: ");

            int reminderId = Integer.parseInt(intent.getStringExtra(ReminderEditActivity.EXTRA_REMINDER_ID));
            String title = new ReminderDatabase(context).getReminder(reminderId).getTitle();

            Intent notificationIntent = new Intent(context, ReminderEditActivity.class);
            notificationIntent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(reminderId));

            PendingIntent contentIntent = PendingIntent.getActivity(
                    context,
                    reminderId,
                    notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "Channel_id")
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.pill_reminder_icon))
                    .setSmallIcon(R.drawable.ic_alarm_on_white_24dp)
                    .setContentTitle("It's time to take your Medication…")
                    .setTicker(title)
                    .setVibrate(new long[]{0, 500, 1000})
                    .setContentText(title)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d("@@@@@@@@@@", "onReceive:    1 ");

                NotificationChannel notificationChannel = new NotificationChannel("Channel_id", "Notification", NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableVibration(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableLights(true);
                notificationChannel.setShowBadge(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        Log.d("@@@@@@@@@@", "onReceive:    2 ");

            Notification notification = notificationBuilder.build();
            notificationManager.notify("Channel_id", reminderId, notification);


    }
}
