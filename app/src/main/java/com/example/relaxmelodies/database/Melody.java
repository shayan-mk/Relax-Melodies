package com.example.relaxmelodies.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//@Entity(tableName = "Melody")
public class Melody implements Serializable {

    //@PrimaryKey(autoGenerate = true)
    public int ID;

    //@ColumnInfo(name = "name")
    private final String name;

    //@ColumnInfo(name = "duration")
    private final double duration;

    //@ColumnInfo(name = "fileAddress")
    private final int resourceId;

    //@ColumnInfo(name = "iconAddress")
    private final int iconResource;

    public Melody(String name, double duration, int resourceId, int iconResource) {

        this.name = name;
        this.duration = duration;
        this.resourceId = resourceId;
        this.iconResource = iconResource;

    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getIconResource() {
        return iconResource;
    }

}
