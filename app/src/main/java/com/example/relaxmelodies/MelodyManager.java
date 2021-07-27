package com.example.relaxmelodies;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.relaxmelodies.database.Melody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MelodyManager {

    private static final String TAG = MelodyManager.class.getName();
    private final Context context;
    private final Map<Integer, MediaPlayer> mediaPlayers;
    private List<Integer> nowPlaying;


    public MelodyManager(Context context) {
        this.context = context;
        mediaPlayers = new HashMap<>();
        nowPlaying = new ArrayList<>();
        initMediaPlayers();
    }

    public void initMediaPlayers() {
        for (Melody melody : Melody.getAllMelodies()) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, melody.getResourceId());
//            mediaPlayer.release();
            Log.d(TAG, "initMediaPlayers: id " + melody.getId() + " created");
            mediaPlayers.put(melody.getId(), mediaPlayer);
        }
    }

    /*private void updateNowPlaying(List<Integer> list) {
        nowPlaying = new ArrayList<>(list);
    }*/

    public void _internalPlayMelody(int id) {
        MediaPlayer mediaPlayer = mediaPlayers.get(id);

        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {

                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                nowPlaying.add(id);
            }
        }
    }

    public void  _internalStopMelody(int id) {
        MediaPlayer mediaPlayer = mediaPlayers.get(id);

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayList<Integer> newList = new ArrayList<>();
                for (Integer integer : new ArrayList<>(nowPlaying)) {
                    if(integer != id){
                        newList.add(integer);
                    }
                }
                nowPlaying = newList;
            }
        }
    }

    public Runnable playMix(List<Integer> melodyIds) {
        return () -> {
            for (Integer melodyId : melodyIds) {
                _internalPlayMelody(melodyId);
            }
        };
    }

    public Runnable releaseAll() {
        return () -> {
            for (Integer melodyId : nowPlaying) {
                _internalStopMelody(melodyId);
            }
        };
    }

    public Runnable changePlayStatus(int id){
        return () -> {
            if(nowPlaying.contains(id)){
                _internalStopMelody(id);
            }else {
                _internalPlayMelody(id);
            }
        };
    }

}
