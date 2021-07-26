package com.example.relaxmelodies.ui.breathe;

import android.content.SharedPreferences;
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

    private TextView statusText;

    private Animation animationInhaleText;
    private Animation animationExhaleText;

    private Animation animationInhaleInnerCircle;
    private Animation animationExhaleInnerCircle;

    private View outerCircleView;
    private View innerCircleView;

    private int inhaleDuration;
    private int holdDuration;
    private int exhaleDuration;


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

        SharedPreferences appSettingsPref = getActivity().getSharedPreferences("AppSettingsPrefs", 0);
        inhaleDuration = appSettingsPref.getInt("InhaleDuration", 6000);
        exhaleDuration = appSettingsPref.getInt("ExhaleDuration", 6000);
        holdDuration = appSettingsPref.getInt("HoldDuration", 6000);


        //Inhale - make large
        animationInhaleText = AnimationUtils.loadAnimation((MainActivity)getActivity(), R.anim.zoomin);
        animationInhaleText.setFillAfter(true);
        animationInhaleText.setDuration(inhaleDuration);
        animationInhaleText.setAnimationListener(inhaleAnimationListener);

        animationInhaleInnerCircle = AnimationUtils.loadAnimation((MainActivity)getActivity(), R.anim.zoomin);
        animationInhaleInnerCircle.setFillAfter(true);
        animationInhaleInnerCircle.setDuration(inhaleDuration);
        animationInhaleInnerCircle.setAnimationListener(inhaleAnimationListener);

        //Inhale - make small
        animationExhaleText = AnimationUtils.loadAnimation((MainActivity)getActivity(), R.anim.zoomout);
        animationExhaleText.setFillAfter(true);
        animationExhaleText.setDuration(exhaleDuration);
        animationExhaleText.setAnimationListener(exhaleAnimationListener);

        animationExhaleInnerCircle = AnimationUtils.loadAnimation((MainActivity)getActivity(), R.anim.zoomout);
        animationExhaleInnerCircle.setFillAfter(true);
        animationExhaleInnerCircle.setDuration(exhaleDuration);
        animationExhaleInnerCircle.setAnimationListener(exhaleAnimationListener);


    }

    private Animation.AnimationListener inhaleAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //Log.d(TAG, "inhale animation end");
            statusText.setText("HOLD");
            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    statusText.setText("EXHALE");
                    statusText.startAnimation(animationExhaleText);
                    innerCircleView.startAnimation(animationExhaleInnerCircle);
                }
            }, holdDuration);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

    private Animation.AnimationListener exhaleAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //Log.d(TAG, "inhale animation end");
            statusText.setText("HOLD");
            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    statusText.setText("INHALE");
                    statusText.startAnimation(animationInhaleText);
                    innerCircleView.startAnimation(animationInhaleInnerCircle);
                }
            }, holdDuration);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };
}