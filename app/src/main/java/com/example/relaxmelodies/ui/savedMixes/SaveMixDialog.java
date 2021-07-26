package com.example.relaxmelodies.ui.savedMixes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.relaxmelodies.MainActivity;
import com.example.relaxmelodies.R;

import java.util.List;

public class SaveMixDialog extends AppCompatDialogFragment {

    private final List<Integer> melodyIds;

    public SaveMixDialog(List<Integer> melodyIds) {
        this.melodyIds = melodyIds;
    }

    @NonNull
    @Override
    @SuppressLint("SetTextI18n")
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.save_mix_dialog, null);
        final EditText editText = view.findViewById(R.id.save_mix_text);

        // setting listeners of save buttons
        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view1 -> {
            String name = editText.getText().toString();
            if(!name.isEmpty()){
                ((MainActivity)getActivity()).saveMix(name, melodyIds);
                Toast.makeText(getContext(), "Saved successfully!", Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(getContext(), "Enter the name!", Toast.LENGTH_SHORT).show();
            }

            ((MainActivity)getActivity()).hideSoftKeyboard();
        });

        builder.setView(view);
        builder.setCancelable(true);

        return builder.create();
    }

    public void dismissDialog(){
        this.dismiss();
    }
}
