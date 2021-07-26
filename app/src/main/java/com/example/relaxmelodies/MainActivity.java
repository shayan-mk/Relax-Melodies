package com.example.relaxmelodies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.relaxmelodies.database.DatabaseManager;
import com.example.relaxmelodies.database.Melody;
import com.example.relaxmelodies.databinding.ActivityMainBinding;
import com.example.relaxmelodies.ui.melodies.MelodiesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

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
    private ExecutorService threadPool;
    private Handler handler;
    private MelodyManager melodyManager;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.initDatabaseManager(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_breathe,
                R.id.navigation_melodies,
                R.id.navigation_saved_mixes,
                R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        initApp();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(TAG, "handleMessage: " + msg.what);
                switch (msg.what) {
                    case DB_MELODY_LOAD:
                        // TODO:
                        break;
                    case DB_SAVED_MIX_LOAD:
                        // TODO
                        break;
                    case DB_MELODY_INSERT:
                        // TODO
                        break;
                    case DB_SAVED_MIX_INSERT:
                        // TODO
                        break;
                    case DB_MELODY_DELETE:
                        // TODO
                        break;
                    case DB_MELODY_TRUNCATE:
                        // TODO
                        break;
                    case DB_SAVED_MIX_TRUNCATE:
                        // TODO
                        break;
                }
            }
        };
    }

    public void execute(Runnable runnable){
        threadPool.execute(runnable);
    }

    public Handler getHandler(){
        return handler;
    }

    private void initApp(){
        Log.d(TAG, "initApp: ");

        MelodiesViewModel melodiesVM =  new ViewModelProvider(this).get(MelodiesViewModel.class);
        Log.d(TAG, "initApp: live: "+ melodiesVM.getMelodies());
//        Log.d(TAG, "initApp: value: "+ melodiesVM.getMelodies().getValue());
//        melodyManager = new MelodyManager(this, melodiesVM.getMelodies().getValue());

        SharedPreferences appSettingsPref = getSharedPreferences("AppSettingsPrefs", 0);
        boolean isNightMode = appSettingsPref.getBoolean("NightMode", false);

        if(isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void playMelody(int ID) {
        melodyManager._internalPlayMelody(ID);
    }

    public void stopMelody(int ID) {
        melodyManager._internalStopMelody(ID);
    }

    public void playMix(String mixName) {
        // TODO
    }

    public List<Integer> getCurrentMelodies() {
        // TODO
        return null;
    }
}