package com.example.relaxmelodies.ui.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.relaxmelodies.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        //Initializing truncate button
        Button truncateButton = binding.truncateButton;
        //Setting listener for truncate button
        truncateButton.setOnClickListener(view1 -> {

//            //Pass the truncate action to the handler
//            mainActivity.execute(DatabaseManager.getInstance().
//                    truncateTable(mainActivity.getHandler()));

        });

        SharedPreferences appSettingsPref = getActivity().getSharedPreferences("AppSettingsPrefs", 0);
        SharedPreferences.Editor editor = appSettingsPref.edit();
        boolean isNightMode = appSettingsPref.getBoolean("NightMode", false);

        //Initializing switch button
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch darModeSwitch = binding.darkModeSwitch;
        darModeSwitch.setChecked(isNightMode);

        //Setting listener for dark mode switch
        darModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //Dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("NightMode", true);
            } else {
                //Light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("NightMode", false);
            }
            editor.apply();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}