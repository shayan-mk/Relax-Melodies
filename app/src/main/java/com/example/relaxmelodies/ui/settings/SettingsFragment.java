package com.example.relaxmelodies.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.relaxmelodies.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }


//    private SettingsViewModel settingsViewModel;
//    private FragmentSettingsBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        settingsViewModel =
//                new ViewModelProvider(getActivity()).get(SettingsViewModel.class);
//
//        binding = FragmentSettingsBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textSettings;
//        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
//        //Initializing truncate button
//        final Button truncateButton = binding.truncateButton;
//        //Setting listener for truncate button
//        truncateButton.setOnClickListener(view1 -> {
//
////            //Pass the truncate action to the handler
////            mainActivity.execute(DatabaseManager.getInstance().
////                    truncateTable(mainActivity.getHandler()));
//
//        });
//
//        SharedPreferences appSettingsPref = getActivity().getSharedPreferences("AppSettingsPrefs", 0);
//        SharedPreferences.Editor editor = appSettingsPref.edit();
//        boolean isNightMode = appSettingsPref.getBoolean("NightMode", false);
//
//        //Initializing switch button
//        @SuppressLint("UseSwitchCompatOrMaterialCode")
//        final Switch darModeSwitch = binding.darkModeSwitch;
//        darModeSwitch.setChecked(isNightMode);
//
//        //Setting listener for dark mode switch
//        darModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                //Dark mode
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                editor.putBoolean("NightMode", true);
//            } else {
//                //Light mode
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                editor.putBoolean("NightMode", false);
//            }
//            editor.apply();
//        });
//
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}