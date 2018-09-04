package com.example.felix.dailybucketlist.Main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.Adapter.BucketListCustomAdapter;
import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.R;

import java.util.ArrayList;

// Fragement zeigt die aktuellen Aufgaben der Woche an
public class BucketListFragment extends Fragment {

    ArrayList<String> goalIds;
    ArrayList<Goal> goalsForWeek;
    ListView bucketListView;
    BucketListCustomAdapter adapter;
    int week;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view;
        view = inflater.inflate(R.layout.bucketlist_fragment, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        goalsForWeek = new ArrayList<Goal>();

        // Hole alle Aufgaben der aktuellen Woche basierend auf den goalIds
        Bundle bundle = getArguments();
        week = bundle.getInt("week");
        goalIds = bundle.getStringArrayList("goalIds");

        for(String goalId : goalIds){
            goalsForWeek.add(BucketListDatabase.getInstance(getContext()).readGoal(Long.parseLong(goalId)));
        }

        // Setze Kalenderwoche
        TextView tv_week = getView().findViewById(R.id.textView_week);
        tv_week.setText("KW " + Integer.toString(week));

        initListView();

    }

    private void initListView() {

        // Übergebe alle Aufgaben der Woche an den Adapter zur Listenanzeige
        bucketListView = getView().findViewById(R.id.bucketListView);
        adapter = new BucketListCustomAdapter(getContext(), goalsForWeek);
        bucketListView.setAdapter(adapter);

        // Wenn keine Aufgabe -> zeige Ersatztext
        if(goalsForWeek.isEmpty()){
            bucketListView.addHeaderView(((LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE)).inflate(R.layout.bucketlist_heading, null));
        }
    }
}
