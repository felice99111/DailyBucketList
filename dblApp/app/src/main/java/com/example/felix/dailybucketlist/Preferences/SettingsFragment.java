package com.example.felix.dailybucketlist.Preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.felix.dailybucketlist.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Lädt die Preferences aus der xml-Datei
        addPreferencesFromResource(R.xml.preferences);
    }
}
