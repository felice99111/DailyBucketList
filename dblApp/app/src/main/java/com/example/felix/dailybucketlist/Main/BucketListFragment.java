package com.example.felix.dailybucketlist.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.Adapter.BucketListCustomAdapter;
import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Database.BucketListDatabase;
import com.example.felix.dailybucketlist.Goals.Goal;
import com.example.felix.dailybucketlist.R;

import java.util.ArrayList;

// Fragement zeigt die aktuellen Aufgaben der Woche an.
public class BucketListFragment extends Fragment {

    ArrayList<String> goalIds;
    ArrayList<Goal> goalsForWeek;
    ListView bucketListView;
    BucketListCustomAdapter adapter;
    int weekNum;
    int year;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bucketlist_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        goalsForWeek = new ArrayList<Goal>();

        // Holt alle Aufgaben der aktuellen Woche basierend auf den goalIds.
        Bundle bundle = getArguments();
        weekNum = bundle.getInt(Config.ADAPTER_INTENT_WEEK_KEY);
        year = bundle.getInt(Config.ADAPTER_INTENT_YEAR_KEY);
        goalIds = bundle.getStringArrayList(Config.ADAPTER_INTENT_IDS_KEY);

        for(String goalId : goalIds){
            goalsForWeek.add(BucketListDatabase.getInstance(getContext()).readGoal(Long.parseLong(goalId)));
        }

        // Setzt Kalenderwoche und Jahr.
        TextView weekTxt = getView().findViewById(R.id.textView_week);
        weekTxt.setText("KW " + Integer.toString(weekNum));
        TextView yearTxt = getView().findViewById(R.id.textView_year);
        yearTxt.setText(Integer.toString(year));

        initListView();

    }

    private void initListView() {

        // Übergibt alle Aufgaben der Woche an den Adapter zur Listenanzeige.
        bucketListView = getView().findViewById(R.id.bucketListView);
        adapter = new BucketListCustomAdapter(getContext(), goalsForWeek);
        bucketListView.setAdapter(adapter);

        // Wenn keine Aufgabe -> zeigt Ersatztext.
        if(goalsForWeek.isEmpty()){
            bucketListView.addHeaderView(((LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE)).inflate(R.layout.bucketlist_heading, null));
        }
    }


}
