package com.example.felix.dailybucketlist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.felix.dailybucketlist.Adapter.BucketListCustomAdapter;
import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Goal> allGoals, goals_upcoming, goals_overdue;
    private ListView listView1, listView2;
    private ArrayAdapter<Goal> adapter_upcoming, adapter_overdue;

    //ADDING NEW GOAL: Dialog and EditText Object
    private EditText inputAddNewGoal;
    private AlertDialog alertDialogAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allGoals = BucketListDatabase.getInstance(this).readAllGoals();
        goals_upcoming = new ArrayList<Goal>();
        goals_overdue = new ArrayList<Goal>();
        for (Goal goal : allGoals) {
            Date currentTime = Calendar.getInstance().getTime();
            if (currentTime.after(goal.getDate().getTime())) {
                goals_overdue.add(goal);
            }
            else{
                goals_upcoming.add(goal);
            }
        }

        initUI();
        initActionBar();

    }

    private void initUI() {
        initListView();
    }

    private void initListView() {
        listView1 = (ListView) findViewById(R.id.bucketListView_overdue);
        listView2 = (ListView) findViewById(R.id.bucketListView_upcoming);
        listView1.addHeaderView(((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.table_heading_overdue, null));
        listView2.addHeaderView(((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.table_heading_upcoming, null));


        listView1.setAdapter(adapter_overdue);
        listView2.setAdapter(adapter_upcoming);
    }

    private void refreshListView() {
        allGoals.clear();
        allGoals.addAll(BucketListDatabase.getInstance(this).readAllGoals());
        adapter_upcoming.notifyDataSetChanged();
        adapter_overdue.notifyDataSetChanged();
    }


    //Implementieren der Action Bar:

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
                BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this); //Kontext des onClickListeners, deshalb würde nur this eine falsche Referenz übergeben
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
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}





/** Methode, um neuen Eintrag in die Datenbank zu erstellen
 BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this); //Kontext des onClickListeners, deshalb würde nur this eine falsche Referenz übergeben
 database.createGoal(new Goal("Zimmer streichen", Calendar.getInstance()));
 database.createGoal(new Goal("Joggen"));

 refreshListView(); **/

/** Methode, um alle Einträge zu löschen
 BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this);
 database.deleteAllGoals();
 refreshListView(); **/

/** Methode, um obersten Eintrag der Database zu löschen
 BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this);

 if(goals.size() > 0 ) {
 database.deleteGoal(goals.get(0));
 }
 refreshListView(); **/

/** Methode, um ersten Datenbankeintrag zu upgraden (mit random nummer zu versehen): updatet wirklich nur das erste?
 if(goals.size() > 0) {
 BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this);
 Random r = new Random();
 goals.get(0).setName(String.valueOf(r.nextInt()));
 database.updateGoal(goals.get(0));
 refreshListView(); **/