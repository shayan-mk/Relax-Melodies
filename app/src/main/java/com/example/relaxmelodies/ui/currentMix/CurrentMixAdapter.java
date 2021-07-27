package com.example.relaxmelodies.ui.currentMix;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaxmelodies.database.Melody;
import com.example.relaxmelodies.database.Mix;
import com.example.relaxmelodies.databinding.CurrentMixItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CurrentMixAdapter extends ListAdapter<Melody, CurrentMixAdapter.MelodyViewHolder> {
    ItemActionListener listener;

    public CurrentMixAdapter(ItemActionListener listener) {
        super(new CurrentMixAdapter.DiffCallback());
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public MelodyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CurrentMixItemBinding binding = CurrentMixItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MelodyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MelodyViewHolder holder, int position) {
        Melody currentItem = getItem(position);
        holder.bind(currentItem, listener);
    }

    public static class MelodyViewHolder extends RecyclerView.ViewHolder {

        private CurrentMixItemBinding binding;

        public MelodyViewHolder(CurrentMixItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Melody melody, ItemActionListener listener) {
//            binding.savedMixName.setText(melody.getName());
//            binding.savedMixDelete.setOnClickListener(view -> listener.onItemDelete(melody));
//            itemView.setOnClickListener(view -> listener.onItemClick(melody.getMelody_ids()));
        }
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<Melody> {

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Melody oldItem, @NonNull @NotNull Melody newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Melody oldItem, @NonNull @NotNull Melody newItem) {
            boolean same = true;
            if( oldItem.getId() != newItem.getId()) same = false;
            if (!oldItem.getName().equals(newItem.getName())) same = false;
            if (oldItem.getIconResource() != newItem.getIconResource()) same = false;
            if (oldItem.getResourceId() != newItem.getResourceId()) same = false;

            return same;
        }
    }

    public interface ItemActionListener {
        void onItemClick(List<Integer> melodyIds);

        void onItemDelete(Mix mix);
    }
}
