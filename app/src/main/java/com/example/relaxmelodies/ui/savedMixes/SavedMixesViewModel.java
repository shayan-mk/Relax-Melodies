package com.example.relaxmelodies.ui.savedMixes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.relaxmelodies.database.Mix;

import java.util.List;

public class SavedMixesViewModel extends ViewModel {

    private MutableLiveData<List<Mix>> mSavedMixes;

    public SavedMixesViewModel() {
        // TODO: set saved mixes from DB
//        mSavedMixes =
    }
    public LiveData<List<Mix>> getSavedMixes() {
        return mSavedMixes;
    }
}