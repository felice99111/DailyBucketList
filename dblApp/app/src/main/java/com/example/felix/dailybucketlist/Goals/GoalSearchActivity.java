package com.example.felix.dailybucketlist.Goals;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.R;

import java.util.Calendar;
import java.util.List;

public class GoalSearchActivity extends AppCompatActivity {

    private Button btn_search;
    private TextView txt_calendar_week;
    private CalendarView calendarView;

    private List<Goal> goals;
    private int week;
    private int year;
    private String placeholder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_search);

        placeholder = this.getResources().getString(R.string.calendar_week_text);
        goals = BucketListDatabase.getInstance(this).readAllGoals();

        initUI();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int calendarYear, int calendarMonth, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendarYear, calendarMonth, dayOfMonth);
                //die Klassevariablen week und year werden mit jedem Klick auf ein Datum im Kalender aktualisiert
                week = calendar.get(Calendar.WEEK_OF_YEAR);
                year = calendar.get(Calendar.YEAR);
                txt_calendar_week.setText(placeholder + week);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Die ausgewählte Woche wird nur dann angezeigt, wenn in dieser ein Ziel eingetragen wurde.
                if(weekExists()) {
                    showChoosenWeek();
                } else {
                    Toast.makeText(GoalSearchActivity.this, GoalSearchActivity.this.getResources().getString(R.string.toast_goal_not_existing), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showChoosenWeek() {
        //existiert in der ausgewählten Woche ein Ziel, so werden die benötigten Daten Woche und Jahr an die Main Activity übergeben, um die gegebene
        //Woche anzuzeigen. Über den "SEARCH_INTENT_ACTIVITY_KEY" erhält die Main Activity die Information, aus welcher Activity aus "onResume()"
        //aufgerufen wurde.
        Intent intent = new Intent(this, BucketListActivity.class);
        intent.putExtra(Config.SEARCH_INTENT_WEEK_KEY, week);
        intent.putExtra(Config.SEARCH_INTENT_YEAR_KEY, year);
        intent.putExtra(Config.SEARCH_INTENT_ACTIVITY_KEY, Config.SEARCH_INTENT_ACTIVITY_VALUE);
        startActivity(intent);
    }

    private void initUI() {
        btn_search = (Button) findViewById(R.id.btn_search_week);
        txt_calendar_week = (TextView) findViewById(R.id.calendar_week);
        calendarView = (CalendarView) findViewById(R.id.calendar_view);

        //Die aktuelle Kalendarwoche wird beim Start der Activity angezeigt. Die Klassenvariablen week und year werden bei jedem "klick" auf den Kalendar
        //überschrieben.
        week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        year = Calendar.getInstance().get(Calendar.YEAR);
        txt_calendar_week.setText(placeholder + week);
    }

    private boolean weekExists() {
        //es wird überprüft, ob überhaupt ein Goal in der Datenbank existiert, das in der ausgewählten Kalendarwoche bzw. Jahr erstellt wurde
        for(Goal goal: goals) {
            if(goal.getDate() != null) {
                int goalWeek = goal.getDate().get(Calendar.WEEK_OF_YEAR);
                int goalYear = goal.getDate().get(Calendar.YEAR);
                if (goalWeek == week && goalYear == year) {
                    return true;
                }
            }
        }
        return false;
    }

}
