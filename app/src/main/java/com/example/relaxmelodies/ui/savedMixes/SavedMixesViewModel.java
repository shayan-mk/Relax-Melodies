package com.example.relaxmelodies.ui.savedMixes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.relaxmelodies.database.Mix;

import java.util.List;

public class SavedMixesViewModel extends ViewModel {

    private final MutableLiveData<List<Mix>> mSavedMixes;

    public SavedMixesViewModel() {
        mSavedMixes = new MutableLiveData<>();
    }

    public void updateSavedMixes(List<Mix> savedMixes) {
        mSavedMixes.setValue(savedMixes);
    }

    public LiveData<List<Mix>> getSavedMixes() {
        return mSavedMixes;
    }
}