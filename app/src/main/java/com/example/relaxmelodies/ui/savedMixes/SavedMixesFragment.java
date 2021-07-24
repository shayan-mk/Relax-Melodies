package com.example.relaxmelodies.ui.savedMixes;

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

import com.example.relaxmelodies.databinding.FragmentSavedMixesBinding;

public class SavedMixesFragment extends Fragment {

    private SavedMixesViewModel savedMixesViewModel;
    private FragmentSavedMixesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedMixesViewModel =
                new ViewModelProvider(getActivity()).get(SavedMixesViewModel.class);

        binding = FragmentSavedMixesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSavedMixes;
        savedMixesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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