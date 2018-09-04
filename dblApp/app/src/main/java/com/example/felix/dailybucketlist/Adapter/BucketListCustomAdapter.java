package com.example.felix.dailybucketlist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.Goals.GoalActivity;
import com.example.felix.dailybucketlist.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

// Adapter zur Anzeige der Aufgaben in der BucketListActivity.
public class BucketListCustomAdapter extends ArrayAdapter<Goal> {
    public BucketListCustomAdapter(@NonNull Context context, ArrayList<Goal> resource) {
        super(context, R.layout.bucketlist_item, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.bucketlist_item, parent,false);

        // Setzt alle view-Werte jeder Aufgabe.
        final Goal goalItem = getItem(position);
        TextView name = customView.findViewById(R.id.textView_Name);
        TextView day= customView.findViewById(R.id.textView_Day);
        ImageView check = customView.findViewById(R.id.imageView_check);

        name.setText(goalItem.getName());

        // Zeigt Haken, wenn Aufgabe erfüllt.
        if(goalItem.isReached()){
            check.setVisibility(View.VISIBLE);
        }

        // Holt Wochentag.
        String weekdays[] = new DateFormatSymbols(Locale.GERMAN).getWeekdays();
        int dayNum = goalItem.getDate().get(Calendar.DAY_OF_WEEK);
        String dayShort = weekdays[dayNum].substring(0, Math.min(weekdays[dayNum].length(), 2));
        day.setText(dayShort);

        // OnClick Funktion für jede Aufgabe, leitet auf Detailseite weiter.
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
