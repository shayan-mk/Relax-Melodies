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

    private List<Melody> melodies;
    private ItemActionListener listener;

    public MelodiesAdapter(ItemActionListener listener) {
        this.melodies = new ArrayList<>();
    }

    @Override
    public int getCount() {
       return melodies.size();
    }

    @Override
    public Object getItem(int position) {
        return melodies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MelodyItemBinding binding = MelodyItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        ImageView image= binding.iconimage;
        TextView textView= binding.textdata;
        Melody melody = melodies.get(position);

        image.setImageResource(melody.getIconResource());
        textView.setText(melody.getName());

        convertView.setOnClickListener(view -> listener.onItemClick(melody.ID));

        return binding.getRoot();
    }

    public void submitList(List<Melody> melodies) {
        this.melodies = new ArrayList<>(melodies);
        notifyDataSetChanged();
    }

    public interface ItemActionListener {
        void onItemClick(int ID);
    }
}
