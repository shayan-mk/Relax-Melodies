package com.example.relaxmelodies.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        switch (key) {
            case "inhale_duratoin":
            case "exhale_duration":
            case "hold_duration":
                Preference preference = findPreference(key);
                preference.setSummary(sharedPreferences.getInt(key, 3000));
                break;
            case "night_mode":
                ((MainActivity)getActivity()).setNightMode();
                break;
            case "truncate":
                ((MainActivity)getActivity()).truncateSavedMixes();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}