package com.example.relaxmelodies.ui.savedMixes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedMixesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SavedMixesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is savedMixes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}