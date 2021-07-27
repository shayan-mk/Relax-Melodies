package com.example.relaxmelodies.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {MixMelody.class}, version = 54, exportSchema = false)

public abstract class RoomDB extends RoomDatabase {
    //Create database instance
    private static RoomDB database;
    //Define database name
    private static final String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context) {
        //Check condition
        if (database == null) {
            //When database is null -> initialize database
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return database
        return database;
    }

    //Create Dao
    public abstract MainDao mainDao();

    public static RoomDB getDatabase() {
        return database;
    }
}