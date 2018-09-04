package com.example.felix.dailybucketlist.Preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.felix.dailybucketlist.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
