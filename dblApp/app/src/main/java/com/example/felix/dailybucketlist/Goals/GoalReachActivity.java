package com.example.felix.dailybucketlist.Goals;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.R;

import java.util.Calendar;

// Activity zeigt detailierte Ansicht einer Aufgabe an
public class GoalReachActivity extends AppCompatActivity {

    Goal goal;
    private AlertDialog alertDialogDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goal_reach);

        // Setze alle Werte der Aufgabe
        TextView tv_name = (TextView)findViewById(R.id.textView_goal_name);
        TextView tv_date = (TextView)findViewById(R.id.textView_goal_date);
        TextView tv_reach = (TextView)findViewById(R.id.textView_reach);
        ImageView iv_delete = (ImageView)findViewById(R.id.imageView_delete);
        Button btn_reach = (Button)findViewById(R.id.btn_reached);

        final long goalId = getIntent().getLongExtra("goalId", 0);
        goal = BucketListDatabase.getInstance(this).readGoal(goalId);

        // Wenn Aufgabe erfüllt zeige Text
        if(goal.isReached()){
            btn_reach.setVisibility(View.INVISIBLE);
            tv_reach.setText("Aufgabe erfüllt");
            tv_reach.setTextColor(Color.GREEN);
        }

        tv_name.setText(goal.getName());
        tv_date.setText(goal.getDateInText());

        // OnClick Funktion für erfüllt-Markierung
        btn_reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal.setReached(true);
                BucketListDatabase.getInstance(GoalReachActivity.this).updateGoal(goal);
                Intent intent = new Intent(getApplicationContext(), BucketListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // OnClick Funktion für Aufgabe löschen
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDelete();
            }
        });
    }

    private void requestDelete() {
        //Der User könnte aus Versehen auf den Lösch Button getippt haben, deshalb wird ein AlertDialog gezeigt, um den Löschvorgang zu hinterfragen.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Möchtest du das Ziel wirklich löschen?");

        builder.setPositiveButton("löschen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Stimmt der User zu, wird das Ziel aus der Datenbank gelöscht und die App zeigt die aktualisierte MainActivity an
                BucketListDatabase.getInstance(GoalReachActivity.this).deleteGoal(goal);
                Intent intent = new Intent(getApplicationContext(), BucketListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogDelete = builder.create();
        alertDialogDelete.show();
    }

}
