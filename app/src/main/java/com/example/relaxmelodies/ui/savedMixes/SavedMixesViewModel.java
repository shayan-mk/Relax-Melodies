package com.example.relaxmelodies.ui.savedMixes;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.relaxmelodies.database.Mix;

import java.util.ArrayList;
import java.util.List;

public class SavedMixesViewModel extends ViewModel {

    private static final String TAG = SavedMixesViewModel.class.getName();
    private final MutableLiveData<List<Mix>> mSavedMixes;
    private List<Mix> cacheSavedMixes;

    public SavedMixesViewModel() {
        mSavedMixes = new MutableLiveData<>();
    }

    public void updateSavedMixes(List<Mix> savedMixes) {
        cacheSavedMixes = new ArrayList<>(savedMixes);
        mSavedMixes.setValue(cacheSavedMixes);
    }

    public void deleteSavedMix(Mix savedMix){
        Log.d(TAG, "deleteSavedMix: " + cacheSavedMixes.get(0).getMelody_ids());
        cacheSavedMixes.remove(savedMix);
        mSavedMixes.setValue(new ArrayList<>(cacheSavedMixes));
    }

    public LiveData<List<Mix>> getSavedMixes() {
        return mSavedMixes;
    }

    public void filterSavedMixes(String text){
        List<Mix> filteredList = new ArrayList<>();
        for (Mix mix : cacheSavedMixes) {
            if(mix.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(mix);
            }
        }
        mSavedMixes.setValue(filteredList);
    }
}