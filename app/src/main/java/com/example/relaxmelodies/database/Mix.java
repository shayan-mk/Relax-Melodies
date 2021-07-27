package com.example.relaxmelodies.database;

import java.util.ArrayList;
import java.util.List;

public class Mix {

    private final String name;
    private final List<Integer> melody_ids;

    public Mix(String name) {
        this.name = name;
        this.melody_ids = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getMelody_ids() {
        return melody_ids;
    }

    public void addMelodyId(int id) {
        melody_ids.add(id);
    }
}
