package com.example.felix.dailybucketlist.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.TabbingTutorial.FragmentPage;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// Adapter gibt Fragment dür die Wochenansicht der Aufgaben zurück
// Holt alle Aufgaben der Woche aus der Datenbank
public class BucketListPagerAdapter extends FragmentStatePagerAdapter {

    private List<Goal> allGoals;
    ArrayList<String> goalIds;
    BucketListFragment bucketListFragment;

    public BucketListPagerAdapter(FragmentManager fm, List<Goal>allGoals) {
        super(fm);
        this.allGoals = allGoals;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        goalIds = new ArrayList<String>();

        // Hole alle Aufgaben der aktuellen Woche
        for (Goal goal : allGoals) {
            if (goal.getDate().get(Calendar.WEEK_OF_YEAR) == position + 1) {
                goalIds.add(Long.toString(goal.getId()));
            }
        }

        // Übergebe alle Aufgaben Ids der aktuellen Woche an das Fragment
        bundle.putStringArrayList("goalIds", goalIds);
        bundle.putInt("week", position + 1);

        bucketListFragment = new BucketListFragment();
        bucketListFragment.setArguments(bundle);

        return bucketListFragment;
    }

    @Override
    public int getCount() {
        return 52;
    }

}
