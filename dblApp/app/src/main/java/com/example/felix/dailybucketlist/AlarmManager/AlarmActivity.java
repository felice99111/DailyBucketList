package com.example.felix.dailybucketlist.AlarmManager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.MainActivity;
import com.example.felix.dailybucketlist.R;
import com.example.felix.dailybucketlist.TabbingTutorial.TabbingActivity_V2;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private Button buttonSetAlarm;
    private TimePicker timePicker;
    private Intent mainActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        createNotificationChannel();
        mainActivityIntent = new Intent(AlarmActivity.this, BucketListActivity.class);

        //Die AlarmActivity soll nur aufgerufen werden, wenn noch keine Uhrzeit für die Benachrichtigung gesetzt wurde.
        //D.h. beim ersten Start der App. Von da an wird die AlarmActivity umgangen und sofort die BucketListActivity aufgerufen.
        //Die Uhrzeit der Benachrichtigung kann jederzeit in den Settings geändert werden.

        /*if(checkAlarmSet()) {
            startActivity(mainActivityIntent);
        }*/

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        buttonSetAlarm = (Button) findViewById(R.id.buttonSetAlarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                if(Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                } else {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );
                }

                setAlarm(calendar.getTimeInMillis());
                saveAlarmSet();
                startActivity(mainActivityIntent);
            }
        });

    }

    private boolean checkAlarmSet() {
        //Über SharedPraferences und dem Key ALARM_PREFERENCES_KEY wird ausgelesen, welcher boolean "hinterlassen wurde"
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Config.ALARM_PREFERENCES, 0);
        boolean alarmSet = settings.getBoolean(Config.ALARM_PREFERENCES_KEY, false);
        Log.d("Alarm set: ", alarmSet ? "true" : "false");
        return alarmSet;
    }


    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //Setting alarmManager to repeat every day
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }

    private void saveAlarmSet() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Config.ALARM_PREFERENCES, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Config.ALARM_PREFERENCES_KEY, Config.ALARM_SET);
        editor.apply();
    }

    private void createNotificationChannel() {
        //Ab API 26 ist es notwendig, jeder Notification einen eigenen Channel hinzuzufügen
        //Bei älternen APIs wird dieser Channel einfach ignoriert (ist nicht in der Support Libary enthalten)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel name";
            String description = "channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Config.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
