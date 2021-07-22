package com.example.relaxmelodies;

import java.util.ArrayList;

public class SavedMix {

    private static ArrayList<SavedMix> allMixes = new ArrayList<>();
    private static int lastUsedId = 0;

    private String id;
    private String name;
    private ArrayList<String> melodies;

    public void saveAMix(String name, ArrayList<String> melodies){
        new SavedMix(name, melodies);

        //Todo: add to database.
    }

    private SavedMix(String name, ArrayList<String> melodies) {
        this.id = "Mix_" + (getLastUsedId() + 1);

        this.name = name;
        this.melodies = melodies;

        allMixes.add(this);
        lastUsedId += 1;
    }

    private static int getLastUsedId(){
        return lastUsedId;
    }
    public ArrayList<SavedMix> getAllMixes(){
        return allMixes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMelodies() {
        return melodies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMelodies(ArrayList<String> melodies) {
        this.melodies = melodies;
    }
}
