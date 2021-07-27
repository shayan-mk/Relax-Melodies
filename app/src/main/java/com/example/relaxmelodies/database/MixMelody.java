package com.example.relaxmelodies.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "MixMelody")
public class MixMelody implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "melody_id")
    private final int melodyId;

    public MixMelody(String name, int melodyId) {
        this.name = name;
        this.melodyId = melodyId;

    }


    public String getName() {
        return name;
    }

    public int getMelodyId() {
        return melodyId;
    }
}
