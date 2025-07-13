package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.PromotionType; // Thay đổi theo package của bạn
import com.example.myassignmentproject.R; // Thay đổi theo package gốc của ứng dụng bạn

import java.util.List;

public class PromotionTypeAdapter extends RecyclerView.Adapter<PromotionTypeAdapter.PromotionTypeViewHolder> {

    private List<PromotionType> promotionTypeList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PromotionType promotionType);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PromotionTypeAdapter(List<PromotionType> promotionTypeList) {
        this.promotionTypeList = promotionTypeList;
    }

    @NonNull
    @Override
    public PromotionTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion_type, parent, false);
        return new PromotionTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionTypeViewHolder holder, int position) {
        PromotionType promotionType = promotionTypeList.get(position);
        holder.promotionTypeName.setText(promotionType.getTypeName());
        holder.promotionTypeDescription.setText(promotionType.getDescription());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(promotionType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionTypeList.size();
    }

    public static class PromotionTypeViewHolder extends RecyclerView.ViewHolder {
        TextView promotionTypeName;
        TextView promotionTypeDescription;

        public PromotionTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            promotionTypeName = itemView.findViewById(R.id.text_promotion_type_name);
            promotionTypeDescription = itemView.findViewById(R.id.text_promotion_type_description);
        }
    }

    public void updatePromotionTypes(List<PromotionType> newPromotionTypes) {
        this.promotionTypeList.clear();
        this.promotionTypeList.addAll(newPromotionTypes);
        notifyDataSetChanged();
    }
}