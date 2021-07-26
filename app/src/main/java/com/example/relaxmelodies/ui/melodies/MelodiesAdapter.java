package com.example.relaxmelodies.ui.melodies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.relaxmelodies.database.Melody;
import com.example.relaxmelodies.databinding.MelodyItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MelodiesAdapter extends BaseAdapter {
    private static final String TAG = MelodiesAdapter.class.getName();

    private List<Melody> melodies;

    public MelodiesAdapter() {
        this.melodies = new ArrayList<>();
    }

    @Override
    public int getCount() {
       return melodies.size() * 2;
    }

    @Override
    public Object getItem(int position) {
        if (position % 2 != 0) return null;

        return melodies.get(position / 2);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MelodyItemBinding binding = MelodyItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        ImageView image= binding.iconImage;
        TextView textView= binding.textData;
        if (position % 2 == 0) {
            Melody melody = melodies.get(position / 2);
            image.setImageResource(melody.getIconResource());
            textView.setText(melody.getName());
        }

        return binding.getRoot();
    }

    public void submitList(List<Melody> melodies) {
        this.melodies = new ArrayList<>(melodies);
        notifyDataSetChanged();
    }
}
