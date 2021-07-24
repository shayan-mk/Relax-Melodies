package com.example.relaxmelodies.ui.melodies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MelodiesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MelodiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is melodies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}