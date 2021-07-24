package com.example.relaxmelodies.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "MixMelody",
        foreignKeys = {@ForeignKey(entity = Melody.class,
                parentColumns = "ID",
                childColumns = "melody_id",
                onDelete = ForeignKey.CASCADE)
        })
public class MixMelody implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "melody_id")
    private final int melodyId;

    public MixMelody(String name, int melodyId) {

        this.name = name;
        this.melodyId = melodyId;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMelodyId(){
        return melodyId;
    }
}
