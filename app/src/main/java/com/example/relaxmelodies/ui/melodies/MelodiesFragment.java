package com.example.relaxmelodies.ui.melodies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.relaxmelodies.databinding.FragmentMelodiesBinding;

public class MelodiesFragment extends Fragment {

    private MelodiesViewModel melodiesViewModel;
    private FragmentMelodiesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        melodiesViewModel =
                new ViewModelProvider(getActivity()).get(MelodiesViewModel.class);

        binding = FragmentMelodiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMelodies;
        melodiesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}