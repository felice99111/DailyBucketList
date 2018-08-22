package com.example.felix.dailybucketlist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.felix.dailybucketlist.Goals.Goal;

import java.util.List;

public class BucketListAdapter extends ArrayAdapter<Goal>{


    // Test
    private void test() {
    }

    //Nimm dir ÜB07 TableItemAdapter als "Vorbild". Die @NonNull Querys kannst du so stehen lassen, die kommen von der Datenbank


    public BucketListAdapter(@NonNull Context context, int resource, @NonNull List<Goal> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
