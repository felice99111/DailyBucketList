package com.example.felix.dailybucketlist.TabbingTutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.felix.dailybucketlist.R;

// Aktivity zeigt 3-seitiges Tutorial mit Swipe Navigation an
public class TabbingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbing);

        // Initialisiere Viewer Pager und Adapter für die Tutorial Swipe Funktion
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
    }
}
