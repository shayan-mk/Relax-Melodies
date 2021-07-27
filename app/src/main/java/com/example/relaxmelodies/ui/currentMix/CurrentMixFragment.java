package com.example.relaxmelodies.ui.currentMix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.PartialNowPlayingBinding;

import java.util.List;

public class CurrentMixFragment extends Fragment implements CurrentMixAdapter.ItemActionListener{
    private PartialNowPlayingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = PartialNowPlayingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewNowPlaying;
        CurrentMixAdapter adapter = new CurrentMixAdapter(this);
        recyclerView.setAdapter(adapter);
//        adapter.submitList(((MainActivity)getActivity()).getCurrentMelodies());

        binding.saveMixButton.
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

    // play and pause
    // settings
    // current playing

}
