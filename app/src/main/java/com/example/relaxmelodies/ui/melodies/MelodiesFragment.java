package com.example.relaxmelodies.ui.melodies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.database.Melody;
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

        GridView gridView = binding.gridView;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position % 2 == 0) {
                    ((MainActivity) getContext()).playMelody(position / 2  + 1);
                }
            }
        });
        MelodiesAdapter adapter = new MelodiesAdapter();
        gridView.setAdapter(adapter);
        adapter.submitList(Melody.getAllMelodies());

//        melodiesViewModel.getMelodies().observe(getViewLifecycleOwner(), new Observer<List<Melody>>() {
//            @Override
//            public void onChanged(List<Melody> melodies) {
//                adapter.submitList(melodies);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}