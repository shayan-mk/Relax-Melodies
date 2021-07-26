package com.example.relaxmelodies.ui.savedMixes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.relaxmelodies.database.Mix;

import java.util.List;

public class SavedMixesViewModel extends ViewModel {

    private LiveData<List<Mix>> mSavedMixes;

    public SavedMixesViewModel() {

    }
    public LiveData<List<Mix>> getSavedMixes() {
        return mSavedMixes;
    }
}