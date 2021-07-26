package com.example.relaxmelodies.ui.melodies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.relaxmelodies.database.DatabaseManager;
import com.example.relaxmelodies.database.Melody;

import java.util.List;

public class MelodiesViewModel extends ViewModel {

    private final MutableLiveData<List<Melody>> mMelodies;

    public MelodiesViewModel() {
        mMelodies = new MutableLiveData<>(DatabaseManager.getInstance().loadMelodies());
//        mMelodies = DatabaseManager.getInstance().loadMelodies();
    }

    public LiveData<List<Melody>> getMelodies() {
        mMelodies.setValue(DatabaseManager.getInstance().loadMelodies());
        return mMelodies;
    }
}