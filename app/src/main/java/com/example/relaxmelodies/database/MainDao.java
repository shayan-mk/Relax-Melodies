package com.example.relaxmelodies.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(Melody melody);

    @Insert(onConflict = REPLACE)
    void insert(SavedMix savedMix);

    @Delete
    void delete(Melody melody);

    @Delete
    void delete(SavedMix savedMix);

    @Query("DELETE FROM Melody")
    void deleteAllMelodies();

    @Query("DELETE FROM SavedMix")
    void deleteAllSavedMixes();

    @Query("SELECT * FROM Melody")
    List<Melody> getAllMelodies();

    @Query("SELECT * FROM SavedMix")
    List<SavedMix> getAllSavedMixes();

    @Query("SELECT * FROM SavedMix WHERE name LIKE :name ")
    List<SavedMix> findSavedMixByName(String name);
}
