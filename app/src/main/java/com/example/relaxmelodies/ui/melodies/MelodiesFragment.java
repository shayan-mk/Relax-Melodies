package com.example.relaxmelodies.ui.melodies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.relaxmelodies.database.Melody;
import com.example.relaxmelodies.databinding.FragmentMelodiesBinding;

import java.util.List;

public class MelodiesFragment extends Fragment implements MelodiesAdapter.ItemActionListener {

    private MelodiesViewModel melodiesViewModel;
    private FragmentMelodiesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        melodiesViewModel =
                new ViewModelProvider(getActivity()).get(MelodiesViewModel.class);

        binding = FragmentMelodiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GridView gridView = binding.gridView;
        MelodiesAdapter adapter = new MelodiesAdapter(this);
        gridView.setAdapter(adapter);

        melodiesViewModel.getMelodies().observe(getViewLifecycleOwner(), new Observer<List<Melody>>() {
            @Override
            public void onChanged(List<Melody> melodies) {
                adapter.submitList(melodies);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int ID) {
        // TODO: play melody
    }
}