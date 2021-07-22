package com.example.relaxmelodies;

import java.util.ArrayList;

public class Melody {

    private static ArrayList<Melody> allMelodies = new ArrayList<>();
    private static int lastUsedId = 0;

    private String ID;
    private String name;
    private int duration;
    private String fileAddress;

    public void saveAMelody(String name, int duration, String fileAddress){
        new Melody(name, duration, fileAddress);

        //Todo: add to database.
    }

    private Melody(String name, int duration, String fileAddress) {
        this.ID = "Melody_" + (lastUsedID() + 1);
        this.name = name;
        this.duration = duration;
        this.fileAddress = fileAddress;

        allMelodies.add(this);
        lastUsedId += 1;
    }

    public static ArrayList<Melody> getAllMelodies(){
        return allMelodies;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public int lastUsedID(){
        return lastUsedId;
    }
}
