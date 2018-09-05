package com.example.felix.dailybucketlist.Goals;

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

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
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

        /*week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        year = Calendar.getInstance().get(Calendar.YEAR);
        txt_calendar_week.setText(placeholder + week);*/

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int calendarYear, int calendarMonth, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendarYear, calendarMonth, dayOfMonth);
                week = calendar.get(Calendar.WEEK_OF_YEAR);
                year = calendar.get(Calendar.YEAR);
                txt_calendar_week.setText(placeholder + week);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weekExists()) {
                    Toast.makeText(GoalSearchActivity.this, "Ziel existiert", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GoalSearchActivity.this, "Ziel existiert nicht", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUI() {
        btn_search = (Button) findViewById(R.id.btn_search_week);
        txt_calendar_week = (TextView) findViewById(R.id.calendar_week);
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
    }

    private boolean weekExists() {
        //es wird überprüft, ob überhaupt ein Goal in der Datenbank existiert, das in der ausgewählten Kalendarwoche bzw. Jahr erstellt wurde
        for(Goal goal: goals) {
            int goalWeek = goal.getDate().get(Calendar.WEEK_OF_YEAR);
            int goalYear = goal.getDate().get(Calendar.YEAR);

            if(goalYear < year || goalYear > year) {
                return false;
            }
            if(goalWeek <= week) {
                return true;
            }
        }
        return false;
    }

}
