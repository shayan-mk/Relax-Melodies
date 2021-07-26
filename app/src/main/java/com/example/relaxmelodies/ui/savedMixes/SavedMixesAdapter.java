package com.example.relaxmelodies.ui.savedMixes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.SavedMixItemBinding;

import org.jetbrains.annotations.NotNull;

public class SavedMixesAdapter extends ListAdapter<Mix, SavedMixesAdapter.MixViewHolder> {

    ItemActionListener listener;

    public SavedMixesAdapter(ItemActionListener listener) {
        super(new DiffCallback());
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public MixViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        SavedMixItemBinding binding = SavedMixItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MixViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MixViewHolder holder, int position) {
        Mix currentItem = getItem(position);
        holder.bind(currentItem, listener);
    }

    public static class MixViewHolder extends RecyclerView.ViewHolder {

        private SavedMixItemBinding binding;

        public MixViewHolder(SavedMixItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Mix savedMix, ItemActionListener listener) {
            binding.savedMixName.setText(savedMix.getName());
            binding.savedMixDelete.setOnClickListener(view -> listener.onItemClick(savedMix.getName()));
            itemView.setOnClickListener(view -> listener.onItemDelete(savedMix.getName()));
        }
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<Mix> {

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Mix oldItem, @NonNull @NotNull Mix newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Mix oldItem, @NonNull @NotNull Mix newItem) {
            boolean same = true;
            if (!oldItem.getName().equals(newItem.getName())) same = false;
            if (!oldItem.getMelody_ids().containsAll(newItem.getMelody_ids())) same = false;

            return same;
        }
    }

    public interface ItemActionListener {
        void onItemClick(String mixName);

        void onItemDelete(String mixName);
    }

}
