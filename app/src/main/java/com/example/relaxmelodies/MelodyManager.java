package com.example.relaxmelodies;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.relaxmelodies.database.Melody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MelodyManager {

    private final Context context;
    private final Map<Integer, MediaPlayer> mediaPlayers;


    public MelodyManager(Context context, List<Melody> melodies) {
        this.context = context;
        mediaPlayers = new HashMap<>();
        initMediaPlayers(melodies);
    }

    public void initMediaPlayers(List<Melody> melodies) {
        for (Melody melody : melodies) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, melody.getResourceId());
            mediaPlayers.put(melody.ID, mediaPlayer);
        }
    }

    public void _internalPlayMelody(int ID) {
        MediaPlayer mediaPlayer = mediaPlayers.get(ID);

        try {
            if (mediaPlayer != null) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  _internalStopMelody(int ID) {
        MediaPlayer mediaPlayer = mediaPlayers.get(ID);

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

    public Runnable playMelody(int ID) {
        return () -> {
            _internalPlayMelody(ID);
        };
    }

    public Runnable stopMelody(int ID) {
        return () -> {
            _internalStopMelody(ID);
        };
    }


}
