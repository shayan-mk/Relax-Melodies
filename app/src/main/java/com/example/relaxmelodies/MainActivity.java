package com.example.relaxmelodies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.relaxmelodies.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //Defining Message Codes
    private static final int BASE_MESSAGE_CODE = 1000;
    public static final int DB_MELODY_LOAD = BASE_MESSAGE_CODE + 1;
    public static final int DB_SAVED_MIX_LOAD = BASE_MESSAGE_CODE + 2;
    public static final int DB_MELODY_INSERT = BASE_MESSAGE_CODE + 3;
    public static final int DB_SAVED_MIX_INSERT = BASE_MESSAGE_CODE + 4;
    public static final int DB_MELODY_DELETE = BASE_MESSAGE_CODE + 5;
    public static final int DB_SAVED_MIX_DELETE = BASE_MESSAGE_CODE + 6;
    public static final int DB_MELODY_TRUNCATE = BASE_MESSAGE_CODE + 7;
    public static final int DB_SAVED_MIX_TRUNCATE = BASE_MESSAGE_CODE + 8;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_breathe,
                R.id.navigation_melodies,
                R.id.navigation_saved_mixes,
                R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void playMix(String mixName) {
        // TODO
    }
}