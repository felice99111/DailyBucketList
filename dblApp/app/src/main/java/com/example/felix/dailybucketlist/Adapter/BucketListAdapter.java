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

    private List<Goal> table;
    private Context context;
    private int resource;

    public BucketListAdapter(@NonNull Context context, int resource, @NonNull List<Goal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        table = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);

    }
}
