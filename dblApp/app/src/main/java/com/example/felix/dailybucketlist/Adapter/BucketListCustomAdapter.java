package com.example.felix.dailybucketlist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.Goals.GoalActivity;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

// Adapter zur Anzeige der Aufgaben in der BucketListActivity
public class BucketListCustomAdapter extends ArrayAdapter<Goal> {
    public BucketListCustomAdapter(@NonNull Context context, ArrayList<Goal> resource) {
        super(context, R.layout.bucketlist_item, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.bucketlist_item, parent,false);

        // Setze alle view-Werte jeder Aufgabe
        final Goal goalItem = getItem(position);
        TextView tv_Name = customView.findViewById(R.id.textView_Name);
        TextView tv_Day= customView.findViewById(R.id.textView_Day);
        ImageView iv_check = customView.findViewById(R.id.imageView_check);

        tv_Name.setText(goalItem.getName());

        // Zeige Haken wenn Aufgabe erfüllt
        if(goalItem.isReached()){
            iv_check.setVisibility(View.VISIBLE);
        }

        // Hole Wochentag
        String weekdays[] = new DateFormatSymbols(Locale.GERMAN).getWeekdays();
        int day = goalItem.getDate().get(Calendar.DAY_OF_WEEK);
        String dayShort = weekdays[day].substring(0, Math.min(weekdays[day].length(), 2));
        tv_Day.setText(dayShort);

        // OnClick Funktion für jede Aufgabe, leitet auf Detailseite weiter
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoalActivity.class);
                intent.putExtra("goalId", goalItem.getId());
                getContext().startActivity(intent);
            }
        });

        return customView;
    }
}
