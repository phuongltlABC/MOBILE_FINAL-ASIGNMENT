package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.Size;
import com.example.myassignmentproject.R;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeViewHolder> {

    private List<Size> sizeList;
    private OnItemClickListener listener;

    // Interface xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Size size);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SizeAdapter(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    @NonNull
    @Override
    public SizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size, parent, false);
        return new SizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeViewHolder holder, int position) {
        Size size = sizeList.get(position);
        holder.sizeName.setText(size.getSizeName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null && holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                listener.onItemClick(size);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    public void updateSizes(List<Size> newSizes) {
        this.sizeList.clear();
        this.sizeList.addAll(newSizes);
        notifyDataSetChanged();
    }

    static class SizeViewHolder extends RecyclerView.ViewHolder {
        TextView sizeName;

        public SizeViewHolder(@NonNull View itemView) {
            super(itemView);
            sizeName = itemView.findViewById(R.id.text_size_name);
        }
    }
}
