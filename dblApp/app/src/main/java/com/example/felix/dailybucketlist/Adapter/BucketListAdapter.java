package com.example.felix.dailybucketlist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.felix.dailybucketlist.Goals.Goal;

import java.util.List;

public class BucketListAdapter extends ArrayAdapter<Goal>{

    //Nimm dir ÜB07 TableItemAdapter als "Vorbild". Die @NonNull Querys kannst du so stehen lassen, die kommen von der Datenbank

    private List<Goal> goals;
    private Context context;
    private int resource;


    public BucketListAdapter(@NonNull Context context, int resource, @NonNull List<Goal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        goals = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, null);
        }
/*
        TextView monday = (TextView) v.findViewById(android.R.id.monday);
        TextView tuesday = (TextView) v.findViewById(android.R.id.tuesday);
        TextView wednesday = (TextView) v.findViewById(android.R.id.wednesday);
        TextView thursday = (TextView) v.findViewById(android.R.id.thursday);
        TextView friday = (TextView) v.findViewById(android.R.id.friday);

        Goal item = goals.get(position);
        v.setBackgroundResource(getColorForRank(item.getRank()));
        rank.setText(String.valueOf(item.getRank()));
        team.setText(item.getTeam());
        playedGames.setText(String.valueOf(item.getPlayedGames()));
        points.setText(String.valueOf(item.getPoints()));
        goalDifference.setText(item.getGoals() + ":" + item.getGoalsAgainst()); */

        return v;

    }
}
