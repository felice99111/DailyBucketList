package com.example.felix.dailybucketlist.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// RemoteViewFactory zur Anzeige der Aufgaben der aktuellen Woche im Widget
public class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private ArrayList<Goal> goalList;

    public WidgetRemoteViewsFactory(Context applicationContext, Intent intent) {
        context = applicationContext;
    }

    @Override
    public void onCreate() {
        // Hole alle Aufgaben der Woche und speicher sie in der Liste
        List<Goal> allGoals = BucketListDatabase.getInstance(this.context).readAllGoals();
        int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        goalList = new ArrayList<Goal>();
        for(Goal goal : allGoals){
            if(goal.getDate().get(Calendar.WEEK_OF_YEAR) == currentWeek){
                goalList.add(goal);
            }
        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return goalList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // Setze RemoteView Text für alle Aufgaben
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.collection_widget_list_item);
        rv.setTextViewText(R.id.widgetItemTaskNameLabel, goalList.get(position).getName());

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}