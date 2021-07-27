package com.example.relaxmelodies;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.relaxmelodies.database.DatabaseManager;
import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.ActivityMainBinding;
import com.example.relaxmelodies.ui.savedMixes.SavedMixesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private static final int BASE_MESSAGE_CODE = 1000;
    public static final int DB_MELODY_LOAD = BASE_MESSAGE_CODE + 1;
    public static final int DB_SAVED_MIX_LOAD = BASE_MESSAGE_CODE + 2;
    public static final int DB_MELODY_INSERT = BASE_MESSAGE_CODE + 3;
    public static final int DB_SAVED_MIX_INSERT = BASE_MESSAGE_CODE + 4;
    public static final int DB_MELODY_DELETE = BASE_MESSAGE_CODE + 5;
    public static final int DB_SAVED_MIX_DELETE = BASE_MESSAGE_CODE + 6;
    public static final int DB_MELODY_TRUNCATE = BASE_MESSAGE_CODE + 7;
    public static final int DB_SAVED_MIX_TRUNCATE = BASE_MESSAGE_CODE + 8;

    private MelodyManager melodyManager;
    private DatabaseManager databaseManager;
    private Handler handler;
    private ExecutorService threadPool;
    private SavedMixesViewModel savedMixesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        databaseManager = new DatabaseManager(this);
        melodyManager = new MelodyManager(this);
        threadPool = Executors.newFixedThreadPool(10);
        handler = getNewHandler();
        savedMixesViewModel =
                new ViewModelProvider(this).get(SavedMixesViewModel.class);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
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

        initPreferences();


    }

    private void initPreferences() {

        SharedPreferences appSettingsPref = getSharedPreferences("AppSettingsPrefs", 0);
        boolean isNightMode = appSettingsPref.getBoolean("NightMode", false);

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public void playMelody(int id) {
        execute(melodyManager.playMelody(id));
    }

    public void stopMelody(int id) {
        execute(melodyManager.stopMelody(id));
    }

    public void playMix(List<Integer> melodyIds) {

        execute(melodyManager.playMix(melodyIds));
    }

    public void deleteMix(Mix mix) {
        execute(databaseManager.deleteMix(mix, handler));
    }

    public void saveMix(String name, List<Integer> melodyIds) {
        execute(databaseManager.insertMix(name, melodyIds, handler));
    }

    public void loadSavedMixes() {
        execute(databaseManager.loadMixList(handler));
    }

    public void truncateSavedMixes() {
        execute(databaseManager.truncateSavedMixes(handler));
    }

    public void playAnimation(Runnable runnable, long postDelay){
        handler.postDelayed(runnable, postDelay);
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private Handler getNewHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {

                    case DB_SAVED_MIX_LOAD:
                        List<Mix> savedMixes = Arrays.asList((Mix[]) msg.obj);
                        savedMixesViewModel.updateSavedMixes(savedMixes);
                        break;
                    case DB_SAVED_MIX_INSERT:
                        Toast.makeText(MainActivity.this, "Saved successfully!", Toast.LENGTH_SHORT).show();
                        break;
                    case DB_SAVED_MIX_TRUNCATE:
                        Toast.makeText(MainActivity.this, "All mixes are deleted successfully!", Toast.LENGTH_SHORT).show();
                        break;
                    case DB_SAVED_MIX_DELETE:
                        savedMixesViewModel.deleteSavedMix((Mix)msg.obj);
                        Toast.makeText(MainActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
}