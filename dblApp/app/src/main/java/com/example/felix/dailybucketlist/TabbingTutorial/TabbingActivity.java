package com.example.felix.dailybucketlist.TabbingTutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.felix.dailybucketlist.R;

// Activity zeigt 3-seitiges Tutorial mit Swipe Navigation an.
public class TabbingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbing);

        // Initialisiert ViewPager und Adapter für die Tutorial Swipe Funktion.
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
    }

}
