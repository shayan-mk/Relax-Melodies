package com.example.relaxmelodies;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.relaxmelodies.database.Melody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MelodyManager {

    private static final String TAG = MelodyManager.class.getName();
    private final Context context;
    private final Map<Integer, MediaPlayer> mediaPlayers;


    public MelodyManager(Context context) {
        this.context = context;
        mediaPlayers = new HashMap<>();
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

    public void _internalPlayMelody(int id) {
        MediaPlayer mediaPlayer = mediaPlayers.get(id);

        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
                Log.d(TAG, "_internalPlayMelody: " + id + " preparing");
//                    mediaPlayer.prepare();
                Log.d(TAG, "_internalPlayMelody: " + id + " starting");
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                Log.d(TAG, "_internalPlayMelody: " + id + " started");
            }
        }
    }

    public void  _internalStopMelody(int id) {
        MediaPlayer mediaPlayer = mediaPlayers.get(id);

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
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

    public Runnable stopAllMelodies() {
        return () -> {
            List<Integer> melodyIds = ((MainActivity) context).getCurrentMelodies();
            for (Integer melodyId : melodyIds) {
                _internalStopMelody(melodyId);
            }
        };
    }

    public Runnable playMelody(int id) {
        return () -> {
            _internalPlayMelody(id);
        };
    }

    public Runnable stopMelody(int id) {
        return () -> {
            _internalStopMelody(id);
        };
    }


}
