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
        updateDurationSummary("inhale_duration");
        updateDurationSummary("exhale_duration");
        updateDurationSummary("hold_duration");

        findPreference("truncate").setOnPreferenceClickListener(preference -> {
            ((MainActivity)getActivity()).truncateSavedMixes();
            return true;
        });

        findPreference("restore_settings").setOnPreferenceClickListener(preference -> {
            getActivity().getSharedPreferences("AppSettingsPrefs", 0).edit().clear().apply();
            return true;
        });

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences appSettingsPrefs = getActivity().getSharedPreferences("AppSettingsPrefs", 0);
        String value = sharedPreferences.getString(key, "");
        appSettingsPrefs.edit().putString(key, value).apply();

        switch (key) {
            case "inhale_duration":
            case "exhale_duration":
            case "hold_duration":
                updateDurationSummary(key);
                break;
            case "night_mode":
                ((MainActivity)getActivity()).setNightMode();
                break;
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

    private void updateDurationSummary(String key) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppSettingsPrefs", 0);
        Preference preference = findPreference(key);
        String summary = "Current duration: "+ sharedPreferences.getString(key, "3000").charAt(0) + "s (tap to change)";
        preference.setSummary(summary);

    }
}