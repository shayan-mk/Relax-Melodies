package com.example.relaxmelodies.ui.breathe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.R;
import com.example.relaxmelodies.databinding.FragmentBreatheBinding;

public class BreatheFragment extends Fragment {

    private BreatheViewModel breatheViewModel;
    private FragmentBreatheBinding binding;

    private Animation animationInhaleText;
    private Animation animationHoldText;
    private Animation animationExhaleText;

    private View outerCircleView;
    private View innerCircleView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        breatheViewModel =
                new ViewModelProvider(getActivity()).get(BreatheViewModel.class);

        binding = FragmentBreatheBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBreathe;
        breatheViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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


    private void prepareAnimation(){
        int inhaleDuration = breatheViewModel.getInhaleDuration();
        int exhaleDuration = breatheViewModel.getExhaleDuration();
        
        
        //Inhale - make large
        animationInhaleText = AnimationUtils.loadAnimation((MainActivity)getActivity(), R.anim.zoomin);
        
        
    }
}