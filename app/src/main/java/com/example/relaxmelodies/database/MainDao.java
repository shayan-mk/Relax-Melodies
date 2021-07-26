package com.example.relaxmelodies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    /*@Insert(onConflict = REPLACE)
    void insert(Melody melody);*/

    @Insert(onConflict = REPLACE)
    void insert(MixMelody mixMelody);

    /*@Delete
    void delete(Melody melody);*/

    @Query("DELETE FROM MixMelody WHERE name LIKE :name")
    void delete(String name);

    /*@Query("DELETE FROM Melody")
    void deleteAllMelodies();*/

    @Query("DELETE FROM MixMelody")
    void deleteAllSavedMixes();

    /*@Query("SELECT * FROM Melody")
    List<Melody> getAllMelodies();*/

    @Query("SELECT * FROM MixMelody")
    LiveData<List<MixMelody>> getAllSavedMixes();

    //It is not necessary.
    @Query("SELECT * FROM MixMelody WHERE name LIKE :name ")
    List<MixMelody> findSavedMixByName(String name);
}
