package com.example.felix.dailybucketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText inputName, inputReached;
    private Button createButton, clearAllButton, clearFirstButton, updateFirstButton;
    private List<Goal> goals;
    private ListView listView;
    private ArrayAdapter<Goal> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goals = BucketListDatabase.getInstance(this).readAllGoals();

        initUI();
    }

    private void initUI() {
        inputName = (EditText) findViewById(R.id.goal);
        inputReached = (EditText) findViewById(R.id.reached);
        createButton = (Button) findViewById(R.id.createButton);
        clearAllButton = (Button) findViewById(R.id.clearAllButton);
        clearFirstButton = (Button) findViewById(R.id.clearFirstButton);
        updateFirstButton = (Button) findViewById(R.id.updateFirstButton);
        initListView();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this); //Kontext des onClickListeners, deshalb würde nur this eine falsche Referenz übergeben
                database.createGoal(new Goal("Zimmer streichen", Calendar.getInstance()));
                database.createGoal(new Goal("Joggen"));

                refreshListView();
            }
        });

        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this);
                database.deleteAllGoals();
                refreshListView();
            }
        });

        clearFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this);

                if(goals.size() > 0 ) {
                    database.deleteGoal(goals.get(0));
                }
                refreshListView();
            }
        });

        updateFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goals.size() > 0) {
                    BucketListDatabase database = BucketListDatabase.getInstance(MainActivity.this);
                    Random r = new Random();
                    goals.get(0).setName(String.valueOf(r.nextInt()));
                    database.updateGoal(goals.get(0));
                    refreshListView();
                }
            }
        });
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.bucketListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, goals);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object element = parent.getAdapter().getItem(position);
            }
        });
    }

    private void refreshListView() {
        goals.clear();
        goals.addAll(BucketListDatabase.getInstance(this).readAllGoals());
        adapter.notifyDataSetChanged();
    }


}
