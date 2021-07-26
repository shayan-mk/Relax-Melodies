package com.example.relaxmelodies.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Melody")
public class Melody implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int ID;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "duration")
    private final double duration;

    @ColumnInfo(name = "fileAddress")
    private final String fileAddress;

    public Melody(String name, double duration, String fileAddress) {

        this.name = name;
        this.duration = duration;
        this.fileAddress = fileAddress;

    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public String getFileAddress() {
        return fileAddress;
    }

}
