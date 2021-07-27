package com.example.relaxmelodies.ui.savedMixes;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.FragmentSavedMixesBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SavedMixesFragment extends Fragment implements SavedMixesAdapter.ItemActionListener {

    private static final String TAG = SavedMixesFragment.class.getName();
    private SavedMixesViewModel savedMixesViewModel;
    private FragmentSavedMixesBinding binding;
    private EditText searchBar;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private static final int REQUEST_CODE = 1234;

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
        micButton.setOnClickListener(v -> startVoiceRecognitionActivity());

        searchBar = binding.editText;
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                savedMixesViewModel.filterSavedMixes(s.toString());
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

    private void speak(){
        //intent to show speech
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say name of the mix");

        //start intent
        try {
            //show dialog
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            Log.d(TAG, "speak: ");
        }
        catch (Exception e){
            //if there was some error
            //get message of error and show
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice searching...");
        startActivityForResult(intent, REQUEST_CODE);
    }
    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            final ArrayList< String > matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!matches.isEmpty())
            {
                String Query = matches.get(0);
                searchBar.setText(Query);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}