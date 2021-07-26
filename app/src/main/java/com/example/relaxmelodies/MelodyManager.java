package com.example.relaxmelodies;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.relaxmelodies.database.Melody;

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

    public void _internalPlayMelody(int id) {
        MediaPlayer mediaPlayer = mediaPlayers.get(id);

        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
//                    mediaPlayer.prepare();
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
                mediaPlayer.release();
                nowPlaying.remove(id);
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
