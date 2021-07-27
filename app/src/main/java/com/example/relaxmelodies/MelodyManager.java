package com.example.relaxmelodies;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

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
    private Boolean isPlaying;


    public MelodyManager(Context context) {
        this.context = context;
        mediaPlayers = new HashMap<>();
        nowPlaying = new ArrayList<>();
        initMediaPlayers();
        isPlaying = false;
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

    public void _internalSelectMelody(int id) {
        if(!isPlaying)
            isPlaying = true;
        nowPlaying.add(id);

        play();
    }

    public void _internalDeselectMelody(int id) {
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

    private void play(){
        for (Integer id : nowPlaying) {
            MediaPlayer mp = mediaPlayers.get(id);
            if(!mp.isPlaying()){
                mp.start();
                mp.setLooping(true);
            }
        }
    }

    public Runnable playMix(List<Integer> melodyIds) {
        return () -> {
            isPlaying = true;

            for (Integer id : nowPlaying) {
                stopMediaPlayer(id);
            }
            nowPlaying = new ArrayList<>(melodyIds);
            play();
        };
    }

    private void stopMediaPlayer(int id){
        MediaPlayer mp = mediaPlayers.get(id);

        if(mp.isPlaying()){
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Runnable changeMelodyPlayerStatus(){
        return () -> {
            if(isPlaying){

                isPlaying = false;
                for (Integer id : nowPlaying) {
                    stopMediaPlayer(id);
            }
        }else{
            isPlaying = true;

            play();
        }
        };
    }

    public Runnable changePlayStatus(int id){
        return () -> {
            if(nowPlaying.contains(id)){
                _internalDeselectMelody(id);
            }else {
                _internalSelectMelody(id);
            }
        };
    }

    public List<Integer> getNowPlaying(){
        return nowPlaying;
    }


}
