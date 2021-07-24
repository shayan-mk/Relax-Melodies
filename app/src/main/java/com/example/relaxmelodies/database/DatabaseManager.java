package com.example.relaxmelodies.database;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

//TODO: write codes in a separate file and do not import main activity!
import com.example.relaxmelodies.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseManager {
    public static final String TAG = DatabaseManager.class.getName();

    private static RoomDB database;
    private static DatabaseManager databaseManager = null;

    private synchronized void insertMelody(Melody melody){
        database.mainDao().insert(melody);
    }

    private synchronized void insertMixMelody(MixMelody mixMelody){
        database.mainDao().insert(mixMelody);
    }

    private synchronized void deleteMelody(Melody melody){
        database.mainDao().delete(melody);
    }

    private synchronized void deleteSavedMix(String mixName){
        database.mainDao().delete(mixName);
    }

    private synchronized void truncateMelodyTable(){
        database.mainDao().deleteAllMelodies();
    }

    private synchronized void truncateMixMelodyTable(){
        database.mainDao().deleteAllSavedMixes();
    }

    private List<Melody> loadMelodies(){
        return database.mainDao().getAllMelodies();
    }

    private List<MixMelody> loadSavedMixes(){
        return database.mainDao().getAllSavedMixes();
    }

    //It is not necessary.
    private List<MixMelody> findMixByName(String name){
        return database.mainDao().findSavedMixByName(name);
    }

    public static DatabaseManager getInstance(){
        if(databaseManager == null){
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public static void initDatabaseManager(Context context){
        databaseManager = new DatabaseManager();
        database = RoomDB.getInstance(context);
    }

    public Runnable loadMelodyList( Handler handler) {
        return () -> {
            List<Melody> melodyList = loadMelodies();
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_LOAD;
            message.arg1 = 1;
            Melody[] melodies = new Melody[melodyList.size()];
            message.obj = melodyList.toArray(melodies);
            handler.sendMessage(message);
        };
    }

    public Runnable insertMelody( Melody melody, Handler handler) {
        return () -> {
            insertMelody(melody);
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_INSERT;
            message.arg1 = 1;
            handler.sendMessage(message);
        };
    }

    public Runnable deleteMelody( Melody melody, Handler handler) {
        return () -> {
            deleteMelody(melody);
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_DELETE;
            message.arg1 = 1;
            message.obj = melody;
            handler.sendMessage(message);
        };
    }

    public Runnable truncateMelodyTable(Handler handler){
        return () -> {
            truncateMelodyTable();
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_TRUNCATE;
            message.arg1 = 1;
            handler.sendMessage(message);
        };
    }

    public Runnable insertMix( Mix mix, Handler handler) {
        return () -> {
            String name = mix.getName();
            for (int melody_id : mix.getMelody_ids()) {
                insertMixMelody(new MixMelody(name, melody_id));
            }
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_INSERT;
            message.arg1 = 1;
            handler.sendMessage(message);
        };
    }

    public Runnable deleteMelody( Mix mix, Handler handler) {
        return () -> {
            deleteSavedMix(mix.getName());
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_DELETE;
            message.arg1 = 1;
            handler.sendMessage(message);
        };
    }

    public Runnable truncateMixMelodyTable(Handler handler){
        return () -> {
            truncateMixMelodyTable();
            Message message = new Message();
            message.what = MainActivity.DB_MELODY_TRUNCATE;
            message.arg1 = 1;
            handler.sendMessage(message);
        };
    }

    public Runnable loadMixList(Handler handler){
        return () ->{
            List<MixMelody> mixList = loadSavedMixes();
            List<Mix> mixes = new ArrayList<>();

            for (MixMelody mixMelody : mixList) {
                addMixMelodyToList(mixMelody, mixes);
            }

            Message message = new Message();
            message.what = MainActivity.DB_SAVED_MIX_LOAD;
            message.arg1 = 1;
            Melody[] mixArr = new Melody[mixes.size()];
            message.obj = mixes.toArray(mixArr);
            handler.sendMessage(message);
        };
    }

    private void addMixMelodyToList(MixMelody mixMelody, List<Mix> list){
        String name = mixMelody.getName();
        for (Mix mix : list) {
            if(name.equals(mix.getName())){
                mix.addMelodyId(mixMelody.getMelodyId());
                return;
            }
        }
        list.add(new Mix(mixMelody.getName(), Arrays.asList(mixMelody.getMelodyId())));
    }
}
