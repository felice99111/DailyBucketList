package com.example.felix.dailybucketlist.TabbingTutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.felix.dailybucketlist.AlarmManager.AlarmActivity;
import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.R;

// Activity zeigt 3-seitiges Tutorial mit Swipe Navigation an.
public class TabbingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Die sobald onCreate in der BucketListActivity aufgerufen wird, wird die statische Variable "isRunning" auf true gesetzt.
        //Das Tutorial soll nur beim aller ersten Start oder über Aufruf in den Einstellungen angezeigt werden (für letzteren Fall läuft die BucketListActivity bereits).
        //Wurde das Tutorial einmal beendet, wird es via SharedPreferences vermerkt. So wird bei jedem Start überprüft, ob die Activity schon mal durchlaufen wurde.

        if(!BucketListActivity.isRuning) {
            if (checkTutorialPassed()) {
                startActivityForResult(new Intent(this, AlarmActivity.class), 1);
            }
        }

        setContentView(R.layout.activity_tabbing);

        // Initialisiert ViewPager und Adapter für die Tutorial Swipe Funktion.
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
    }

    private boolean checkTutorialPassed() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Config.TUTORIAL_PREFERENCES, 0);
        boolean tutorialPassed = settings.getBoolean(Config.TUTORIAL_PREFERENCES_KEY, false);
        return tutorialPassed;
    }

}
