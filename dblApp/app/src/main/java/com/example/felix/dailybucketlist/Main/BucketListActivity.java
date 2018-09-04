package com.example.felix.dailybucketlist.Main;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.Preferences.SettingsActivity;
import com.example.felix.dailybucketlist.R;
import com.example.felix.dailybucketlist.Widget.BucketListAppWidget;

import java.util.Calendar;
import java.util.List;

// Main Activity der App.
// Listet alle aktiven Aufgaben aus der Datenbank auf.
public class BucketListActivity extends AppCompatActivity {

    List<Goal> allGoals;
    BucketListPagerAdapter bucketListPagerAdapter;

    private EditText inputAddNewGoal;
    private AlertDialog alertDialogAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucketlist);

        // Initialisiert View Pager für die Swipe Funktion.
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(0);

        // Holt alle Aufgaben aus der Datenbank und übergibt diese an Adapter.
        allGoals =  BucketListDatabase.getInstance(this).readAllGoals();
        bucketListPagerAdapter = new BucketListPagerAdapter(getSupportFragmentManager(), allGoals);
        viewPager.setAdapter(bucketListPagerAdapter);

        // Setzt Seite auf aktuelle Woche.
        Calendar calendar = Calendar.getInstance();
        viewPager.setCurrentItem(calendar.get(Calendar.WEEK_OF_YEAR) - 1);

        initActionBar();
    }

    private void refreshListView() {
        // Aktualisiert Aufgabenliste.
        allGoals.clear();
        allGoals.addAll(BucketListDatabase.getInstance(this).readAllGoals());

        // Aktualisiert Widget.
        Intent updateIntent = new Intent(this, BucketListAppWidget.class);
        updateIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), BucketListAppWidget.class));
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(updateIntent);

        // Aktualisiert die Seite bzw. lädt die MainActivity neu.
        Intent intent = new Intent(getApplicationContext(), BucketListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void initActionBar() {
        addNewGoal();
    }

    private void addNewGoal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Welches Ziel setzt du dir?");
        inputAddNewGoal = new EditText(this);
        builder.setView(inputAddNewGoal);

        builder.setPositiveButton("hinzufügen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String goalName = inputAddNewGoal.getText().toString();
                BucketListDatabase database = BucketListDatabase.getInstance(BucketListActivity.this); //Kontext des onClickListeners, deshalb würde nur this eine falsche Referenz übergeben
                database.createGoal(new Goal(goalName, Calendar.getInstance()));
                refreshListView();
            }
        });

        builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogAdd = builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Suchfunktion wird am Ende implementiert", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_add_goal:
                alertDialogAdd.show();
                inputAddNewGoal.setText("");
                return true;
            case R.id.action_delete:
                Toast.makeText(this, "Löschfunktion wird bald implementiert", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(BucketListActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
