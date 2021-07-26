package com.example.relaxmelodies.ui.breathe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BreatheViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private int inhaleDuration;
    private int holdDuration;
    private int exhaleDuration;

    public BreatheViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is breathe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public int getInhaleDuration() {
        return inhaleDuration;
    }

    public void setInhaleDuration(int inhaleDuration) {
        this.inhaleDuration = inhaleDuration;
    }

    public int getHoldDuration() {
        return holdDuration;
    }

    public void setHoldDuration(int holdDuration) {
        this.holdDuration = holdDuration;
    }

    public int getExhaleDuration() {
        return exhaleDuration;
    }

    public void setExhaleDuration(int exhaleDuration) {
        this.exhaleDuration = exhaleDuration;
    }
}