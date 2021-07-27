package com.example.relaxmelodies.ui.savedMixes;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.FragmentSavedMixesBinding;

import java.util.List;

public class SavedMixesFragment extends Fragment implements SavedMixesAdapter.ItemActionListener {

    private SavedMixesViewModel savedMixesViewModel;
    private FragmentSavedMixesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedMixesViewModel =
                new ViewModelProvider(getActivity()).get(SavedMixesViewModel.class);
        ((MainActivity)getActivity()).loadSavedMixes();

        binding = FragmentSavedMixesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SavedMixesAdapter adapter = new SavedMixesAdapter(this);
        RecyclerView recyclerView = binding.recyclerViewSavedMixes;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);

        Button micButton = binding.micButton;
        //TODO: voice search

        EditText searchBar = binding.editText;
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO: filter results
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        savedMixesViewModel.getSavedMixes().observe(getViewLifecycleOwner(), new Observer<List<Mix>>() {
            @Override
            public void onChanged(List<Mix> mixes) {
                adapter.submitList(mixes);
            }
        });

        return root;
    }

    @Override
    public void onItemClick(List<Integer> melodyIds) {
        ((MainActivity)getActivity()).playMix(melodyIds);
    }

    @Override
    public void onItemDelete(Mix mix) {
        ((MainActivity)getActivity()).deleteMix(mix);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}