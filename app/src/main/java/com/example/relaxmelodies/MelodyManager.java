package com.example.relaxmelodies;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class MelodyManager {

    private MediaPlayer mediaPlayer;
    private String nameOfMelody;
    private Context context;
    private int resourceId;

    public MelodyManager(Context context, int resourceId, String nameOfMelody){
        this.nameOfMelody = nameOfMelody;
        this.context = context;
        this.resourceId = resourceId;
    }

    public void playMelody(){
        if(mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(context, resourceId);

        try {
            if(!mediaPlayer.isPlaying()) {
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopMelody(){
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }
}
