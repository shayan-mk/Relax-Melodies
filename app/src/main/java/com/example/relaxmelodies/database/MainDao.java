package com.example.relaxmelodies.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(MixMelody mixMelody);

    @Query("DELETE FROM MixMelody WHERE name LIKE :name")
    void delete(String name);

    @Query("DELETE FROM MixMelody")
    void deleteAllSavedMixes();

    @Query("SELECT * FROM MixMelody")
    List<MixMelody> getAllSavedMixes();

}
