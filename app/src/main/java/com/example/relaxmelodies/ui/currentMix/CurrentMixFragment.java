package com.example.relaxmelodies.ui.currentMix;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.PartialNowPlayingBinding;
import com.example.relaxmelodies.ui.savedMixes.SaveMixDialog;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CurrentMixFragment extends Fragment implements CurrentMixAdapter.ItemActionListener{
    private PartialNowPlayingBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: " + "Hwlloooooooooooooooooooooooooooo");
        binding = PartialNowPlayingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewNowPlaying;
        CurrentMixAdapter adapter = new CurrentMixAdapter(this);
        recyclerView.setAdapter(adapter);
//        adapter.submitList(((MainActivity)getActivity()).getCurrentMelodies());

        Log.d(TAG, "onCreateView: " + binding.saveMixButton);
        binding.saveMixButton.setOnClickListener(v -> openDialogBox());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(List<Integer> melodyIds) {

    }

    @Override
    public void onItemDelete(Mix mix) {

    }

    private void openDialogBox(){
        Log.d(TAG, "openDialogBox: " + "ooooooooooooooooooooooooooo");
        new SaveMixDialog(((MainActivity)getActivity()).getNowPlaying());
    }

    // play and pause
    // settings
    // current playing

}
