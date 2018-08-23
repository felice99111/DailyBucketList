package com.example.felix.dailybucketlist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.MainActivity;

import java.util.List;

public class BucketListAdapter extends ArrayAdapter<Goal>{

    //über die List (sollte sich wie eine normale ArrayList verhalten) erhältst du Zugriff auf alle Goals in der Datenbank
    List <Goal> goalDB;
    private Context context;

    //Nimm dir ÜB07 TableItemAdapter als "Vorbild". Die @NonNull Querys kannst du so stehen lassen, die kommen von der Datenbank


    public BucketListAdapter(@NonNull Context context, int resource, @NonNull List<Goal> objects) {
        super(context, resource, objects);
        goalDB = BucketListDatabase.getInstance(context).readAllGoals();
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);

/*
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(android.R.layout.table_item, null);
        }

        TextView monday = (TextView) v.findViewById(android.R.id.monday);
        TextView tuesday = (TextView) v.findViewById(android.R.id.tuesday);
        TextView wednesday = (TextView) v.findViewById(android.R.id.wednesday);
        TextView thursday = (TextView) v.findViewById(android.R.id.thursday);
        TextView friday = (TextView) v.findViewById(android.R.id.friday);

        return v; */
    }
}
